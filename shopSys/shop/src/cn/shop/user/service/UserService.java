package cn.shop.user.service;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.user.dao.UserDao;
import cn.shop.user.vo.User;
import cn.shop.utils.PageBean;
import cn.shop.utils.UUIDUtils;
import cn.shop.utils.mail.Mail;
import cn.shop.utils.mail.MailUtils;

@Transactional
public class UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User findByUsername(String username) {
		return userDao.findByUserName(username);
	}
	
	public void save(User user) {
		user.setState(0);
		user.setCode(UUIDUtils.getUUID() + UUIDUtils.getUUID());
		userDao.save(user);
		
		//注册信息发邮件的过程
		Properties prop = new Properties();
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String host = prop.getProperty("host");  //获取主机
		String username = prop.getProperty("username"); //获取邮箱的用户名
		String password = prop.getProperty("password"); //获取用户密码
		//登录邮件服务器  得到Session
		Session session = MailUtils.createSession(host, username, password);
		
		//创建Mail对象
		String from = prop.getProperty("from");
		String to = user.getEmail();
		String subject = prop.getProperty("subject");
		String content = MessageFormat.format(prop.getProperty("content"), user.getCode());
		Mail mail = new Mail(from, to, subject, content);
		//发邮件
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public User findByCode(String code) {
		User user = userDao.findByCode(code);
		return user;
	}

	public void update(User userExsit) {
		userDao.update(userExsit);
	}

	public User login(User user) {
		return userDao.findUser(user);
	}

	public PageBean<User> findAll(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		pageBean.setPage(page);
		pageBean.setLimit(5);
		int totalCount = userDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = totalCount % 5 == 0 ? totalCount/5 : totalCount/5+1;
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * 5;
		List<User> list = userDao.findAllUser(begin, 5);
		pageBean.setList(list);
		return pageBean;
	}
}

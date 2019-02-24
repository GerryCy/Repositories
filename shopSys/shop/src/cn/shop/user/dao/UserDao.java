package cn.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;


import cn.shop.user.vo.User;
import cn.shop.utils.PageHibernateCallback;

public class UserDao {

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	/*
	 * 按照用户名查询
	 */
	public User findByUserName(String username) {
		String hql = "from User where username = ?";
		List<User> list = (List<User>)hibernateTemplate.find(hql, username);
		if(list != null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public void save(User user) {
		hibernateTemplate.save(user);
	}

	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = (List<User>)hibernateTemplate.find(hql, code);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void update(User userExsit) {
		hibernateTemplate.update(userExsit);
	}

	public User findUser(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = (List<User>) hibernateTemplate.find(hql, user.getUsername(), user.getPassword(), 1);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int findTotalCount() {
		String hql = "select count(*) from User";
		List<Long> list = (List<Long>)hibernateTemplate.find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findAllUser(int begin, int i) {
		String hql = "from User order by uid desc";
		List<User> list = (List<User>)hibernateTemplate.execute(new PageHibernateCallback<User>(hql, null, begin, i));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}

package cn.shop.user.action;


import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.shop.user.service.UserService;
import cn.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{

	private User user = new User();    //使用模型驱动的方式获取表单提交的参数信息
	private UserService userService;
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User getModel() {
		return user;
	}

	public String registPage() {
		
		return "regist";
	}
	
	public String findByName() throws Exception {
		String userName = user.getUsername();
		User userExsit = userService.findByUsername(userName);
		HttpServletResponse response = ServletActionContext.getResponse();
//		userName = userName.replaceAll(" ", "");
//		if(userName=="") {
//			response.getWriter().println("<font color='red'>用户名不能为空</font>");
//		}
		response.setContentType("text/html;charset=UTF-8");
		if(userExsit != null) {
			//表名用户名存在
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		} else {
			// 没查询到该用户:用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	
	public String regist() {
		userService.save(user);
		return "msg";
	}
	
	public String active() {
		//根据邮件的激活码来查询用户信息
		User userExsit = userService.findByCode(user.getCode());
		if(userExsit == null) {  //激活码错误
			this.addActionMessage("激活码错误");
		} else {
			userExsit.setState(1);
			userExsit.setCode(null);
			userService.update(userExsit);
			this.addActionMessage("激活成功!请去登录");
		}
		return "msg";
	}
	
	public String loginPage() {
		return "loginPage";
	}
	
	public String login() {
		String checkcode1 = (String) ServletActionContext.getRequest()
				.getSession().getAttribute("checkcode");
		User userExsit = userService.login(user);
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			ServletActionContext.getRequest().setAttribute("exsitUser", userExsit);
			this.addActionError("验证码输入错误!");
			return "checkcodeFail";
		}
		
		if(userExsit == null) {
			this.addActionMessage("登录失败:用户名或密码错误或用户未激活!");
			return LOGIN;
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("exsitUser", userExsit);
		}
		return "loginSuccess";
	}
	
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}

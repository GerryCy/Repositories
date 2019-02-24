package cn.shop.user.adminaction;

import cn.shop.user.service.UserService;
import cn.shop.user.vo.User;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	private UserService userService;
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public User getModel() {
		return user;
	}
	
	public String findAll() {
		PageBean<User> pageBean = userService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
}

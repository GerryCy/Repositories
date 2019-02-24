package cn.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import cn.shop.adminuser.service.AdminUserService;
import cn.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUserService adminUserService;
	private AdminUser adminUser = new AdminUser();
	
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	@Override
	public AdminUser getModel() {
		return adminUser;
	}
	
	public String login() {
		AdminUser adminUserExsit = adminUserService.find(adminUser);
		if(adminUserExsit == null) {
			this.addActionError("用户名或密码错误!请检查后重新登录");
			return "loginFail";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", adminUserExsit);
			return "loginSuccess";
		}
	}
	
	public String quit() {
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}

package cn.shop.interceptor;

import org.aopalliance.intercept.Invocation;
import org.apache.struts2.ServletActionContext;

import cn.shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		AdminUser adminUser = (AdminUser)ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if(adminUser != null) {
			return invocation.invoke();
		} else {
			ActionSupport actionSupport = (ActionSupport)invocation.getAction();
			actionSupport.addActionError("请登录后操作");
			return ActionSupport.LOGIN;
		}
	}

}

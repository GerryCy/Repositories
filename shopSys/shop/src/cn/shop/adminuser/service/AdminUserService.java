package cn.shop.adminuser.service;

import cn.shop.adminuser.dao.AdminUserDao;
import cn.shop.adminuser.vo.AdminUser;

public class AdminUserService {

	private AdminUserDao adminUserDao;
	
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser find(AdminUser adminUser) {
		return adminUserDao.find(adminUser);
	}
}

package cn.shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.shop.adminuser.vo.AdminUser;

public class AdminUserDao {

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public AdminUser find(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = (List<AdminUser>)hibernateTemplate.find(hql, adminUser.getUsername(), adminUser.getPassword());
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}

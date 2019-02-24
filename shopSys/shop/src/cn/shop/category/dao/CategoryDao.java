package cn.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;


import cn.shop.category.vo.Category;

public class CategoryDao {

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = (List<Category>) hibernateTemplate.find(hql);
		return list;
	}

	public void save(Category category) {
		hibernateTemplate.save(category);
	}

	public Category find(Integer cid) {
		return hibernateTemplate.get(Category.class, cid);
	}

	public void delete(Category category) {
		hibernateTemplate.delete(category);
	}

	public void update(Category category) {
		hibernateTemplate.update(category);
	}


}
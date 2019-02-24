package cn.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageHibernateCallback;

public class CategorySecondDao {

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int findTotalCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = (List<Long>)hibernateTemplate.find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findAll(int begin, int i) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = hibernateTemplate.execute(
				new PageHibernateCallback<CategorySecond>(hql, null, begin, i));
		return list;
	}

	public void save(CategorySecond categorySecond) {
		hibernateTemplate.save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		hibernateTemplate.delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		String hql = "from CategorySecond where csid = ?";
		List<CategorySecond> list = (List<CategorySecond>)hibernateTemplate.find(hql, csid);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void update(CategorySecond categorySecond) {
		hibernateTemplate.update(categorySecond);
	}

	public List<Product> findAll() {
		String hql = "from CategorySecond";
		List<Product> list = (List<Product>)hibernateTemplate.find(hql);
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}

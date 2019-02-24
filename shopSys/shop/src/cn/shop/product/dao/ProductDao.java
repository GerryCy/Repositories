package cn.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.shop.product.vo.Product;
import cn.shop.utils.PageHibernateCallback;

public class ProductDao{

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//首页上周热门商品查询
	public List<Product> findHot() {
		//采用离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);
		//查询热门商品
		detachedCriteria.add(Restrictions.eq("is_hot", 1));
		
		detachedCriteria.addOrder(Order.desc("pdate"));
		
		//执行查询的操作
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(detachedCriteria, 0, 10);
		return list;
	}

	public List<Product> findNew() {
		//采用离线条件查询
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Product.class);

		detachedCriteria.addOrder(Order.desc("pdate"));
		
		//执行查询的操作
		List<Product> list = (List<Product>) hibernateTemplate.findByCriteria(detachedCriteria, 0, 10);
		return list;
	}

	public Product findByPid(Integer pid) {
		String hql = "from Product where pid = ?";
		List<Product> list = (List<Product>) hibernateTemplate.find(hql, pid);
		
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public int findRecordsByCid(Integer cid) {
		String hql = "select count(*) from Product p where p.categorySecond.category.cid = ? ";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, cid);
		if(list != null && list.size() >= 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPageCid(Integer cid, Integer begin, int limit) {
		String hql = "select p from Product p join p.categorySecond cs join cs.category c where c.cid = ?";
		List<Product> list = (List<Product>) hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{cid}, begin, limit));
		if(list != null && list.size() >= 0) {
			return list;
		}
		return null;
	}

	public List<Product> findByPageCsid(Integer csid, Integer begin, Integer limit) {
		String hql = "select p from Product p join p.categorySecond cs where cs.csid = ?";
		List<Product> list = (List<Product>) hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, new Object[]{csid}, begin, limit));
		if(list != null && list.size() >= 0) {
			return list;
		}
		return null;
	}

	public int findRecordsByCsid(Integer csid) {
		String hql = "select count(*) from Product p where p.categorySecond.csid = ?";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql, csid);
		if(list != null && list.size() >= 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public int findTotalCount() {
		String hql = "select count(*) from Product";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Product> findByPage(int begin, int i) {
		String hql = "from Product order by csid desc";
		List<Product> list = (List<Product>)hibernateTemplate.execute(new PageHibernateCallback<Product>(hql, null, begin, i));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public void save(Product product) {
		hibernateTemplate.save(product);
	}

	public void delete(Product product) {
		hibernateTemplate.delete(product);
	}

	public void update(Product product) {
		hibernateTemplate.update(product);
	}

}

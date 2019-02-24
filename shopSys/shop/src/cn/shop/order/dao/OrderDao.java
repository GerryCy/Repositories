package cn.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.utils.PageHibernateCallback;

public class OrderDao {

	private HibernateTemplate hibernateTemplate;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public int findRecordByUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ? ";
		List<Long> list = (List<Long>)hibernateTemplate.find(hql, uid);
		if(list != null && list.size() >= 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findOrdersByUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = (List<Order>) hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin , limit));
		if(list != null && list.size() >= 0) {
			return list;
		}
		return null;
	}

	public Order findByOid(Integer oid) {
		Order order = hibernateTemplate.get(Order.class, oid);
		return order;
	}

	public void saveOrder(Order order) {
		hibernateTemplate.save(order);
	}

	public int findTotalCount() {
		String hql = "select count(*) from Order";
		List<Long> list = (List<Long>) hibernateTemplate.find(hql);
		if(list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findAll(int begin, int i) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = (List<Order>) hibernateTemplate.execute(new PageHibernateCallback<Order>(hql, null, begin, i));
		if(list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = (List<OrderItem>) hibernateTemplate.find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}

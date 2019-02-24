package cn.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.order.dao.OrderDao;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.utils.PageBean;

@Transactional
public class OrderService {

	private OrderDao orderDao;
	
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public PageBean<Order> findByUid(Integer uid, Integer page) {
		//设置当前页码
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setPage(page);//当前页数
		pageBean.setLimit(10);//每页显示数目
		int totalRecords = orderDao.findRecordByUid(uid);
		pageBean.setTotalCount(totalRecords);//设置总记录数
		int totalPage = totalRecords % 10 == 0 ? totalRecords/10 : totalRecords/10 + 1;
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1 ) * 10;
		List<Order> list = orderDao.findOrdersByUid(uid, begin, 10);
		pageBean.setList(list);
		return pageBean;
	}

	public Order findByOid(Integer oid) {
		Order order = orderDao.findByOid(oid);
		return order;
	}

	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}

	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		pageBean.setLimit(10);
		pageBean.setPage(page);
		int totalCount = orderDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = totalCount % 10 == 0 ? totalCount/10 : totalCount/10 + 1;
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * 10;
		List<Order> list = orderDao.findAll(begin, 10);
		pageBean.setList(list);
		return pageBean;
	}

	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}
}

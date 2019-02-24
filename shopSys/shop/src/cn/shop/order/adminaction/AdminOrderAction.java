package cn.shop.order.adminaction;

import java.util.List;

import cn.shop.order.service.OrderService;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	private Integer page;
	private OrderService orderService;
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	@Override
	public Order getModel() {
		return order;
	}
	
	public String findAll() {
		PageBean<Order> pageBean = orderService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String findOrderItem() {
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
}

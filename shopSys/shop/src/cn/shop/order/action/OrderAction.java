package cn.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.shop.cart.vo.Cart;
import cn.shop.cart.vo.CartItem;
import cn.shop.order.service.OrderService;
import cn.shop.order.vo.Order;
import cn.shop.order.vo.OrderItem;
import cn.shop.user.vo.User;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	private OrderService orderService;
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	@Override
	public Order getModel() {
		return order;
	}
	
	public String findByUid() {
		//获得uid
		User userExsit = (User)ServletActionContext.getRequest().getSession().getAttribute("exsitUser");
		Integer uid = userExsit.getUid();
		//根据用户的id查询订单
		PageBean<Order> pageBean = orderService.findByUid(uid, page); 
		//将PageBean放在桌面之上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	
	public String findByOid() {
		order = orderService.findByOid(order.getOid());
		return "findByOid";
	}
	
	public String saveOrder() {
		//先查看购物车是否为空
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			this.addActionMessage("购物车为空!请选择商品之后再加入订单");
			return "msg";
		}
		User userExsit = (User)ServletActionContext.getRequest().getSession().getAttribute("exsitUser");
		if(userExsit == null) {
			this.addActionMessage("请登录后操作!");
			return "msg";
		}
		order.setTotal(cart.getTotal());//设置总数
		order.setState(1); //设置订单状态
		order.setUser(userExsit);//设置订单的拥有者
		order.setOrdertime(new Date());//设置订单创建的时间
		order.setAddr(userExsit.getAddr());//设置订单的地址信息
		order.setPhone(userExsit.getPhone());
		order.setName(userExsit.getName());
		
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setOrder(order);
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setSubtotal(cartItem.getSubtotal());
			order.getOrderItems().add(orderItem);
		}
		
		orderService.saveOrder(order);
		cart.clearCart();
		return "saveOrder";
	}
}

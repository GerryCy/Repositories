package cn.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import cn.shop.cart.vo.Cart;
import cn.shop.cart.vo.CartItem;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport  {

	public Cart cart = new Cart();
	private Integer pid;
	private int count;
	
	private ProductService productService;
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public String myCart() {
		Cart cart = getCart();
		return "myCart";
	}
	
	public String addCart() {
		CartItem cartItem = new CartItem();
		
		cartItem.setCount(count);
		
		Product product = productService.findByPid(pid);
		
		cartItem.setProduct(product);
		
		//将商品添加到购物车  然后将购物车放在Session之中
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	public String clearCart() {
		Cart cart = getCart();
		
		cart.clearCart();
		return "clearCart";
	}
	
	public String removeCart() {
		Cart cart = getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	
	private Cart getCart() {
		Cart cart = (Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}

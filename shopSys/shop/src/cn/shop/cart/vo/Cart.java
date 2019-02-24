package cn.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

	//购物车的属性就是由购物条目组成
	//Key值：商品的pid   Value：购物条目
	private Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	private double total;
	
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//总计
	public double getTotal() {
		return total;
	}

	public void addCart(CartItem cartItem) {
		/*
		 *  * 如果存在:
		 *  	* 数量增加
		 *  	* 总计 = 总计 + 购物项小计
		 *  * 如果不存在:
		 *  	* 向map中添加购物项
		 *  	* 总计 = 总计 + 购物项小计
		 */
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}

	public void clearCart() {
		map.clear();
		total = 0;
	}

	public void removeCart(Integer pid) {
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	
	
}

package cn.shop.index.action;


import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {

	//注入一级分类的Service
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public String execute() throws Exception {
		//查询所有的一级分类
		List<Category> clist = categoryService.findAll();
		//将一级分类放入到Session之中
		ServletActionContext.getRequest().getSession().setAttribute("cList", clist);
		//查询热门分类
		List<Product> hList = productService.findHot();
		//保存到值栈之中
		ActionContext.getContext().getValueStack().set("hList", hList);
		//查询最新商品
		List<Product> nList = productService.findNew();
		//保存到值栈之中
		ActionContext.getContext().getValueStack().set("nList", nList);
		
		return "index";
	}
}

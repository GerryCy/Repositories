package cn.shop.product.action;

import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{

	private Product product = new Product();
	private ProductService productService;
	private Integer cid;
	private Integer page;
	private Integer csid;
	
	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	public Integer getCsid() {
		return csid;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	
	public Integer getCid() {
		return cid;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Override
	public Product getModel() {
		return product;
	}
	
	public String findByPid() {
		//由于是采用模型驱动方式获取提交参数  所以product对象直接就在值栈的栈顶
		product = productService.findByPid(product.getPid());  
		return "findByPid";
	}
	
	public String findByCid() {
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);
		//将PageBean放到值栈之中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	public String findByCsid() {
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		//将PageBean放到值栈之中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}

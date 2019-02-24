package cn.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.product.service.ProductService;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product = new Product();
	private Integer page;
	private ProductService productService;
	private CategorySecondService categorySecondService;
	
	//上传文件的三个条件
	private File upload;
	private String uploadFileName;
	private String uploadContentType;
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	@Override
	public Product getModel() {
		return product;
	}
	
	public String findAll() {
		//分页查询
		PageBean<Product> pageBean = productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String addPage() {
		List<Product> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	/*
	 <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" 
			   URIEncoding="UTF-8" useBodyEncodingForURI="true" />
	 */
	public String save() throws IOException{
		product.setPdate(new Date());
		if(upload != null) {
			//获得图片的上传路径
			String path = ServletActionContext.getServletContext().getRealPath("/products");
			//创建上传文件对象
			File diskFile = new File(path + "//" + uploadFileName);
			//上传文件
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		return "save";
	}
	
	public String delete() {
		productService.delete(product);
		return "delete";
	}
	
	public String edit() {
		product = productService.findByPid(product.getPid());
		List<Product> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "edit";
	}
	
	public String update() {
		productService.update(product);
		return "update";
	}
}

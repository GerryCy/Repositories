package cn.shop.categorysecond.adminaction;

import java.util.List;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;
import cn.shop.categorysecond.service.CategorySecondService;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements
		ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();
	private CategorySecondService categorySecondService;
	private Integer page;
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	public String findAll() {
		//分页查询
		PageBean<CategorySecond> pageBean = categorySecondService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String addPage() {
		//查询所有一级分类  在一级分类下面添加二级分裂
		List<Category> list = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", list);
		return "addPage";
	}
	
	public String save() {
		categorySecondService.save(categorySecond);
		return "save";
	}
	
	public String delete() {
		categorySecondService.delete(categorySecond);
		return "delete";
	}
	
	public String edit() {
		//查询二级分类所属的一级分类
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//所有的一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "edit";
	}
	
	public String update() {
		categorySecondService.update(categorySecond);
		return "update";
	}
}

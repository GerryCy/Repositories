package cn.shop.category.adminaction;

import java.util.List;

import cn.shop.category.service.CategoryService;
import cn.shop.category.vo.Category;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category = new Category();
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Override
	public Category getModel() {
		return category;
	}
	
	public String findAll() {
		List<Category> list = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", list);
		return "findAll";
	}
	
	public String delete() {
		//先查询该分类：注意是级联删除
		category = categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	public String edit() {
		category = categoryService.findByCid(category.getCid());
		return "edit";
	}

	public String update() {
		categoryService.update(category);
		return "update";
	}
	
	public String save() {
		categoryService.save(category);
		return "saveSuccess";
	}
}

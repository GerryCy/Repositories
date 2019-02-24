package cn.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.categorysecond.dao.CategorySecondDao;
import cn.shop.categorysecond.vo.CategorySecond;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

@Transactional
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;
	
	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findAll(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setLimit(10);
		pageBean.setPage(page);
		int totalCount = categorySecondDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = totalCount % 10 == 0 ? totalCount/10 : totalCount/10 + 1;
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * 10;
		List<CategorySecond> list = categorySecondDao.findAll(begin, 10);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<Product> findAll() {
		return categorySecondDao.findAll();
	}
}

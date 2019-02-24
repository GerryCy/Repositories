package cn.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.shop.product.dao.ProductDao;
import cn.shop.product.vo.Product;
import cn.shop.utils.PageBean;

@Transactional
public class ProductService {

	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public List<Product> findHot() {
		return productDao.findHot();
	}
	
	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页记录数
		pageBean.setLimit(12);
		//总记录数
		int totalRecords = productDao.findRecordsByCid(cid);
		pageBean.setTotalCount(totalRecords);
		//总页数
		int totalPage = totalRecords % 12 == 0 ? totalRecords/12 : totalRecords/12 + 1;
		pageBean.setTotalPage(totalPage);	
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * 12;
		List<Product> list = productDao.findByPageCid(cid, begin, 12);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页记录数
		pageBean.setLimit(12);
		//总记录数
		int totalRecords = productDao.findRecordsByCsid(csid);
		pageBean.setTotalCount(totalRecords);
		//总页数
		int totalPage = totalRecords % 12 == 0 ? totalRecords/12 : totalRecords/12 + 1;
		pageBean.setTotalPage(totalPage);	
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * 12;
		List<Product> list = productDao.findByPageCsid(csid, begin, 12);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setLimit(10);
		pageBean.setPage(page);
		int totalCount = productDao.findTotalCount();
		pageBean.setTotalCount(totalCount);
		int totalPage = totalCount % 10 == 0 ? totalCount/10 : totalCount/10 + 1;
		pageBean.setTotalPage(totalPage);
		int begin = (page - 1) * 10;
		List<Product> list = productDao.findByPage(begin, 10);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}
}

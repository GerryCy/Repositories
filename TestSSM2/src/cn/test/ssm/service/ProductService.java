package cn.test.ssm.service;

import cn.test.ssm.po.ProductExtend;
import cn.test.ssm.po.ProductQueryVo;

import java.util.List;

public interface ProductService {
    public List<ProductExtend> findProductListByName(ProductQueryVo productQueryVo) throws Exception;

    public ProductExtend queryProductInfo(Integer id) throws Exception;
}

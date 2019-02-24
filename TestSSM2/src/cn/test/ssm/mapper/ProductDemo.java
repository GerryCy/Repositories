package cn.test.ssm.mapper;

import cn.test.ssm.po.ProductExtend;
import cn.test.ssm.po.ProductQueryVo;

import java.util.List;

public interface ProductDemo {

    public List<ProductExtend> findProductListByName(ProductQueryVo productQueryVo) throws Exception;

    public ProductExtend queryProductInfo(Integer id) throws Exception;
}

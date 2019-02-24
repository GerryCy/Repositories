package cn.test.ssm.service.impl;

import cn.test.ssm.mapper.ProductDemo;
import cn.test.ssm.po.ProductExtend;
import cn.test.ssm.po.ProductQueryVo;
import cn.test.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDemo productDemo;  //自动注入mapper接口，然后在实现service的方法中调用mapper接口中的方法

    @Override
    public List<ProductExtend> findProductListByName(ProductQueryVo productQueryVo) throws Exception {
        return productDemo.findProductListByName(productQueryVo);
    }

    @Override
    public ProductExtend queryProductInfo(Integer id) throws Exception {
        return productDemo.queryProductInfo(id);
    }
}

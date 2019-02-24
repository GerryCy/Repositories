package cn.test.ssm.po;

public class ProductQueryVo {

    //Product类信息
    private Product product;
    //扩展类信息，ProductPo继承了Product类
    private ProductExtend productExtend;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductExtend getProductExtend() {
        return productExtend;
    }

    public void setProductExtend(ProductExtend productExtend) {
        this.productExtend = productExtend;
    }
}

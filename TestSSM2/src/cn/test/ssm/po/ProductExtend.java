package cn.test.ssm.po;

public class ProductExtend extends Product{
    private String name;

    private String desc;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ProductExtend{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                super.toString() +
                '}';
    }
}

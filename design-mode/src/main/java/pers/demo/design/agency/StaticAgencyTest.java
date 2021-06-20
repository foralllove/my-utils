package pers.demo.design.agency;

/**
 * StaticAgencyTest
 *
 * @author: hh
 * @since: 2021/6/13 17:43
 */
public class StaticAgencyTest implements Product {

    private Product product;

    public StaticAgencyTest(Product product) {
        this.product = product;
    }

    @Override
    public String name() {
        System.out.println("方法处理前");
        String name = product.name();
        System.out.println("方法处理后");
        return name + "agency";
    }

    public static void main(String[] args) {
        Product product = new ProductImpl();
        StaticAgencyTest test = new StaticAgencyTest(product);
        String name = test.name();
        System.out.println("静态代理结果:" + name);
    }
}

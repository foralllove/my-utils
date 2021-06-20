package pers.demo.design.agency;

/**
 * ProductImpl
 *
 * @author: hh
 * @since: 2021/6/13 17:41
 */
public class ProductImpl implements Product {
    @Override
    public String name() {
        System.out.println("代理元数据");
        return "name";
    }
}

package pers.demo.design.agency;

import java.lang.reflect.Proxy;

/**
 * DynamicAgencyTest
 *
 * @author: hh
 * @since: 2021/6/13 18:03
 */
public class DynamicAgencyTest<T> {
    //实际处理方法
    private T target;

    public DynamicAgencyTest(T target) {
        this.target = target;
    }

    public T getTarget() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("方法处理前");
                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    System.out.println("方法处理后");
                    return returnValue + "agency";
                });
    }

    public static void main(String[] args) {
        Product product = new ProductImpl();
        Product target = new DynamicAgencyTest<Product>(product).getTarget();
        System.out.println(target.name());
    }
}

package pers.demo.design.agency;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CglibAgencyTest
 *
 * @author: hh
 * @since: 2021/6/13 18:12
 */
public class CglibAgencyTest<T> implements MethodInterceptor {
    private T target;

    public CglibAgencyTest(T target) {
        this.target = target;
    }

    public T getTarget() {
        //工具类
        Enhancer en = new Enhancer();
        //设置父类  所以被final/static 修饰不可继承的类和方法不能被代理
        en.setSuperclass(target.getClass());
        en.setCallback(this);
        //创建子类(代理对象)
        return (T) en.create();

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法处理前");
        //执行目标对象的方法
        Object returnValue = method.invoke(target, objects);
        System.out.println("方法处理后");
        return returnValue + "agency";
    }

    public static void main(String[] args) {
        Product product = new ProductImpl();
        Product target = new CglibAgencyTest<>(product).getTarget();
        System.out.println(target.name());
    }
}

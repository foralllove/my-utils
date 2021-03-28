package pers.demo.design.singleton;

/**
 * HungerSingleton 饿汉模式
 *
 * @author: hh
 * @since: 2021/3/21 18:45
 */
public class HungerSingleton {
    private static HungerSingleton instance = new HungerSingleton();

    //自行创建实例
    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        // 通过该函数向整个系统提供实例
        return instance;
    }
}

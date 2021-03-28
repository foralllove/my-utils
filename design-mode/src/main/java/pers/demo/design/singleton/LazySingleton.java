package pers.demo.design.singleton;

/**
 * LazySingleton 懒汉模式
 *
 * @author: hh
 * @since: 2021/3/21 19:02
 */
public class LazySingleton {
    //未初始化
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

package pers.demo.design.singleton;

/**
 * LazyDoubleCheckSingleton 双重锁模式
 *
 * @author: hh
 * @since: 2021/3/21 19:13
 */
public class LazyDoubleCheckSingleton {
    private static LazyDoubleCheckSingleton instance = null;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (instance == null) {
            //同步锁
            synchronized (LazyDoubleCheckSingleton.class) {
                //第二次判断
                if (instance == null) {
                    instance = new LazyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}

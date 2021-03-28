package pers.demo.design.singleton;

/**
 * InnerSingleton
 *
 * @author: hh
 * @since: 2021/3/21 19:27
 */
public class InnerMethodSingleton {
    private InnerMethodSingleton() {
    }

    public static class InnerSingleton {
        private static InnerMethodSingleton instance = new InnerMethodSingleton();

        public static InnerMethodSingleton getInstance() {
            return InnerSingleton.instance;
        }
    }
}

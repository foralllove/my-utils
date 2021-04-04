package pers.demo.design.fm;

/**
 * FactoryMethodMain
 *
 * @author: hh
 * @since: 2021/4/4 19:42
 */
public class FactoryMethodMain {
    public static void main(String[] args) {
        new DeveloperFactory().getProfession().outDesc();

        new TesterFactory().getProfession().outDesc();
    }
}

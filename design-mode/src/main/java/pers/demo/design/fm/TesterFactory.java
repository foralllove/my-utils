package pers.demo.design.fm;

import pers.demo.design.sfp.Profession;
import pers.demo.design.sfp.Tester;

/**
 * TesterFactory
 *
 * @author: hh
 * @since: 2021/4/4 19:41
 */
public class TesterFactory implements FactoryMethod {
    @Override
    public Profession getProfession() {
        return new Tester();
    }
}

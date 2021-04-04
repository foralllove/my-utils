package pers.demo.design.fm;

import pers.demo.design.sfp.Developer;
import pers.demo.design.sfp.Profession;

/**
 * DeveloperFactory
 *
 * @author: hh
 * @since: 2021/4/4 19:40
 */
public class DeveloperFactory implements FactoryMethod {
    @Override
    public Profession getProfession() {
        return new Developer();
    }
}

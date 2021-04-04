package pers.demo.design.af;

import pers.demo.design.sfp.Developer;
import pers.demo.design.sfp.Profession;
import pers.demo.design.sfp.Tester;

/**
 * ProfessionFactory
 *
 * @author: hh
 * @since: 2021/4/4 20:19
 */
public class ProfessionFactory extends AbstractFactory {
    @Override
    public Profession getProfession(int type) {
        Profession profession = null;
        switch (type) {
            case 1:
                profession = new Tester();
                break;
            case 2:
                profession = new Developer();
                break;
        }

        return profession;
    }

    @Override
    public School getSchool(int type) {
        return null;
    }
}

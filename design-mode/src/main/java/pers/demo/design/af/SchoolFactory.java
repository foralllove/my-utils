package pers.demo.design.af;

import pers.demo.design.sfp.Profession;

/**
 * SchoolFactory
 *
 * @author: hh
 * @since: 2021/4/4 20:20
 */
public class SchoolFactory extends AbstractFactory {
    @Override
    public Profession getProfession(int type) {
        return null;
    }

    @Override
    public School getSchool(int type) {
        School school = null;
        switch (type) {
            case 1:
                school = new QhSchool();
                break;
            case 2:
                school = new BdSchool();
                break;
        }
        return school;
    }
}

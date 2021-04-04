package pers.demo.design.sfp;

/**
 * ProfessionSimpleFactory 职业工程
 *
 * @author: hh
 * @since: 2021/3/28 18:08
 */
public class ProfessionSimpleFactory {

    public static Profession getProfession(int type) {
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
}

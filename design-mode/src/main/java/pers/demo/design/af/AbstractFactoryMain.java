package pers.demo.design.af;

/**
 * AbstractFactoryMain
 *
 * @author: hh
 * @since: 2021/4/4 20:22
 */
public class AbstractFactoryMain {
    public static void main(String[] args) {
        SchoolFactory schoolFactory = new SchoolFactory();
        schoolFactory.getSchool(1).outName();
        schoolFactory.getSchool(2).outName();

        ProfessionFactory professionFactory = new ProfessionFactory();
        professionFactory.getProfession(1).outDesc();
        professionFactory.getProfession(2).outDesc();
    }
}

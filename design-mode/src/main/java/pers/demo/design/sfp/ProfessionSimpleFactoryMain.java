package pers.demo.design.sfp;

/**
 * ProfessionSimpleFactoryMain
 *
 * @author: hh
 * @since: 2021/3/28 18:26
 */
public class ProfessionSimpleFactoryMain {
    public static void main(String[] args) {
        
        //打印用户职业消息
        ProfessionSimpleFactory.getProfession(1).outDesc();
        ProfessionSimpleFactory.getProfession(2).outDesc();
    }
}

package pers.demo.design.af;

import pers.demo.design.sfp.Profession;

/**
 * AbstractFactory
 *
 * @author: hh
 * @since: 2021/4/4 20:17
 */
public abstract class AbstractFactory {

    /**
     * 获取职业信息
     *
     * @param type 职业 type
     * @return 职业
     */
    public abstract Profession getProfession(int type);

    /**
     * 获取学信息
     *
     * @param type 学校 type
     * @return 学校
     */
    public abstract School getSchool(int type);
}

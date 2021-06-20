package pers.demo.design.builder;

import lombok.Data;

/**
 * Car
 *
 * @author: hh
 * @since: 2021/4/18 18:45
 */
@Data
public class Car {
    private String engine;
    private String wheel;

    public String info(){
        return engine + wheel;
    }

}

package pers.demo.design.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CarAgentFactory
 *
 * @author: hh
 * @since: 2021/4/18 19:10
 */
@Data
@AllArgsConstructor
public class CarAgentFactory {
    private Manufacturer manufacturer;

    public Car carOne(){
        manufacturer.setEngine();
        manufacturer.setWheel();
        return manufacturer.carOne();
    }
}

package pers.demo.design.builder;

/**
 * BjManufacturer
 *
 * @author: hh
 * @since: 2021/4/18 19:06
 */
public class BjManufacturer extends Manufacturer {
    @Override
    public void setEngine() {
        car.setEngine("奥迪发动机");
    }

    @Override
    public void setWheel() {
        car.setWheel("奥迪轮胎");
    }
}

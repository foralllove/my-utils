package pers.demo.design.builder;

/**
 * HzManufacturer
 *
 * @author: hh
 * @since: 2021/4/18 19:05
 */
public class HzManufacturer extends Manufacturer {
    @Override
    public void setEngine() {
        car.setEngine("宝马发动机");
    }

    @Override
    public void setWheel() {
        car.setWheel("宝马轮胎");
    }
}

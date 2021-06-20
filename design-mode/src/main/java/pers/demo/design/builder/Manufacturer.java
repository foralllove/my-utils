package pers.demo.design.builder;

/**
 * Manufacturer
 *
 * @author: hh
 * @since: 2021/4/18 19:00
 */
public abstract class Manufacturer {
    protected Car car = new Car();

    public abstract void setEngine();


    public abstract void setWheel();

    public Car carOne() {
        return car;
    }
}

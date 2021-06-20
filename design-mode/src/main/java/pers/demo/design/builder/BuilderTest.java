package pers.demo.design.builder;

/**
 * BuilderTest
 *
 * @author: hh
 * @since: 2021/4/18 20:16
 */
public class BuilderTest {
    public static void main(String[] args) {
        CarAgentFactory carAgentFactory = new CarAgentFactory(new HzManufacturer());
        Car car = carAgentFactory.carOne();
        System.out.println(car.info());
    }
}

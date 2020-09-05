package factory.simpleFactory;

public class SimpleFactoryTest {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car1 = factory.getCar("bmw");
        Car car2 = factory.getCar("Honda");
        car1.run();
        car2.run();
    }
}

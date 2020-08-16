package factory.factoryPattern;

public class FactoryTest {
    public static void main(String[] args) {
        Car car1 = new BmwFactory().getCar();
        Car car2 = new HondaFactory().getCar();
        car1.run();
        car2.run();
    }
}

package factory.factoryPattern;

public class BmwFactory implements Factory {

    @Override
    public Car getCar() {
        return new BMW();
    }
}

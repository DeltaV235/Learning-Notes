package factory.factoryPattern;

public class HondaFactory implements Factory {

    @Override
    public Car getCar() {
        return new Honda();
    }
}

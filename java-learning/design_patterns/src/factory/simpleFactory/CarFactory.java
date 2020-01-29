package factory.simpleFactory;

public class CarFactory {
    public Car getCar(String carName) {
        if (carName.equalsIgnoreCase("BMW"))
            return new BMW();
        else if (carName.equalsIgnoreCase("honda"))
            return new Honda();
        else
            return null;
    }
}

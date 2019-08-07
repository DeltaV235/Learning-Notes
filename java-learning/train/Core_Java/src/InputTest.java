import java.util.*;

public class InputTest {
    public static void main(String[] args) {
        String name;
        int age;
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your name :");
        name = scan.nextLine();
        System.out.print("Please enter your age :");
        age = scan.nextInt() + 1;
        System.out.println("Hello, " + name + ". Next year, you will be " + age);
    }
}

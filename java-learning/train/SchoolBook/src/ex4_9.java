import java.util.Scanner;

public class ex4_9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("input a num:");
        double num = scanner.nextDouble();
        int integer = (int) num;
        double point = num - integer;
        System.out.printf("Integer is %d \t point is %.3f\n", integer, point);
    }
}

import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class ex4_8 {
    public static void main(String[] args) {
        System.out.print("enter a number:");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int position = 1;
        int sum = 0;
        while (true) {
            sum += num / position % 10;
            position *= 10;
            if (num / position == 0)
                break;
        }
        System.out.println("sum = " + sum);
    }
}

import java.util.Scanner;

public class ex4_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a number: ");
        int n = Integer.parseInt(scanner.nextLine());
        long sum = 0;
        for (int i = n; i > 0; i--) {
            int product = 1;
            for (int j = i; j > 0; j--) {
                product *= j;
            }
            sum += product;
        }
        System.out.println("Sum is " + sum);
    }
}

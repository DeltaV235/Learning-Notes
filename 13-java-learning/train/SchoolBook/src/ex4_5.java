import java.util.Scanner;

public class ex4_5 {
    public static void main(String[] args) {
        System.out.print("Please enter n:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double sum = 1;
        for (int i = n; i > 1; i--) {
            long product = 1;
            for (int k = i; k > 0; k--) {
                product *= k;
            }
            for (int j = i - 1; j > 0; j--) {
                product *= -1;
            }
            sum += 1.0 / product;

        }
        System.out.println(sum);
    }
}

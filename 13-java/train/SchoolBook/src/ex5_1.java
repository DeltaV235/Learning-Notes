import java.util.Scanner;

public class ex5_1 {
    public static void main(String[] args) {
        System.out.print("请输入n个数，将输出它们的平均数,n = ? :");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        double[] array = new double[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Please enter no." + (i + 1) + " number : ");
            array[i] = scanner.nextDouble();
        }
        double sum = 0;
        for (double num : array) {
            sum += num;
        }
        System.out.println("平均数为: " + sum / n);
    }
}

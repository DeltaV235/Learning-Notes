import java.math.*;

public class ex5_4 {
    public static void main(String[] args) {
        int[][] array = new int[4][5];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                array[row][col] = (int) (Math.random() * 101);
                System.out.print(array[row][col] + "\t");
            }
            System.out.println();
        }
        int min = array[0][0], max = array[0][0];
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                if (array[row][col] > max) {
                    max = array[row][col];
                }
                if (array[row][col] < min) {
                    min = array[row][col];
                }
            }
        }
        System.out.println("\nMax is " + max + "\t Min is " + min);

    }
}

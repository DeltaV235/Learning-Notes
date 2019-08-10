public class ex4_11 {
    public static void main(String[] args) {
        /*
            输出：
            1  3  6  10  15
            2  5  9  14
            4  8  13
            7  12
            11
        */
        int row, col, head = 1, output, rowPlus = 2, colPlus = 1;
        for (row = 1; row <= 5; row++) {
            output = head;
            for (col = 6 - row; col >= 1; col--) {
                System.out.print(output + "\t");
                output += rowPlus;
                rowPlus++;
            }
            System.out.println();
            rowPlus = 2 + row;
            head += colPlus;
            colPlus++;
        }
    }
}

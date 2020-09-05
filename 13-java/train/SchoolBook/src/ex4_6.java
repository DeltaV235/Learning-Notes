public class ex4_6 {
    public static void main(String[] args) {
        int num = 100;
        while (num < 1000) {
            int ge = num % 10;
            int shi = num / 10 % 10;
            int bai = num / 100;
            int square = ge * ge * ge + shi * shi * shi + bai * bai * bai;
            if (square == num)
                System.out.println(num);
            num++;
        }
    }
}

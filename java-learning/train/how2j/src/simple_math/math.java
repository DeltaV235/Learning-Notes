package simple_math;

public class math {
    public static void main(String[] args) {
        int a, b, c, d;
        for (a = 0; a <= 14; a++) {
            for (b = 0; b <= 10; b++) {
                for (c = 0; c <= 14; c++) {
                    for (d = 0; d <= 10; d++) {
                        if (a + b == 8)
                            if (a + c == 14)
                                if (c - d == 6)
                                    if (b + d == 10)
                                        System.out.println(a + "\t" + b + "\n" + c + "\t" + d);
                    }
                }
            }
        }
    }
}

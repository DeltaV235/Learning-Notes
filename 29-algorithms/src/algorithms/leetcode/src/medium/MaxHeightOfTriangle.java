package medium;

public class MaxHeightOfTriangle {
    public int maxHeightOfTriangle(int red, int blue) {
        int res = 0;

        int redFirst = calOneColorHeight(red, 1);
        int blueSecond = calOneColorHeight(blue, 2);
        if (Math.abs(blueSecond - redFirst) == 1)
            res = Math.max(redFirst, blueSecond);
        else
            res = Math.min(redFirst, blueSecond) + 1;

        int blueFirst = calOneColorHeight(blue, 1);
        int redSecond = calOneColorHeight(red, 2);
        if (Math.abs(blueFirst - redSecond) == 1)
            res = Math.max(res, Math.max(blueFirst, redSecond));
        else
            res = Math.max(res, Math.min(redSecond, blueFirst) + 1);

        return res;
    }

    int calOneColorHeight(int total, int init) {
        int d = 2, n = 0;
        total -= init;
        if (total >= 0) {
            n = init;
        }
        while (total >= 0) {
            total -= init + d;
            d += 2;
            n += 2;
        }
        return Math.max(n - 2, 0);
    }


    public static void main(String[] args) {
        new MaxHeightOfTriangle().maxHeightOfTriangle(10, 1);
    }
}

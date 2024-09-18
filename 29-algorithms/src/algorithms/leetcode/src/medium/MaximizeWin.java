package medium;

/**
 * Not Completed
 */
public class MaximizeWin {
    public static void main(String[] args) {
        System.out.println(new MaximizeWin().maximizeWin(new int[]{1, 1, 2, 2, 3, 3, 5}, 2));
    }

    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] prizeCountOnX = new int[prizePositions[n - 1] + 1];

        for (int i = 0; i < n; i++) {
            int position = prizePositions[i];
            prizeCountOnX[position] = prizeCountOnX[position] + 1;
        }

        int biggestCount = 0;
        int secondBiggestCount = 0;
        for (int i = 1; i + k < prizeCountOnX.length; i++) {
            int sum = 0;
            for (int j = i; j <= i + k; j++) {
                sum += prizeCountOnX[j];
            }
            if (sum > biggestCount) {
                secondBiggestCount = biggestCount;
                biggestCount = sum;
            } else if (sum > secondBiggestCount) {
                secondBiggestCount = sum;
            }
        }

        return biggestCount + secondBiggestCount;
    }
}

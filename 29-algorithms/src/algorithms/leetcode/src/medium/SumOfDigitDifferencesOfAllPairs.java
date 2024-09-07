package medium;

/**
 * <a href="https://leetcode.cn/problems/sum-of-digit-differences-of-all-pairs/description">sum-of-digit-differences-of-all-pairs</a>
 */
public class SumOfDigitDifferencesOfAllPairs {
    public long sumDigitDifferences(int[] nums) {
        long sum = 0;
        int[][] numSplitArr = new int[nums.length][String.valueOf(nums[0]).length()];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int[] firstNumSplit;
                int[] secondNumSplit;
                if (numSplitArr[i][0] != 0) {
                    firstNumSplit = numSplitArr[i];
                } else {
                    firstNumSplit = splitNum(nums[i]);
                    numSplitArr[i] = firstNumSplit;
                }
                if (numSplitArr[j][0] != 0) {
                    secondNumSplit = numSplitArr[j];
                } else {
                    secondNumSplit = splitNum(nums[j]);
                    numSplitArr[j] = secondNumSplit;
                }

                for (int k = 0; k < firstNumSplit.length; k++) {
                    if (firstNumSplit[k] != secondNumSplit[k])
                        sum++;
                }
            }
        }
        return sum;
    }

    public int[] splitNum(int num) {
        int[] result = new int[String.valueOf(num).length()];
        int i = result.length - 1;
        while (num != 0) {
            int split = num % 10;
            num /= 10;
            result[i] = split;
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        SumOfDigitDifferencesOfAllPairs sumOfDigitDifferencesOfAllPairs = new SumOfDigitDifferencesOfAllPairs();
        int[] nums = {13, 23, 12};
        System.out.println(sumOfDigitDifferencesOfAllPairs.sumDigitDifferences(nums));
    }
}

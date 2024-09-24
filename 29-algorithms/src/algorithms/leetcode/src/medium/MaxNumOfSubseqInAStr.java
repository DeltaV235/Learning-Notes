package medium;

/**
 * <a href="https://leetcode.cn/problems/maximize-number-of-subsequences-in-a-string/description">Leetcode</a>
 */
public class MaxNumOfSubseqInAStr {
    public long maximumSubsequenceCount(String text, String pattern) {
        char[] textArr = text.toCharArray();
        char[] patternArr = pattern.toCharArray();
        char first = patternArr[0];
        char second = patternArr[1];

        // 如果 pattern[0] == pattern[1]，计算 1 -> count 的等差数列的和，count 为 text 中 pattern[0] 的数量
        if (first == second) {
            int count = 0;
            for (int i = 0; i < textArr.length; ++i) {
                if (textArr[i] == first)
                    ++count;
            }
            return (long) ((1.0 + count) / 2 * count);
        }

        long firstBestCount = 0;
        int twilightLine = text.indexOf(second);
        if (twilightLine != -1) {
            long beforeTwilightFirstCharCount = 1;
            long afterTwilightFirstCharCount = 0;
            long afterTwilightSecondCharCount = 0;
            // 获得在第一个 pattern[1] 前存在的 pattern[0] 的数量
            for (int i = 0; i < twilightLine; ++i) {
                if (textArr[i] == first)
                    ++beforeTwilightFirstCharCount;
            }

            long afterCount = 0;
            for (int i = twilightLine; i < textArr.length; ++i) {
                if (textArr[i] == first) {
                    ++afterTwilightFirstCharCount;
                    continue;
                }

                if (textArr[i] == second) {
                    ++afterTwilightSecondCharCount;
                    afterCount += afterTwilightFirstCharCount;
                }
            }

            // text = "aaabbaab", pattern = "ab"
            // aaaa|bbabab
            // 4 * 3 + (1 + 2)
            firstBestCount = beforeTwilightFirstCharCount * afterTwilightSecondCharCount + afterCount;
        }

        long secondBestCount = 0;

        twilightLine = text.lastIndexOf(first);
        if (twilightLine != -1) {
            long beforeTwilightFirstCharCount = 0;
            long afterTwilightFirstCharCount = 0;
            long afterTwilightSecondCharCount = 1;

            for (int i = twilightLine + 1; i < textArr.length; ++i) {
                if (textArr[i] == second)
                    ++afterTwilightSecondCharCount;
            }

            long beforeCount = 0;
            for (int i = 0; i <= twilightLine; ++i) {
                if (textArr[i] == first) {
                    ++beforeTwilightFirstCharCount;
                    continue;
                }

                if (textArr[i] == second) {
                    beforeCount += beforeTwilightFirstCharCount;
                }
            }

            // text = "aaabbaab", pattern = "ab"
            // aaabbaba|bb
            // 2 * 5 + (3 + 3 + 4)
            secondBestCount = afterTwilightSecondCharCount * beforeTwilightFirstCharCount + beforeCount;
        }

        return Math.max(firstBestCount, secondBestCount);

    }

    public static void main(String[] args) {
        String text = "aabb";
        String pattern  = "ab";
        long result = new MaxNumOfSubseqInAStr().maximumSubsequenceCount(text, pattern);
        System.out.println("result = " + result);
    }
}

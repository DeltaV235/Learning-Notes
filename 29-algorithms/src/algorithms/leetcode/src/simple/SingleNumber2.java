package simple;

import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.cn/submissions/detail/474384555/">只出现一次的数字 II</a>
 */
public class SingleNumber2 {
    public static void main(String[] args) {
        int ans = new Solution().singleNumber(new int[]{1, 4, 1, 1});
        System.out.println("ans = " + ans);
    }
}

class Solution {
    public int singleNumber(int[] nums) {
        Set<Integer> anwSet = new HashSet<>();
        Set<Integer> tripleSet = new HashSet<>();

        for (int num : nums) {
            if (tripleSet.contains(num)) {
                continue;
            }
            if (anwSet.contains(num)) {
                tripleSet.add(num);
                anwSet.remove(num);
                continue;
            }
            anwSet.add(num);
        }
        return (int) anwSet.toArray()[0];
    }
}
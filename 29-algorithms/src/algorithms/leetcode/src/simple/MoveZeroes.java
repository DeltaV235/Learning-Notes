package simple;

/**
 * 移动零
 * <a href="https://leetcode.cn/problems/move-zeroes/?envType=study-plan-v2&envId=top-100-liked"/>
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int endOfNonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[endOfNonZero] = nums[i];
                endOfNonZero++;
            }
        }
        for (int i = endOfNonZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}

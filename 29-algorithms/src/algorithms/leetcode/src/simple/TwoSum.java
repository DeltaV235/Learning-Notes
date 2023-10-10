package simple;


import java.util.*;

/**
 * 两数之和
 */
public class TwoSum {
    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        int[] nums = new int[]{3, 3};
        System.out.println(Arrays.toString(twoSum.twoSum(nums, 6)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, List<Integer>> map = convertToMap(nums);
        int[] output = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int second = target - nums[i];
            if (map.containsKey(second)) {
                output[0] = i;
                if (map.get(second).size() > 1) {
                    output[1] = map.get(second).get(1);
                } else {
                    if (map.get(second).get(0) == i)
                        continue;
                    output[1] = map.get(second).get(0);
                }
                break;
            }
        }
        return output;
    }

    private Map<Integer, List<Integer>> convertToMap(int[] nums) {
        Map<Integer, List<Integer>> retVal = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (retVal.containsKey(nums[i])) {
                retVal.get(nums[i]).add(i);
            } else {
                ArrayList<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                retVal.put(nums[i], indexList);
            }
        }
        return retVal;
    }
}


package medium;

import java.util.HashMap;
import java.util.Map;

public class FindGoodParis {
    public long numberOfPairs(int[] nums1, int[] nums2, int k) {
        long ans = 0;
        int n = nums1.length, m = nums2.length;
        int num1Max = 0;
        Map<Integer, Integer> num1Map = new HashMap(n);
        Map<Integer, Integer> num2Map = new HashMap(m);

        for (int num : nums1) {
            Integer value = num1Map.get(num);
            if (value == null)
                num1Map.put(num, 1);
            else
                num1Map.put(num, value + 1);
            if (num > num1Max)
                num1Max = num;
        }
        for (int num : nums2) {
            Integer value = num2Map.get(num);
            if (value == null)
                num2Map.put(num, 1);
            else
                num2Map.put(num, value + 1);
        }

        for (int num : num2Map.keySet()) {
            int p = 1;
            int target = num * k * p;
            while (target <= num1Max) {
                Integer value = num1Map.get(target);
                if (value != null)
                    ans += (long) value * num2Map.get(num);
                ++p;
                target = num * k * p;
            }
        }
        return ans;
    }
}

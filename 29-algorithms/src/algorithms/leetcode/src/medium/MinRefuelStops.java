package medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MinRefuelStops {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        long totalFuel = 0;
        int n = stations.length, ans = 0, pos = 0;
        for (int i = 0; i < n; ++i)
            totalFuel += stations[i][1];
        if (totalFuel + startFuel < target)
            return -1;
        int nextStatInd = -1;
        while (pos + startFuel < target) {
            int maxScore = Integer.MIN_VALUE;
            int i = nextStatInd + 1;
            nextStatInd = -1;
            while (i < n) {
                int statPos = stations[i][0];
                int fuel = stations[i][1];
                if (startFuel < statPos)
                    break;
                int score = fuel + statPos - target;
                if (score >= maxScore) {
                    maxScore = score;
                    nextStatInd = i;
                }
                ++i;
            }
            if (nextStatInd == -1)
                return -1;
            startFuel = startFuel - (stations[nextStatInd][0] - pos) + stations[nextStatInd][1];
            pos = stations[nextStatInd][0];
            ++ans;
        }
        return ans;
    }

    public static void main(String[] args) {
        int ans = new MinRefuelStops().minRefuelStops(100, 10, new int[][]{
                new int[]{10, 60},
                new int[]{20, 30},
                new int[]{30, 30},
                new int[]{60, 40}
        });
        System.out.println("ans = " + ans);

        Set<Map.Entry<Object, Object>> entries = new HashMap<>().entrySet();
        for (Map.Entry<Object, Object> entry : entries) {
        }
    }
}

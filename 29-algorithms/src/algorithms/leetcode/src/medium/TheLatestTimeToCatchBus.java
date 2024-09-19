package medium;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/the-latest-time-to-catch-a-bus/description/?envType=daily-question&envId=2024-09-18">leetcode</a>
 * <p>
 * Not Completed
 */
public class TheLatestTimeToCatchBus {
    public static void main(String[] args) {
        int[] buses = {20, 30, 10};
        int[] passengers = {19, 13, 26, 4, 25, 11, 21};
        int capacity = 2;
        new TheLatestTimeToCatchBus().latestTimeCatchTheBus(buses, passengers, capacity);
    }

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);

        int passIndex = 0;
        int busIndex = 0;

        int n = buses.length;
        int m = passengers.length;
        int lastBusLoaded = 0;

        while (busIndex < n) {
            if (passIndex == m) {
                break;
            }
            int loadedPass = 0;
            int busTime = buses[busIndex];

            while (loadedPass < capacity) {
                if (passIndex < m && passengers[passIndex] <= busTime) {
                    loadedPass++;
                    passIndex++;
                } else {
                    break;
                }
            }

            busIndex++;
            lastBusLoaded = loadedPass;
        }

        if (passIndex <= m && busIndex == n && lastBusLoaded == capacity) {
            passIndex--;
            if (passIndex == 0)
                return passengers[passIndex] - 1;
            while (passengers[passIndex] - passengers[passIndex - 1] == 1) {
                passIndex--;
                if (passIndex < 1)
                    break;
            }
            return passengers[passIndex] - 1;
        } else {
            if (busIndex == 0)
                return buses[0];

            if (buses[n - 1] == passengers[passIndex - 1]) {
                while (buses[n - 1] == passengers[passIndex - 1]) {
                    passIndex--;
                }
                return passengers[passIndex] - 1;
            } else {
                return buses[n - 1];
            }

        }
    }
}

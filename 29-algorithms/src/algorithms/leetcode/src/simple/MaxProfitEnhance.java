package simple;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 买卖股票的最佳时机
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/"/>
 */
public class MaxProfitEnhance {
    public int maxProfit(int[] prices) {
        ArrayList<Price> list = new ArrayList<>();
        for (int i = 0; i < prices.length; i++) {
            list.add(new Price(i, prices[i]));
        }

        list.sort(Comparator.comparingInt(o -> o.value));
        ArrayList<Price> revert = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            revert.add(list.get(i));
        }

        int max = 0;

        for (Price price : list) {
            for (Price rev : revert) {
                if (rev.index > price.index) {
                    max = Math.max(max, rev.value - price.value);
                    break;
                }
            }
            if (max == revert.get(0).value - list.get(0).value)
                break;
        }

        return max;
    }

    class Price {
        int index;
        int value;

        public Price(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}

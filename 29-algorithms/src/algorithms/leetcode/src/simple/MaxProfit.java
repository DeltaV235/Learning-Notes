package simple;

/**
 * 买卖股票的最佳时机
 * <a href="https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/"/>
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            int profit = calMaxProfit(prices, i);
            if (profit > max)
                max = profit;
        }
        return max;
    }

    public int calMaxProfit(int[] prices, int index) {
        int max = 0;
        int startValue = prices[index];
        for (int i = index + 1; i < prices.length; i++) {
            int profit = prices[i] - startValue;
            if (profit > max)
                max = profit;
        }
        return max;
    }
}

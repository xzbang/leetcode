/**
 *https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most k transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 * 好题标记
 *
 * Created by xzbang on 2015/4/14.
 */
public class BestTimetoBuyandSellStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        //当数组长度比k小时，数组中的递增序列不超过k个，所以可以直接将递增序列相加求解
        if (k >= len / 2) return quickSolve(prices);

        //用于存储i次交易前j+1个子数组序列得到的最大利润
        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            //用于存储第i-1次交易前j-1个子数组序列并且已经买入后的利润，包含最后买入之后还未卖出的代价
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                //第i次交易前j+1个子数组后序列得到的最大利润（最后一次交易已经卖出）
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                //买入后剩下的钱最多
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }

    /**
     * 连续多个递增序列算同一个递增序列，所以通过计算，可以得到一串递增递减序列
     * 当数组长度比k小时，数组中的递增序列不超过k个，所以可以直接将递增序列相加求解
     * @param prices
     * @return
     */
    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    public static void main(String[] args) {
        BestTimetoBuyandSellStockIV bestTimetoBuyandSellStockIV = new BestTimetoBuyandSellStockIV();
        int k = 4;
        int[] prices = {5,7,4,5,3,6,1,2,9,7,10};
        System.out.println(bestTimetoBuyandSellStockIV.maxProfit(k,prices));
    }
}

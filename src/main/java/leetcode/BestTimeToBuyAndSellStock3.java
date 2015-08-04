package leetcode;

/**
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *
 Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 *
 *解题思路：
 *解法1：
 * 与BestTimetoBuyandSellStockIV相同，需要存储前j个价格中每交易i之后的最大值，并有个中间变量存储买入一次之后的最大值
 * 解法2：
 * 由于本题只交易两次，所以可以用四个变量单独存储最大值，依次循环比较即可
 *
 * Created by xzbang on 2015/5/19.
 */
public class BestTimeToBuyAndSellStock3 {
    public int maxProfit(int[] prices) {
        int k =2;
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

    public int maxProfit2(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0;//单次交易的最大值
        int release2 = 0;//两次交易的最大值
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far.
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock3 bestTimetoBuyandSellStock3 = new BestTimeToBuyAndSellStock3();
        int k = 4;
        int[] prices = {5,7,4,5,3,6,1,2,9,7,10};
        System.out.println(bestTimetoBuyandSellStock3.maxProfit(prices));
    }
}

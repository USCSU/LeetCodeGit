package LeetCode;

import java.util.ArrayList;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.

 Design an algorithm to find the maximum profit. You may complete at most two transactions.

 Note:
 You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 */
public class BuyAndSellIII {
    public static int maxProfitConcise(int[] prices){
        if(prices == null || prices.length<2) return 0;
        int result = 0;
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        int min = prices[0];
        left[0] = 0;
        for(int i =1;i<prices.length;i++){
            min = Math.min(min,prices[i]);
            left[i] = Math.max(prices[i]-min,left[i-1]);
        }
        right[prices.length-1] = 0;
        int max = prices[prices.length-1];
        for(int i = prices.length-2;i>=0;i--){
            max = Math.max(max,prices[i]);
            right[i] = Math.max(right[i+1],max - prices[i]);
        }
        for(int i =0;i<right.length;i++){
            if(result < left[i]+right[i])
                result = left[i]+right[i];
        }
        for(int i =0;i<left.length;i++)
            System.out.print(left[i]+" ");
        System.out.println();
        for(int i =0;i<right.length;i++)
            System.out.print(right[i]+" ");
        return result;
    }

    public static int maxProfitK(int[] prices, int k){
        if(prices==null||prices.length<2) return 0;
        int len = prices.length;
        int[][] local= new int[len][k+1];
        int[][] global = new int[len][k+1];
        for(int i = 1;i<len;i++){
            int diff = prices[i] - prices[i-1];
            for(int j = 1;j<=k;j++){
                local[i][j] = Math.max(local[i-1][j]+diff, global[i-1][j-1]+Math.max(diff,0));
                global[i][j] = Math.max(global[i-1][j],local[i][j]);
            }
        }
        return global[len-1][k];
    }
    public static void main(String[] args){
//        int[] array = {1,5,10,2,4,6,8,3};
        int[] array = {4,2,1,3,8,6,5};
        System.out.println(maxProfitConcise(array));
        System.out.println(maxProfitK(array,2));
    }
}

package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Garvin on 10/2/2014.
 */
public class KnapSack {

    public static int ZeroOneKnapSack2DArray(int[] w, int[] v, int c){
        int n = w.length;
        int[][] dp = new int[n+1][c+1];
        for(int i =1;i<n+1;i++){
            for(int j = c;j>=0;j--){
                if(j>=w[i-1])
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-w[i-1]]+v[i-1]);
            }
            for(int[] d:dp)
            System.out.println(Arrays.toString(d));
        }
        return dp[n][c];
    }
    public static int ZeroOneKnapSack1DArray(int[] w, int[] v, int c){
        int[] dp = new int[c+1];
        int n = w.length;
        for(int i =1;i<n+1;i++){
            for(int j = c;j>=0;j--){ //descending order is essential
                if(j>=w[i-1])
                dp[j] = Math.max(dp[j],dp[j-w[i-1]]+v[i-1]);
            }
            System.out.println(Arrays.toString(dp));
        }
        return dp[c];
    }
    public static int CompleteKnapSack(int[] w, int[] v, int c){
        int[] dp = new int[c+1];
        int n = w.length;
        for(int i = 1;i<n+1;i++){
            for(int j = 0;j<=c;j++){
                if(j>=w[i-1])
                dp[j] = Math.max(dp[j],dp[j-w[i-1]]+v[i-1]);
            }
        }
        return dp[c];
    }


    public static void BachetGame(int m, int n, int[] array){
        boolean[] dp = new boolean[n+1];
        int i,j;
        for ( i = 1; i <= n; i++)
            for ( j = 0; j < m; j++){//index of ways to retrieve stones
                if (i >= array[j]  && !dp[i - array[j]]) {
                    dp[i] = true;
                    break;
                }
            for(int k =0;k<n+1;k++)
                System.out.print((dp[k]?"T":"F") +"  ");
            System.out.println(i-1+ " "+j);
//            if(j >= array.length)
//                dp[i] = false;
        }
        for(int k =0;k<n+1;k++)
            System.out.print((dp[k]?"T":"F") +" ");
        System.out.println();
        if (dp[n])
            System.out.println("Stan wins");
        else
            System.out.println("Ollie wins");

    }

    public static void main(String[] args){
        int[] w = {3,4,8,5};//weight of items;
        int[] v = {3,4,8,5}; // value of items;
        int c = 10;//capacity of knapSack;
        System.out.println(ZeroOneKnapSack2DArray(w, v, c));
        System.out.println(ZeroOneKnapSack1DArray(w, v, c));
        System.out.println(CompleteKnapSack(w, v, c));
//        int[] w1 = {3,3,3,4,4,5,5};
//        int[] v1 = {4,4,4,5,5,6,6};
//        System.out.println(ZeroOneKnapSack1DArray(w1,v1,c));
//            int[] array = {1,3,8};
//            int[] array = {3};
//        BachetGame(3,10,array);
//        BachetGame(3,10,array);
//        BachetGame(3,21,array);
//        BachetGame(3,22,array);
//        BachetGame(3,23,array);
    }
}

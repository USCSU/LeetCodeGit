package LeetCode2015;

import java.util.Arrays;

/**
 * Created by Trace_route on 7/10/15.
 */
public class MaximalSquare {
    /* analyze:
       square : row == col
       dp[i,j] = max len of rectange
       dp[i,j] = {
        matrix[i,j] == 0? 0; 因为不能延长任何长度
        matrix[i,j] == 1? min(dp[i,j-1], dp[i-1,j-1] , dp[i-1,j] )+1;
        因为只有保证三个方向的值之后才能确定是否可以组成正方形
       }
    * */
    public static int maximalSquare(char[][] matrix) {
        //error checking
        if(matrix == null || matrix.length == 0) return 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        int max = 0;
        for(int i=0;i<col;i++) {
            dp[0][i] = matrix[0][i] - '0';
            max = Math.max(max,dp[0][i]);
        }
        for(int i =0;i<row;i++){
            dp[i][0] = matrix[i][0] - '0';
            max = Math.max(max,dp[i][0]);
        }
        for(int i =1;i<row;i++){
            for(int j =1;j<col;j++){
                if(matrix[i][j] == '0') dp[i][j] = 0;
                else{
                    int min = Math.min(dp[i-1][j-1], dp[i-1][j]);
                    min = Math.min(min,dp[i][j-1]);
                    dp[i][j] = min+1;
                }
                max = Math.max(dp[i][j],max);
            }
        }
        for(int[] d: dp)
        System.out.println(Arrays.toString(d));

        return max*max;
    }
    public static void main(String[] args){
        char[][] matrix = {{'1', '0', '1', '0', '0'},
                           {'1', '0', '1', '1', '1'},
                           {'1', '1', '1', '1', '1'},
                           {'1', '0', '0', '1', '0'}};
        char[][] dp = {{'1'}};
        System.out.println(maximalSquare(matrix));
    }
}

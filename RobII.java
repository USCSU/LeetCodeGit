package LeetCode2015;

import java.util.Arrays;

/**
 * Created by Trace_route on 7/18/15.
 */
public class RobII {
    public static void main(String[] args){
        int[] array = {50,1,1,50};
        System.out.println(rob(array));
    }
    public static int rob(int[] nums) {
        if(nums == null || nums.length<1) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(robHelper(Arrays.copyOfRange(nums,0,nums.length-1)),
                        robHelper(Arrays.copyOfRange(nums,1,nums.length))
                );
    }
    public static int robHelper(int[] nums){
        int[] dp = new int[nums.length+1];
        dp[0] = 0; dp[1] = nums[0];
        for(int i =2;i<dp.length;i++){
            dp[i] = Math.max(dp[i-2]+nums[i-1], dp[i-1]);
        }
        System.out.println(Arrays.toString(dp));
        return dp[nums.length];

        //---

    }
}

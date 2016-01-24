package LeetCode2015;

import sun.plugin.javascript.navig.Array;

import java.util.Arrays;

/**
 * Created by Trace_route on 7/16/15.
 */
public class SelfProduct {
    public static int[] productExceptSelf(int[] nums) {
        if(nums == null || nums.length ==0) return nums;

        int[] ret = new int[nums.length];
        Arrays.fill(ret,1);
        int left =1, right =1;
        for(int i =0, j= ret.length-1;i<ret.length&&j>=0;i++,j--){
            ret[i]*=left;
            ret[j]*=right;
            left*=nums[i];
            right*=nums[j];
        }
        return ret;

    }
    public  static void main(String[] args){
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(nums)));
    }
}

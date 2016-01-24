package LeetCode2015;

/**
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a subarray of which the sum ≥ s.
 *
 * If there isn't one, return 0 instead.
 *
 *
 *  For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinimumSubArray {
    public static int minSubArrayLen(int s, int[] nums) {
        //error cehcking
        if(nums == null || nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int start= 0, end = 0, sum =0;
        while(end < nums.length){
                sum+=nums[end];
            while(start<nums.length && sum>=s){
                min = Math.min(min, end-start+1);
                sum-=nums[start++];
            }
            end++;
        }
        return min == Integer.MAX_VALUE?0:min;

    }
    public static void main(String[] args ){
        int[] array = {2,3,1,2,4,3};
        int sum = 7;
        System.out.println(minSubArrayLen(sum,array));
    }


}

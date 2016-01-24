package LeetCode2015;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Trace_route on 7/2/15.
 */
public class MajorityNumberII {
    public static List<Integer> majorityII(int[] nums){

        int val1 = 0, val2 =0, counter1=0, counter2 =0;
        for(int i =0;i<nums.length;i++){
            if(counter1==0){
                counter1=1;
                val1 = nums[i];
            }else if(val1 == nums[i]){//order here is very careful here
                counter1++;
            }else if(counter2 ==0){
                counter2 = 1;
                val2 = nums[i];
            }else if(val2 == nums[i])
                counter2++;
            else{
                counter1--;
                counter2--;
            }
        }

        return collector(nums,val1,val2,counter1,counter2);
    }






    private static List<Integer> collector( int[] nums, int val1, int val2, int counter1, int counter2){
        List<Integer> ret = new LinkedList<Integer>();
        int cnt1 = 0, cnt2 =0;
        for(int num:nums){
            if(counter1>0 && val1 == num)
                cnt1 ++;
            else if(counter2>0 && val2 == num)
                cnt2 ++;
        }
        if(cnt1>nums.length/3) ret.add(val1);
        if(cnt2>nums.length/3) ret.add(val2);
        return ret;
    }



    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        int first_val = 0, second_val = 0;
        int first_count = 0, second_count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (first_count == 0) {
                first_val = nums[i];
                first_count = 1;
            } else if (nums[i] == first_val) {
                first_count++;
            } else if (second_count == 0) {
                second_val = nums[i];
                second_count = 1;
            } else if (nums[i] == second_val) {
                second_count++;
            } else {
                first_count--;
                second_count--;
            }
        }
        return collector(nums,first_val,second_val,first_count,second_count);
//        checkMajorityHelper(res, nums, first_count, first_val, second_count,
//                second_val);
//        return res;
    }

    private static void checkMajorityHelper(List<Integer> res, int[] nums,
                                            int first_count, int first_val, int second_count, int second_val) {
        int count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (first_count > 0 && nums[i] == first_val) {
                count1++;
            } else if (second_count > 0 && nums[i] == second_val) {
                count2++;
            }
        }
        if (count1 > len / 3) {
            res.add(first_val);
        }
        if (count2 > len / 3) {
            res.add(second_val);
        }
    }
    public static void main(String[] args){
//        int[] array = {1,1,1,1,2,2,2,3};
        int[] array = {2,2,3};
//        int[] array = {-1,1,1,1,2,1};
        System.out.println(majorityII(array));
        System.out.println(majorityElement(array));
    }
}

package LeetCode2015;

/**
 * Created by Trace_route on 7/2/15.
 */
public class MajorityNumber {
    public static int majorityElement(int[] nums) {
        int target = nums[0], counter = 1;
        for(int i =1;i<nums.length;i++){
            if(nums[i] == target)
                counter++;
            else counter--;
            if(counter ==0){
                target = nums[i];
                counter = 1;
            }
        }
        counter = 0;
        for(int i :nums)
            if(i == target) counter++;
        return counter>nums.length/2?target:-1;
    }
    public static void main(String[] args){
        int[] nums = {1,1,1,1,2,2,2,2,2,3};
        System.out.println(majorityElement(nums));
    }
}

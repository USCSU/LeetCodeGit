package LeetCode2015;


import java.util.LinkedList;
import java.util.List;

public class SummaryRange {
    public static List<String> summaryRanges(int[] nums) {
        List<String> ret =new LinkedList<String>();
        int start = 0;
        for(int i =1;i<nums.length;i++){
            if(nums[i] - nums[i-1] != 1){
                if(i-1==start)
                    ret.add(nums[start]+"");
                else
                    ret.add(nums[start]+"->"+nums[(i-1)]);
                start = i;
            }
        }
        if(start<nums.length){
                if(start == nums.length-1)
                    ret.add(nums[start]+"");
                else
                    ret.add(nums[start]+"-->"+nums[nums.length-1]);

        }
        return ret;
    }
    public static void main(String[] args){
        int[] nums = {0,1,2,4,5,7};
        System.out.println(summaryRanges(nums));
    }
}

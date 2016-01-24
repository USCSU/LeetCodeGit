package LeetCode;

import java.util.Arrays;

/**
 * Created by Garvin on 9/4/2014.
 */
public class ThreeSumClosest {
    public static int threeSumClosest(int[] num, int target) {
        int res = 0;
        int min = Integer.MAX_VALUE;
        Arrays.sort(num);
        for(int i =0;i<num.length;i++){
            int low = i+1, high = num.length -1;
            while(low<high){
                int sum = num[i]+num[low]+num[high];
                if(sum == target){
                    return target;
                }else if(sum > target){
                    if(min > Math.abs(sum - target)){
                        min = Math.abs(sum - target);
                        res = sum;
                    }
                    high--;
                }else{
                    if(min > Math.abs(sum - target)){
                        min = Math.abs(sum - target);
                        res = sum;
                    }
                    low++;
                }
            }
        }
        return res;
    }

    public static int threeSumClosest1(int[] numbers ,int target) {
        // write your code here
        if(numbers == null || numbers.length ==0) return 0;
        int n = numbers.length;
        Arrays.sort(numbers);
        int min = Integer.MAX_VALUE;
        int ret = 0;
        for(int i =0;i<n;i++){
            for(int low = i+1,high=n-1;low<high;){
                int sum = numbers[i]+numbers[low]+numbers[high];
                if(sum == target) return sum;
                else if(sum>target){
                    high--;
                    if(min > Math.abs(sum-target)){
                        min = Math.abs(sum-target);
                        ret = sum;
                    }
                }else{
                    low++;
                    if(min > Math.abs(sum-target)){
                        min = Math.abs(sum-target);
                        ret= sum;
                    }
                }
            }
        }
        return ret;
    }
    public static void main(String[] args){
        int[] num = {2,7,11,15};
        System.out.println(threeSumClosest1(num,3));
        System.out.println(threeSumClosest(num,3));
    }
}

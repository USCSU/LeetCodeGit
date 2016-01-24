package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Garvin on 8/30/2014.
 */
public class threeSum {
    // get three nums if the sum of them is 0.
    private static ArrayList<ArrayList<Integer>> threeSumExe(int[] num){
        if(num == null) return null;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if(num.length <3) return res;
        Arrays.sort(num);
        HashSet<ArrayList<Integer>> set = new HashSet<ArrayList<Integer>>();
        for(int i =0;i<num.length;i++){
            set.addAll(twoSum(num,i+1,num.length-1,0-num[i]));
        }
        res.addAll(set);
        return res;
    }
    private  static ArrayList<ArrayList<Integer>> twoSum(int[] num, int low,int high, int target){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        while(low<high){
            if(num[low]+num[high] == target){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(0-target);
                temp.add(num[low]);
                temp.add(num[high]);
                res.add(temp);
                low++;
                high--;
            }else if(num[low]+num[high] < target){
                low++;
            }else{
                high--;
            }
        }
        return res;
    }
    public static void main(String[] args){
            int[] S = {-1,0, 1, 2, -1, -4};
            System.out.println(threeSumExe(S));
    }
}

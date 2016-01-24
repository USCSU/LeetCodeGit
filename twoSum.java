package LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Garvin on 8/30/2014.
 */
//twosum problem assume all the numbers are distinct.
public class twoSum {
    //O(nlogn) algorithm, const space
    public int[] twoSumExe(int[] num, int k){
        int[] res = new int[2]; // return two indexes
        Arrays.sort(num); // O(nlogn)
        int low = 0, high = num.length -1;
        while(low < high){
            if(num[low] + num[high] == k){
                res[0] = low+1;
                res[1] = high+1;
                return res;
            }else if(num[low] + num[high] < k){
                low++;
            }else{
                high --;
            }
        }
        return res;
    }

    //O(n) solution, with O(N) space
    public int[] twoSumExe2(int[] num, int k){
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i =0;i<num.length;i++){
            if(map.containsKey(num[i])){
                res[0] = map.get(num[i])+1;
                res[1] = i+1;
                return res;
            }else{
                map.put(k-num[i],i);
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] num = {2, 7, 11, 15};
        twoSum t = new twoSum();
//        int[] res = t.twoSumExe(num, 9);
        int[] res = t.twoSumExe2(num,9);
        System.out.println(res[0] + "-->"+ num[res[0]-1]);
        System.out.println(res[1] + "-->"+ num[res[1]-1]);
    }
}

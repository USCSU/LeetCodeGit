package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Garvin on 10/13/2014.
 */
public class LongestIncreaseSequence {
    public static int getSequence(int[] num){
        HashSet<Integer> set = new HashSet<Integer>();
        int max = 0;
        if(num == null) return 0;
        for(int x:num)
            set.add(x);
        for(int x:num){
            int low = x-1;
            int high = x+1;
            int counter = 1;
            while(set.contains(low)){
                counter++;
                set.remove(low);
                low--;
            }
            while(set.contains(high)){
                counter++;
                set.remove(high);
                high++;
            }
            max = Math.max(counter,max);
        }
        return max;
    }
    public static void main(String[] args){
        int[] array = {100, 4, 200, 1, 3, 2};
        System.out.println(getSequence(array));
    }
}

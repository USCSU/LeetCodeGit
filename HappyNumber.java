package LeetCode2015;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Trace_route on 7/6/15.
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        return helper((long)n,new HashSet<Long>());
    }
    public static boolean helper(long n,HashSet<Long> set){
        //base
        if(set.contains(n) || n > Integer.MAX_VALUE) return false;
        set.add(n);
        Long num = caculate(n);
        System.out.println(num);
        if(num == 1) return true;
        return helper(num,set);
    }
    public static Long caculate(Long n ){
        Long ret = (long)0;
        while(n!=0){
            ret += (long)Math.pow(n%10,2);
            n/=10;
        }
        return ret;
    }
    public static void main(String[] args){
        System.out.println(isHappy(119));
        System.out.println(isHappyIterative(19));
    }

    public static boolean isHappyIterative(int n){
        HashSet<Long> set = new HashSet<Long>();
        long num = n;
        while(num!=1){
            set.add((long)num);
            num  = caculate((long)num);
            if(num > Integer.MAX_VALUE || set.contains(num)) return false;

        }
        return true;
    }
}

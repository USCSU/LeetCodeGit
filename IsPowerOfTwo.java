package LeetCode2015;

/**
 * Created by Trace_route on 7/10/15.
 */
public class IsPowerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        if(n == 1) return true;
        if(n<1) return false;
        int tag = n&1;
        int count =0;
        for(;n!=0;count++) n = n&n-1;
        return tag ==0&&count == 1;
    }
    public static void main(String[] args){
        System.out.println(isPowerOfTwo(4));
    }
}

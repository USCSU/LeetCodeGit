package LeetCode;

public class ReverseInteger {
    //spoiler: 1. positive or negetive. 2. overflow
    public static int reverseInteger(int x){
        int helper = Math.abs(x);
        int res = 0;
        boolean neg = x<0?true:false;

        while(helper>0){
            res=res*10+helper%10;
            helper/=10;
        }
        if(neg) res = -res;
        if(res>Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if(res<Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return res;
    }
    public static void main(String[] args){
        System.out.println(reverseInteger(-123));
    }
}

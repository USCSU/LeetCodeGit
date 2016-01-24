package LeetCode;

/**
 * Created by Garvin on 10/19/2014.
 */
public class pow {
    public static double pow(double x, int n){
       System.out.println(x + " --> "+ n);
        if(n==0) return 1;
        double half = pow(x,n/2);
        if((n&1) ==0)
            return half*half;
        else if(n>0)
            return half*half*x;
        else
            return half*half/x;
    }
    public static float pow1(long x, long n){
        if(n==0) return 1;
        float half = pow1(x,n/2);

        if(half>Long.MAX_VALUE) throw new IndexOutOfBoundsException();
        if((n&1) ==0)
            return half*half;
        else if(n>0)
            return half*half*x;
        else
            return half*half/x;
    }
    public static void main(String[] agrs){
        System.out.println(pow1(2,5));
    }
}

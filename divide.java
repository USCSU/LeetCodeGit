package LeetCode;

/**
 * Created by Trace_route on 12/15/14.
 */
public class divide {
    public static int divide(int dividend, int divisor) {
        if(divisor == 0) return Integer.MAX_VALUE;
        long  a = Math.abs((long)dividend);
        long  b = Math.abs((long)divisor);
        int res = 0;
        while(a>=b){
            long t = b;
            for(long i =1;a>=t;i<<=1,t<<=1){
                a-=t;
                res+=i;
                System.out.println(a+" "+ t);
            }
        }
        return ((dividend<0)^(divisor<0))? -res:res;
    }
    public static int div(int a, int b){
        if(b ==0) return Integer.MAX_VALUE;
        long d1 = Math.abs((long)a);
        long d2 = Math.abs((long)b);
        int ret = 0;
        while (d1>=d2){
            long d2Temp = d2;
            for(int i =1;d1>d2Temp;i<<=1,d2Temp<<=1){
                ret+=i;
                d1-=d2Temp;
            }
        }
        return ret;
    }

    public static void main(String[] args){
        System.out.println(divide(101,2));
        System.out.println(div(101,2));
    }
}

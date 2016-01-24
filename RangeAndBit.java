package LeetCode2015;

/**
 * Created by Trace_route on 7/10/15.
 */
public class RangeAndBit {
    //m<
    // get left common part
    public static int rangeBitwiseAnd(int m, int n) {
        if(m == n) return m;
        int diff = m^n;
        int index = (int)(Math.log(diff)/Math.log(2))+1;
        m = m >>index;
        return m<<index;

    }
    public static int rangeBitwiseAnd1(int m, int n) {
        return (n > m) ? (rangeBitwiseAnd1(m/2, n/2) << 1) : m;
    }
    public static int rangeBitwiseAndII(int m, int n){
        int count = 0;
        while(m != n){
            m >>= 1;
            n >>= 1;
            count++;
        }
        return m<<=count;
    }
    public static void main(String[] args){
        for(int i =0;i<50;i++){
            for(int j =0;j<50;j++){
        System.out.println(rangeBitwiseAnd(i,j));
//        System.out.println(rangeBitwiseAnd1(i,j));
        System.out.println(rangeBitwiseAndII(i,j));
                System.out.println("----");

            }
        }
//        System.out.print(~0);
    }
}

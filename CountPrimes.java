package LeetCode2015;

import java.util.BitSet;

/**
 * Created by Trace_route on 7/10/15.
 */
public class CountPrimes {
    public static int countPrimes(int n) {
        if(n <2) return 0;
        int counter = 0;
        BitSet bitSet = new BitSet(n);
        bitSet.set(2,n,true);

        for(int i = 2;i*i<n;i++){
            if(bitSet.get(i)){
                for(int j = i+i;j<n;j+=i){
                    bitSet.set(j,false);
                }
            }
        }
        for(int i =2;i<n;i++)
            if(bitSet.get(i))
             counter++;
       return counter;
    }
    public static void main(String[] args){
        int n = 100;
        System.out.println(countPrimes(n));
    }
}

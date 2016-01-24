package LeetCode;

/**
 * Created by Garvin on 9/8/2014.
 */
public class SingleNumber {
    public int getSingleNum(int[] A){
        int result = 0;
        for(int i:A)
        result^=i;
        return result;
    }
}

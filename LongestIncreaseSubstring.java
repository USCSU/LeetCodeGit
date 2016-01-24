package LeetCode;

/**
 * Created by Garvin on 10/13/2014.
 */
public class LongestIncreaseSubstring {
    public static String LongestIncreasingSubstring(String s){
        if(s == null||s.length()<2) return s;
        int low = 0,high=low+1;
        int markLow = 0;
        int markHigh = markLow+1;
        for(int i =1;i<s.length();i++){
            if(s.charAt(i) - s.charAt(i-1) == 1){
                high++;
                if(high - low > markHigh -markLow){
                    markHigh = high;
                    markLow = low;
                }
            }else{
                low = i;high=i+1;
            }
            System.out.println(low+" -- "+high);
        }
        return s.substring(markLow,markHigh);

    }
}

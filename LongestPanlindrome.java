package LeetCode;

/**
 * Created by Garvin on 9/8/2014.
 */
public class LongestPanlindrome {
    public static String getLongerString(String s1, String s2){
        return s1.length()>s2.length()?s1:s2;
    }
    public static String panlindrome(String s, int low, int high){
        while(low>=0 && high<s.length() && s.charAt(low)==s.charAt(high)){
            low--;
            high++;
        }
        return s.substring(low+1,high);
    }
    public  static String longestPalindromeStr(String s) {
        String max = "";
        for(int i =0;i<s.length();i++){
            max = getLongerString(max,getLongerString(panlindrome(s,i,i+1), panlindrome(s,i-1,i+1)));
        }
        return max;
    }
    public static void main(String[] args){
        System.out.println(longestPalindromeStr("abcbcaabbbbbbb"));
    }
}

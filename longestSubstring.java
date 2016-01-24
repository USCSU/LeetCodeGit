package LeetCode;
import java.util.HashMap;

/**
 * Created by Garvin on 9/8/2014.
 */
public class longestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null||s.isEmpty()) return 0;
        int max = Integer.MIN_VALUE;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int start = 0;
        for(int i =0;i<s.length();i++){
            if(map.containsKey(s.charAt(i)) && map.get(s.charAt(i))>=start){
                start = map.get(s.charAt(i))+1;
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-start+1);
        }
        return max;
    }
    public static void main(String[] args){
        System.out.println(lengthOfLongestSubstring("abcccb"));
    }
}

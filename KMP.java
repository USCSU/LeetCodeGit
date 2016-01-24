package LeetCode;

/**
 * Created by Garvin on 9/9/2014.
 */
public class KMP {
    //similar solution as generate partial matching array with time complexity of O(m) and space complexity of O(m)
    public static boolean contains(String s, String pattern) {
        int[] next = generateNext(pattern);
        for (int i = 0, j = 0; i < s.length(); i++) {
            //匹配发生错误时，在 next 数组中向前迭代找到匹配的结果。这是整个算法的核心位置！
            //When non-matching, move index back to a working index
            while (j > 0 && s.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }

            //如果相等，pattern 中的索引往后移动一位
            //if characters are matched, index of pattern should move forward
            if (s.charAt(i) == pattern.charAt(j))
                j++;
            //pattern数组所有位置均连续的匹配完成
            //if all matched, j will be at the end of string
            if (j == pattern.length()) {
//                System.out.println(i - pattern.length() + 1);
                return true;
            }

        }
        return false;
    }

    //build partial match table: time complexity :O(n) Space complexity O(n)
    public static int[] generateNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = 0; //set first val of next array as 0 //第一个元素的 next 值为 0。
        for (int i = 1, j = 0; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = next[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
    public static boolean isRightRotation(String s, String p){
        //error checking
        if(s == null || p == null) return false;
        if(s.length()!=p.length()) return false;
        if(p.length() ==0) return true;//assume empty string is any rotation of empty string
        return contains(s+s,p);

    }
    public static void main(String[] args) {
        String str = "ABC ABCDAB ABCDABCDABDE";
        String pattern = "ABCDABD";
        System.out.println(contains("ABACABABC","ABAB"));
        int[] res = generateNext(pattern);
//        System.out.println(isRightRotation(str, pattern));
//        System.out.println(isRightRotation1(str, pattern));
//        System.out.println(isRightRotation2(str, pattern));
    }

    //Time complexity Analysis:
    //1. When generating next array, linear time running is essential. O(n), where n is length of pattern string.
    //2. Kmp matching will need O(m+n), where m is length of source str, n is length of pattern string; And m=2*n;So
    //The final time complexity is O(2n+n), is O(n).

    //Space complexity Analysis
    //1.only next array is used, so the total would be O(n).
    //Assumption: Assume, implement of + operator is running in O(n) in time and space with java1.7. If not, it can be
    //replaced with stringBuilder. And append every new character every time. In this way, the time complexity is guaranteed
    //to be O(n)

    // Brute force solution: Time complexity :O(n^2) space complexity: O(1)
    //It's a trade off depending on needs.
    public static boolean isRightRotation1(String s, String p){
    //error checking
        if(s == null || p == null) return false;
        if(s.length()!=p.length()) return false;
        if(p.length() ==0) return true;//assume empty string is any rotation of empty string
        return (s+s).indexOf(p)!=-1;

    }

    public static boolean isRightRotation2(String s, String p){
        if(s == null || p == null) return false;
        if(s.length()!=p.length()) return false;
        if(p.length() == 0) return true;
        return strStr(s+s,p);
    }
    public static boolean strStr(String haystack, String needle) {
        // Note: The Solution object is instantiated only once and is reused by each test case.

        if(haystack.length() == 0)
            return true;
        int i =0;
        for(;i < haystack.length()-needle.length();i++){
            if( haystack.substring(i,i+needle.length()).equals(needle))
                break;

        }
        if(i==haystack.length())
            return false;
        else
            return true;
    }

}

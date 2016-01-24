package LeetCode2015;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Trace_route on 7/19/15.
 */
public class MiniPalindromeString {

    public static String shortestPalindorme(String s){
        String s1 =  s;
        String s2 = reverse(s1);
        int k =0, len=s1.length();
        while(!s.equals(s2)){
            s = s.substring(0,len-k);
            s2 = s2.substring(0,len-k);
            k++;
        }
        //s is palindrome
        String nonPalin = s1.substring(s.length());
        String reverseNonPalin = reverse(nonPalin);
         return reverseNonPalin+s+nonPalin;

    }
    public static String reverse(String s){
        return new StringBuffer(s).reverse().toString();
    }
    public static void main(String[] args){
        System.out.println(Integer.MAX_VALUE);
        System.out.println((1<<31)-1);

//        System.out.println(shortestPalindrome("aacecaa"));
//        System.out.println(shortestPalindrome("ABCCBAD"));

//        System.out.println(shortestPalindrome("abcs"));
//        System.out.println(shortestPalindrome("baac"));
    }
    public static String shortestPalindrome(String s) {
        boolean[] dp = makePalindrome(s);
        int[] pi = KMPpreprocess(s);
        int palinIndex=Math.min(pi[pi.length-1],s.length()-1);
//        System.out.println(palinIndex);
        String nonPalin=s.substring(palinIndex+1);
        String Palin=s.substring(0,palinIndex+1);
//        System.out.println(nonPalin+"-->"+Palin);



        StringBuffer helper = new StringBuffer();
        for(int i =s.length()-1;i>=0;i--){
            //find out if palindrome
            if(dp[i]) break;
            helper.append(s.charAt(i));
        }
        return helper.append(s).toString();
    }


    public static int[] KMPpreprocess(String S){
        int[] pi=new int[S.length()];
        //init start of pi
        pi[0]=-1;
        //get each index in pi, i is the index being processed
        for (int i=1;i<pi.length;i++){
            int j=pi[i-1];
            while (j!=-1 && S.charAt(j+1)!=S.charAt(i)) {j=pi[j];}
            if (j==-1){
                if (S.charAt(0)==S.charAt(i)) pi[i]=0;
                else pi[i]=-1;
            }
            else if (S.charAt(j+1)==S.charAt(i)){
                pi[i]=j+1;
            }

        }
System.out.println(Arrays.toString(pi));
        return pi;
    }
    public static boolean[] makePalindrome(String str){
        boolean[] dp1 = new boolean[str.length()];
        boolean[] dp2 = new boolean[str.length()];
        for(int row = str.length()-1;row>=0;row--){
            for(int col = row;col<str.length();col++){
                char c1 = str.charAt(row);
                char c2 = str.charAt(col);
                if(row==col) dp1[col] = true;
                else dp1[col] = c1==c2&&(col-row<2||dp2[col-1]);
            }
//        System.out.println(Arrays.toString(dp1));
            boolean[] temp = dp1;
            dp1 = dp2;
            dp2=temp;
        }
        return dp2;
    }
//    public static boolean[][] makePalindrome(String str){
//        boolean[][] dp = new boolean[str.length()][str.length()];
//        for(int row = str.length()-1;row>=0;row--){
//            for(int col = row;col<str.length();col++){
//                char c1 = str.charAt(row);
//                char c2 = str.charAt(col);
//                if(row == col) {
//                    dp[row][col] = true;
//
//                }
//                else{
//                    dp[row][col] = (c1==c2)&&(col-row<2 || dp[row+1][col-1]);
//                }
//            }
//        }
//        for(boolean[] d:dp)
//        System.out.println(Arrays.toString(d));
//        return dp;
//    }
}

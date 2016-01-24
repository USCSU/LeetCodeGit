package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Garvin on 10/18/2014.
 */
public class WordBreakII {
    public static ArrayList<String> wordBreakBruteForce(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.isEmpty()||dict.isEmpty()) return res;
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i =1;i<dp.length;i++){
            for(int k =0;k<i;k++){
                if(dp[k] && dict.contains(s.substring(k,i))) dp[i] = true;
            }
        }
        wordBreakBruteForceHelper(s,0,"",dict,dp,res);
        return res;
    }
    public static void wordBreakBruteForceHelper(String s,int index, String runner, Set<String> dict, boolean[] dp, ArrayList<String> res){
        if(index>=s.length()){
            res.add(runner.trim());
            return;
        }
        for(int i = index;i<s.length();i++){
            if(dict.contains(s.substring(index,i+1))&&dp[i+1]){
                wordBreakBruteForceHelper(s,i+1,runner +" "+s.substring(index,i+1), dict, dp,res);
            }
        }
    }


    public static ArrayList<String> wordBreakTest(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.isEmpty()) return res;
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1;i<dp.length;i++){
            for(int k =0;k<i;k++){
                if(dp[k]&&dict.contains(s.substring(k,i))) dp[i] = true;
            }
        }
        for(boolean i : dp)
            System.out.print(i+" ");
        helper(s,"",dict,dp,res,0);
        return res;
    }
    public static void helper(String s, String runner, Set<String> dict, boolean[] dp,ArrayList<String> res, int index){
        if(index>=s.length()){
            res.add(runner.trim());
            return ;
        }
        for(int i =index;i<s.length();i++){
            if(dict.contains(s.substring(index,i+1)) && dp[i+1]){
                String temp = runner +" "+s.substring(index,i+1);
                helper(s,temp,dict,dp,res,i+1);
            }
        }
    }
    public static void main(String[] args){
        String s = "catsanddog";
        Set<String> dict= new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
       System.out.println( wordBreakTest(s,dict));
        System.out.println( wordBreakBruteForce(s,dict));
    }
}

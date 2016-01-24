package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class wordBreakI {
    //wordBreak I: dp to judge if works
    public static boolean wordBreakDp(String s, Set<String> dict){
        if(s == null || s.length() ==0||dict.isEmpty()) return false;
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        for(int i =1;i<=s.length();i++){
            for(int k =0;k<i;k++){
                if(dp[k]&&dict.contains(s.substring(k,i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        for(boolean i:dp)
        System.out.print(i+ " ");
        return dp[s.length()];
    }
//    public static boolean wordBreakDpII(String s, Set<String> dict){
//        if(s==null || s.isEmpty() || dict.isEmpty()) return false;
//        boolean dp[] = new boolean[s.length()+1];
//        dp[s.length()] = true;
//        for(int i = s.length()-1;i>=0;i--){
//            for(int j = s.length();j>=i;j--){
//                if(dp[j] && dict.contains(s.substring(i,j)))
//
//            }
//        }
//    }
    //brute force recursive to get all combinations
    public static ArrayList<String> wordBreakExceedTime(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        wordBreakExceedTimeHelper(s,dict,res,"");
        return res;
    }
    public static void wordBreakExceedTimeHelper(String s, Set<String> dict, ArrayList<String> res, String temp){
        if(s.isEmpty()){
            res.add(temp);
            return;
        }
        for(int i = 1;i<=s.length();i++){
            if(dict.contains(s.substring(0,i))){
                String t = temp + " "+ s.substring(0,i);
                wordBreakExceedTimeHelper(s.substring(i),dict,res,t);
            }
        }
    }
    //Alternative recursive way to solve repeated problem
    public static ArrayList<String> wordBreakSaveTime(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.isEmpty()||dict.isEmpty()) return res;
        res = wordBreakSaveTimeHelper(s,dict,new HashMap<String, ArrayList<String>>());
        return res;
    }
    public static ArrayList<String> wordBreakSaveTimeHelper(String s, Set<String> dict,HashMap<String, ArrayList<String>> map){
        ArrayList<String > result = new ArrayList<String>();
        //base
        if(map.containsKey(s)) return map.get(s);
        for(int i = 1;i<s.length()+1;i++){
            String suffix = s.substring(s.length() - i);
            if(dict.contains(suffix)){
                if(s.length() == i){
                    result.add(suffix);
                }else{
                    ArrayList<String> prefix = wordBreakSaveTimeHelper(s.substring(0,s.length()-i),dict,map);
                    for(String str: prefix){
                        result.add(str+" "+suffix);
                    }
                }
            }
        }
        map.put(s,result);
        return result;
    }
    //recursive + dp to eliminate unnecessary comparison
    public static ArrayList<String> wordBreakSaveTimeWithDp(String s,Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        if(s == null || s.isEmpty() || dict.isEmpty()) return res;
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        for(int i =1;i<=s.length();i++){
            for(int k =0;k<i;k++){
                if(dp[k]&&dict.contains(s.substring(k,i))){
                    dp[i] = true;break;
                }
            }
        }
        wordBreakSaveTimeWithDpHelper(s,"",0,dict,dp,res);
        return res;
    }

    public static void wordBreakSaveTimeWithDpHelper(String s, String runner, int index, Set<String> dict, boolean[] dp, ArrayList<String> res){
        //base
        if(index==s.length()){
            res.add(runner.trim());
            return;
        }
        //recursive
        for(int i = index;i<s.length();i++){
            if(dict.contains(s.substring(index,i+1))&&dp[i+1]){
                String temp = runner + " " + s.substring(index,i+1);
                wordBreakSaveTimeWithDpHelper(s,temp,i+1,dict,dp,res);
            }
        }

    }

//    public static ArrayList<String> wordBreakNewTry(String s, Set<String> dict){
//        ArrayList<String>
//    }

    public static void main(String[] args){
        Set<String> dict =new HashSet<String>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
//        dict.add("dog");
        dict.add("sand");
        dict.add("this");
        dict.add("is");
        dict.add("th");
        dict.add("thi");
        dict.add("thisa");
        dict.add("sentence");
        final String s = "catsanddog";
        final String s1 = "thisissentence";
        wordBreakDp(s,dict);
        System.out.println(wordBreakExceedTime(s1,dict));
        System.out.println(wordBreakSaveTime(s1,dict));
        System.out.println(wordBreakSaveTimeWithDp(s1,dict));
//        System.out.println(wordBreak(s, dict));
    }
}

package LeetCode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Garvin on 9/3/2014.
 */
public class wordBreak {
    //collect result
    public static String insertSpace1(String s, Set<String> dict){
        if(s == null || s.isEmpty()) return s;
        LinkedList<String> list = new LinkedList<String>();
        StringBuffer  sb = new StringBuffer();
        if(helper(s,dict,list)){
            for(String str:list)
                sb.append(str);
        }
        return sb.toString().trim();
    }


    public static boolean helper(String s,Set<String> dict, LinkedList<String> list){
        if(s.isEmpty()) return true;
        for(int i =1;i<=s.length();i++){
            if(dict.contains(s.substring(0,i))){
                list.add(s.substring(0,i)+" ");
                if(helper(s.substring(i),dict,list)) return true;
                list.removeLast();
            }
        }
        return false;

    }
    //return result
    public static String insertSpace2(String s, Set<String> dict){
        return helper(s,dict);
    }
    public static String helper(String s, Set<String> dict){
        if(s.isEmpty()){
            return "";
        }
        for(int i =1;i<=s.length();i++){
            if(dict.contains(s.substring(0,i))){
                String temp = helper(s.substring(i),dict);
                if(temp!=null)
                    return s.substring(0,i)+" "+temp;

            }
        }
        return null;
    }
    public static String insert(String s, HashSet<String> dict){
        LinkedList<String> list = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        if(insertHelper(s,list,dict)){
            for(String str:list)
                sb.append(str);
        }
        return sb.toString().trim();
    }
    public static boolean insertHelper(String s, LinkedList<String> list, HashSet<String> dict){
        if(s.isEmpty()) return true;
        for(int i =1;i<=s.length();i++){
            if(dict.contains(s.substring(0,i))){
                list.add(s.substring(0,i)+" ");;
                if(insertHelper(s.substring(i),list,dict)) return true;
                list.removeLast();
            }
        }
        return false;
    }

    public static boolean wordBreakDp(String s, Set<String> dict){
        boolean dp[] = new boolean[s.length()+1];
        dp[0] = true;
        for(int i = 1;i<s.length()+1;i++){
            for(int k =0;k<i;k++){
                if(dp[k]&&dict.contains(s.substring(k,i))) dp[i] = true;
            }
        }
        return dp[s.length()];
    }

    public static ArrayList<String> wordBreakSaveTime(String s,Set<String> dict){
        return wordBreakSaveTimeHelper(s,dict,new HashMap<String, ArrayList<String>>());
    }
    public static ArrayList<String> wordBreakSaveTimeHelper(String s, Set<String> dict, HashMap<String, ArrayList<String>> map){
        ArrayList<String> result = new ArrayList<String>();
        //base
        if(map.containsKey(s)) return map.get(s);
        //recursive
        for(int i = 1;i<=s.length();i++){
            String suffix = s.substring(s.length()-i);
            if(dict.contains(suffix)){
                if(s.length() == i){
                    result.add(suffix);
                }else{
                    String prefix = s.substring(0,s.length()-i);
                    ArrayList<String> temp = wordBreakSaveTimeHelper(prefix, dict, map);
                    for(String str:temp){
                        str = str+" "+suffix;
                        result.add(str);
                    }
                }
            }
        }
        map.put(s,result);
        return result;
    }
    public static ArrayList<String> wordBreakExceedTime(String s, Set<String> dict){
        ArrayList<String> res = new ArrayList<String>();
        wordBreakExceedTimeHelper(s,dict,"",res);
        return res;
    }
    public static void wordBreakExceedTimeHelper(String s, Set<String> dict, String runner, ArrayList<String> res){
        //base
        if(s.isEmpty())
            res.add(runner.trim());
        //recursive
        for(int i = 1;i<s.length()+1;i++){
            if(dict.contains(s.substring(0,i))){
                String t = runner + " "+s.substring(0,i);
                wordBreakExceedTimeHelper(s.substring(i),dict,t,res);
                t = runner;
            }
        }
    }
    public static void main(String[] ars){
        HashSet<String> dict = new HashSet<String>();
        dict.add("thi");
        dict.add("th");
        dict.add("this");
        dict.add("thisis");
        dict.add("is");
        dict.add("sentence");
//        dict.add("cat");
//        dict.add("cats");
//        dict.add("and");
//        dict.add("sand");
//        dict.add("dog");

//        System.out.println(wordBreak("catsanddog", dict));
        System.out.println(wordBreakSaveTime("thisissentence", dict));


        System.out.println(wordBreakExceedTime("thisissentence", dict));

    }
}

package LeetCode;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Garvin on 9/5/2014.
 */
public class GrayCode {
    public static ArrayList<String> grayCodeStr(int n){
        ArrayList<String> res = new ArrayList<String>();
        //error checking
        if(n ==0) return res;
        //base
        if(n==1){
            res.add("0");
            res.add("1");
            return res;
        }
        //recursive
        ArrayList<String> temp = grayCodeStr(n - 1);
        for(String i: temp)
            res.add("0"+i);
        for(int i = temp.size()-1;i>=0;i--)
            res.add("1"+temp.get(i));
        return res;
    }

    public static int grayToInt(int num){
        int res = num;
        while((num>>=1)!=0)
            res^=num;
        return res;
    }

    public static ArrayList<Integer> grayCode(int n){
        ArrayList<Integer> res = new ArrayList<Integer>();
        //base
        if(n ==0){
            res.add(0);
            return res;
        }
        //recursive
        ArrayList<Integer> temp = grayCode(n-1);
        for(int i:temp)
            res.add(i);
        int gap = 1<<(n-1);
        for(int i = temp.size()-1;i>=0;i--){
            res.add(gap|temp.get(i));
        }
        return res;
    }
    public static int intToGrayTest(int num){
        return (num>>1)^num;
    }
    public static int GrayIntoIntTest(int num){
        int res = num;
        while((num >>=1)!=0)
            res^=num;
        return res;
    }
    public static boolean isGrayCode(byte a, byte b){
        int x = a^b;
        return (x&(x-1)) == 0;
    }
    //remove all the vowels in one string
    //Upper,
    public static String removeVowel(String s){
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(c!='a'&& c!='e'&&c!='i'&&c!='o'&&c!='u' && c!='A'&& c!='E'&&c!='I'&&c!='O'&&c!='U')
                sb.append(c);
        }
        return sb.toString();
    }
    public static void main(String[] args){
        System.out.println(grayCode(4));
        System.out.println(grayCodeStr(4));
//        System.out.println(GrayIntoIntTest(4));
//        System.out.println(intToGrayTest(7));


    }
}

package LeetCode;

/**
 * Created by Garvin on 9/22/2014.
 */
public class ReverseString {
    public static String reverseWord(String s){
        String[] tokens = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for(String str:tokens){
            if(str.isEmpty()) continue;
            sb.insert(0,str+" ");
        }
        return sb.toString().trim();
    }
    public String reverseWords(String s) {
    if( s == null ) return s;
    StringBuffer sb = new StringBuffer();
    for(int i = s.length() -1;i>=0;i--){
        while(i>=0 && s.charAt(i) == ' ') i--;
        if(i<0) break;
        if(sb.length()!=0) sb.append(" ");
        StringBuffer temp = new StringBuffer();
        while(i>=0&&s.charAt(i)!=' ') temp.append(s.charAt(i--));
        sb.append(reverse1(temp));

    }
    return sb.toString();
}
    public  StringBuffer reverse1(StringBuffer sb){
        int i = 0, j =sb.length()-1;

        while(i<j){
            char c = sb.charAt(i);
            sb.setCharAt(i,sb.charAt(j));
            sb.setCharAt(j,c);
            i++;j--;
        }
        return sb;
    }
    public static void main(String[] args){
        System.out.println(reverseWord(" this is a sentence"));
    }
}

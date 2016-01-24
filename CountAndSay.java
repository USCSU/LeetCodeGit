package LeetCode;

/**
 * Created by Garvin on 10/16/2014.
 */
public class CountAndSay {
    public static String countAndSay(int n){
        StringBuffer sb = new StringBuffer();
        if(n == 1) return "1";
        String residue = countAndSay(n-1);
        char c = residue.charAt(0);
        int count = 1;
        for(int i =1;i<residue.length();i++){
            if(residue.charAt(i) == c){
                count++;
            }else{
                sb.append(count);
                sb.append(c);
                count = 1;
                c = residue.charAt(i);
            }
        }
        sb.append(count);
        sb.append(c);
        return sb.toString();

    }
    public static void main(String[] args){
        System.out.println(countAndSay(10));
    }

}

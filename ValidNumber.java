package LeetCode;

/**
 * Created by Garvin on 10/16/2014.
 */
public class ValidNumber {
    public static boolean isValidNumber(String s) {
        if(s == null) return false; //error
        int len = s.length();
        int low = 0, high = len-1;
        //remove empty char from beginning
        while(low<=high && s.charAt(low) == ' ') low++;
        if(low > len-1) return false;
        while(high >=low&&s.charAt(high) ==' ') high--;
        //skip + and -
        if(s.charAt(low) == '+' || s.charAt(low) == '-') low++;
        boolean num = false;
        boolean dot = false;
        boolean exp = false;

        //traverse every char
        while(low<=high){
            char c = s.charAt(low);
            if(c>='0' && c<='9') num = true;
            else if(c =='.'){
                if(dot || exp) return false; //dot and e has showed up before this char
                dot = true;
            }else if(c =='e'){
                if(exp || !num) return false;//e has showed up before or no number happended before.
                exp = true;
                num = false;//to avoid '1e' situation
            }else if(c == '+' || c=='-'){
                if(s.charAt(low-1)!='e') return false;
            }else
                return false; //illegal char; return;
            low++;
        }
        return num;
    }
    public static  boolean isNumber(String s) {
        int len = s.length();
        int i = 0, e = len - 1;
        while (i <= e && Character.isWhitespace(s.charAt(i))) i++;
        if (i > len - 1) return false;
        while (e >= i && Character.isWhitespace(s.charAt(e))) e--;
        // skip leading +/-
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        boolean num = false; // is a digit
        boolean dot = false; // is a '.'
        boolean exp = false; // is a 'e'
        while (i <= e) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = true;
            }
            else if (c == '.') {
                if(exp || dot) return false;
                dot = true;
            }
            else if (c == 'e') {
                if(exp || num == false) return false;
                exp = true;
                num = false;
            }
            else if (c == '+' || c == '-') {
                if (s.charAt(i - 1) != 'e') return false;
            }
            else {
                return false;
            }
            i++;
        }
        return num;
    }
    public static void main(String[] args){
        System.out.println(isValidNumber("e"));
        System.out.println(isValidNumber(".1e-9892"));
        System.out.println(isValidNumber("1.1e.-9892"));
        System.out.println(isValidNumber(".1e-9892e"));
        System.out.println(isValidNumber("a"));
        System.out.println(isValidNumber("1 d"));

    }
}

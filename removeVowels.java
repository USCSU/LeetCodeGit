package LeetCode;

/**
 * Created by Garvin on 9/9/2014.
 */
public class removeVowels {
    public static String removeVowelsFromString(String s){
        if(s == null) return "";
        if(s.isEmpty()) return s;

        StringBuilder res = new StringBuilder();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c!='a'&& c!='e'&&c!='i'&&c!='o'&&c!='u' && c!='A'&& c!='E'&&c!='I'&&c!='O'&&c!='U')
                res.append(c);
        }
        return res.toString();
    }
    public static void main(String[] args){
        System.out.println(removeVowelsFromString("this is a test"));
    }

    //time complexity: O(n)  traversing every character of String needs O(n) complexity
    //Assume original string can't be altered.
    //Space complexity: O(n) StringBuilder needed with O(n) space complexity.

    //Possible optimization:
    //Because there are only 10 vowels in English, I simply filter them with "if-else" block.If there are more characters
    //needed to be filtered, I can use Hash if it's unicode(large than 255), and a word with bit manipulation if it's ASCII(smaller than 255).

}

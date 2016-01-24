package LeetCode2015.ExpressionCaculator;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Trace_route on 7/2/15.
 */
public class BasicCaculator {
    public static int calculate(String s){
        //error checking

        int ret = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(1);
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){ // get all valid data
                int num = c-'0';
                int j = i+1;
                while(j<s.length() && Character.isDigit(s.charAt(j))){
                    num = num*10 + (s.charAt(j) - '0');
                    j++;
                }
                ret+=stack.pop()*num;
                i = j-1;
            }
            else if(c == '(' || c=='+') // push 1
                stack.push(stack.peek());
            else if(c == '-') //push -1
                stack.push(-1*stack.peek());
            else if(c == ')') stack.pop();
        }
        return ret;
    }
    public static int calculate1(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(1);
        int ret = 0;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = c-'0';
                int j = i+1;
                while(j<s.length() && Character.isDigit(s.charAt(j))){
                    num = num*10+s.charAt(j) - '0';
                    j++;
                }
                ret=+num*stack.pop();
                i =j-1;

            }else if(c == '(' || c == '+')
                stack.push(stack.peek()*1);
            else if(c == '-')
                stack.push(stack.peek()*-1);
            else if(c == ')')
                stack.pop();
        }
        return ret;
    }
    public static int calculate2(String s){
        Stack<Integer> stack = new Stack<Integer>();
        int rs = 0;
        int sign = 1;
        stack.push(1);
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == ' ') continue;
            else if (s.charAt(i) == '('){
                stack.push(stack.peek() * sign);
                sign = 1;
            }
            else if (s.charAt(i) == ')') stack.pop();
            else if (s.charAt(i) == '+') sign = 1;
            else if (s.charAt(i) == '-') sign = -1;
            else{
                int temp = s.charAt(i) - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1)))
                    temp = temp * 10 + s.charAt(++i) - '0';
                rs += sign * stack.peek() * temp;
            }
        }
        return rs;
    }

    public static int caculator(String s){
        //error checking
        if(s == null || s.isEmpty()) return 0;
        int ret =0 ;
        return ret;
    }
    public static void main(String[] args){
//        System.out.println(calculate1("(12+(43+51+23-(23-3))-376)+(6+8))"));
//        System.out.println(calculate("(12+(43+51+23)-(23-3)-376)+(6+8))"));
//        System.out.println(calculate2("(12+(43+51+23)-(23-3)-376)+(6+8))"));
//        System.out.println(calculate(" 2-1 + 2 "));
//        System.out.println(calculate("23+43"));
//        System.out.println(calculate2("23-(56-3)"));
        System.out.println(calculate1("23-(56-3)"));
    }
}

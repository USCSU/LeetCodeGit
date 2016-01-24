package LeetCode2015.ExpressionCaculator;

import java.util.Stack;

/**
 * Created by Trace_route on 7/3/15.
 */
public class EvaluateRPN {
    public static int evalRPN(String[] tokens) {
        //int result = 0;
        Stack<Integer> s1 = new Stack<Integer>();
        // Stack<Character> s2 = new Stack<Character>();
        for(String s: tokens){
            if(isNum(s))
                s1.push(Integer.parseInt(s));
            else{
                int op2 = s1.pop();
                int op1 = s1.pop();
                if(s.equals("+"))
                    s1.push(op1+op2);
                if(s.equals("-"))
                    s1.push(op1-op2);
                if(s.equals("*"))
                    s1.push(op1*op2);
                if(s.equals("/"))
                    s1.push(op1/op2);

            }
        }

        return s1.pop();
    }
    public static boolean isNum(String s){
        try{
            int t = Integer.parseInt(s);

        }catch(Exception e){
            return false;
        }
        return true;
    }
    public static void main(String[] args){
        String s = "12 43 51 + 23 + 23 3 - - + 376 - 6 8 + +";
        String s1 = "23 56 3 - -";
        System.out.println(evalRPN(s.split(" ")));
        System.out.println(evalRPN(s1.split(" ")));
    }
}

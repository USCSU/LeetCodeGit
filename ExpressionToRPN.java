package LeetCode2015.ExpressionCaculator;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/*
* */
public class ExpressionToRPN {
        HashMap<Character,Integer> map;
        ExpressionToRPN() {
           map = new HashMap<Character, Integer>();
            map.put('-', 1);
            map.put('+', 1);
            map.put('*', 2);
            map.put('/', 2);
        }


    //此函数将中缀表达式，转化为后缀表达式
    public  String convert(String expression){
        //error checking
        if(expression == null || expression.length() ==0)
            return "";
        StringBuffer ret = new StringBuffer();
        Stack<Character> stack = new Stack();


        for(int i =0;i<expression.length();i++){
            char c = expression.charAt(i);
            if(Character.isDigit(c) ||Character.isAlphabetic(c)){
                int j = i+1;
                int num = c-'0';
                while(j<expression.length()&& Character.isDigit(expression.charAt(j))){
                    num=num*10+expression.charAt(j)-'0';
                    j++;
                }
                i= j-1;
                ret.append(num+" ");
            }else{ // character
                if(c == '(')
                    stack.push(c);
                else if(c == ')'){
                    while(!stack.isEmpty()){
                        char temp = stack.pop();
                        if(temp!='(') ret.append(temp+" ");
                        else break;
                    }
                }else{
                    int winner = compare(c,stack);
                    if(winner >0){ //current has higher priority
                        stack.push(c);
                    }else{
                        while(winner<=0&&!stack.isEmpty()){
                            ret.append(stack.pop()+" ");
                            winner = compare(c,stack);
                        }
                        stack.push(c);
                    }

                }

            }
        }
        while(!stack.isEmpty()) ret.append(stack.pop()+" ");
        return ret.toString();
    }

    public  int compare (char cur, Stack<Character> stack){
        if(stack.isEmpty()) return 1;
        else if(stack.peek() == '(') return 1;
        else
        return map.get(cur) - map.get(stack.peek());
    }


    /* rules below:
        rule1: 如果输入是数字，直接加入结果集；经过一个while搜集到最大可能整数，比如234
        rule2：如果不是数字，分类讨论，如果是左括号，压栈
        rule3：如果是右括号，出栈压入结果集，知道遇到左括号
        rule4：如果是运算符，那么比较运算级别，如果运算级别比栈里peek的高，那么就压栈，说明还要继续算；
                如果运算级别低，那么就出栈直到当前运算符的运算级别比peek高位置；此时压入当前运算符。
        rule5: 当游标已经到字符串末尾的时候，如果堆栈中还有运算符，则出栈并入结果集。
    * */

    public LinkedList<String> transferToRPN(String expression){
        //error checking here is omitted
        LinkedList<String> ret = new LinkedList<String>();
        Stack<Character> stack = new Stack<Character>();
        for(int i =0;i<expression.length();i++){
            char c = expression.charAt(i);
            //check if it's number
            if(Character.isDigit(c)){ //rule1
                //check how many digits
                int num = c-'0';
                int j = i+1;
                while(j<expression.length()&&Character.isDigit(expression.charAt(j))){
                    num = num*10+expression.charAt(j)-'0';
                    j++;
                }
                ret.add(num+"");
                i = j-1;
            }else{ // not a number
                if(c == '(') stack.push(c);//rule 2

                else if(c == ')'){ //pop all the character until ( // rule3
                    while(!stack.isEmpty()){
                        char temp = stack.pop();
                        if(temp!='(') ret.add(temp+"");
                        else break;
                    }

                }

                else{//rule4
                    int winner = compare(c,stack);
                        while(winner<=0&&!stack.isEmpty()){
                            ret.add(stack.pop()+"");
                            winner = compare(c,stack);
                        }
                    stack.push(c);

                }

            }
        }

        while(!stack.isEmpty()) ret.add(stack.pop()+""); //rule5
        return ret;
    }
    //evaluate reverse polish notation
    public int evaluate(LinkedList<String> rpn){
        int ret = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for(String s: rpn){
            if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")){
                int op2 = stack.pop();
                int op1 = stack.pop();
                if(s.equals("+"))
                    stack.push(op1+op2);
                if(s.equals("-"))
                    stack.push(op1-op2);
                if(s.equals("*"))
                    stack.push(op1*op2);
                if(s.equals("/"))
                    stack.push(op1/op2);

            }else{
                stack.push((Integer.parseInt(s)));
            }
        }
        ret = stack.pop();
        return ret;
    }


    public static void main(String[] args){
        ExpressionToRPN instance = new ExpressionToRPN();
        System.out.println(new ExpressionToRPN().convert("a+b*c+(d*e+f)*g"));
//        System.out.println(new ExpressionToRPN().convert("(12+(43+51+23-(23-3))-376)+(6+8))"));
//        LinkedList<String> rpn = instance.transferToRPN("(12+(43+51+23-(23-3))-376)+(6+8))");
        LinkedList<String> rpn = instance.transferToRPN("((3+5*2)+3)/5+6/4*2+3/4+(7*7)+2*4/2+3");
        System.out.println(instance.evaluate(rpn));
        String s = "12 43 51 + 23 + 23 3 - - + 376 - 6 8 + +";
        System.out.println(s);
        System.out.println();
    }

}

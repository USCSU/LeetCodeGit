package LeetCode2015.ExpressionCaculator;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Trace_route on 7/6/15.
 */
public class Caculator {
    /*The expression string may contain open ( and closing parentheses ),
    the plus + or minus sign -, non-negative integers and empty spaces .
    * */
    HashMap<Character,Integer> map;
    public Caculator(){
        map = new HashMap<Character, Integer>();
        map.put('-',1);
        map.put('+',1);
        map.put('/',2);
        map.put('*',2);
    }
     public static int caculator(String s){
        //error checking
        if(s == null|| s.isEmpty()) return 0;
        int ret = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(1);
        stack.push(1);
        int i =0 ;
        while(i<s.length()){
            char c = s.charAt(i);
            if(Character.isDigit(c)) {
                int j = i + 1;
                int num = c - '0';
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    num = num * 10 + s.charAt(j) - '0';
                    j++;
                }
                ret += stack.pop()*num;
                i = j - 1;
            }else if(c == '(' || c =='+') stack.push(stack.peek() * 1);
            else if(c == '-') stack.push(stack.peek()*-1);
            else if(c == ')') stack.pop();
            System.out.println(stack.toString());
            i++;
        }
         for(int num:stack) ret+=num;
        return ret;
    }
    /*The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
    The integer division should truncate toward zero.
    * */
    public static int caculatorII(String s){
        //error checking
        if(s == null || s.isEmpty()) return 0;
        int ret =0;
        Stack<Integer> stack = new Stack<Integer>();
        char sign = '+';
            int num = 0;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10+c-'0';
            }
            if(!Character.isDigit(c)&&c!=' '||i==s.length()-1){
                if(sign == '+'){
                    stack.push(num);
                }else if(sign == '-'){
                    stack.push(-num);
                }else if(sign=='*'){
                    stack.push(stack.pop()*num);
                }else if(sign =='/'){
                    stack.push(stack.pop()/num);
                }
                sign = c;
                num = 0;
            }
            System.out.println(stack.toString());
        }
        return ret;
    }

    //O(n) solution -- change expression into RPN version
    /* rule1: if its number, push
       rule2 : if it's ( push
       rule3: if its ), pop unitl (
       rule 4: compare priority, pop until the peek's priority is less than cur one
       rule 5: pop out all elements in the stack if all done
        i.e. a+b*c+(d*e+f)*g
        output: abc*+de*f+g*+
    * */
    public LinkedList<String> toRPN(String s){
        LinkedList<String> ret = new LinkedList<String>();
        Stack<Character> stack = new Stack<Character>();
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = c-'0';
                int j = i+1;
                for(;j<s.length()&&Character.isDigit(s.charAt(j));j++){
                    num = num*10+s.charAt(j)-'0';
                }
                ret.add(num+"");
                i = j-1;
            }else if(c ==' ') continue;
            else if(c == '(') stack.push(c);
            else if(c == ')'){
                while(!stack.isEmpty()&&stack.peek()!='(') ret.add(stack.pop()+"");
                if(!stack.isEmpty())
                    stack.pop();
            }else{
                while(compare(stack,c) <=0 && !stack.isEmpty()){
                    ret.add(stack.pop()+"");
                }
                stack.push(c);
            }
        }
        while(!stack.isEmpty()) ret.add(stack.pop()+"");
        return ret;
    }

    public int evaluate(LinkedList<String> rpn ){
        Stack<Integer> stack = new Stack<Integer>();
        for(String s:rpn){
            if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                int val2 = stack.pop();
                int val1 = stack.pop();
                if(s.equals("+")) stack.push(val1+val2);
                else if(s.equals("-")) stack.push(val1-val2);
                else if(s.equals("*")) stack.push(val1*val2);
                else if(s.equals("/")) stack.push(val1/val2);
            }
            else
                stack.push(Integer.parseInt(s));
        }
        return stack.pop();
    }
    public  int compare(Stack<Character> stack, char cur){
        if(stack.isEmpty()) return 1;
        else if(stack.peek() =='(') return 1;
        else return map.get(cur) - map.get(stack.peek());
    }

    class Node{
        char c;
        Node left;
        Node right;
        Node(char c){
            this.c  =c ;
        }
    }

//找到最low的priority
    public int getIndex(String s){
        int result = Integer.MAX_VALUE;
        int index = s.length(), counter =0;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '(') counter++;
            else if(c ==')') counter--;
            else if(counter==0 ){
                if(map.containsKey(c)&&map.get(c)<= result){
                    result = map.get(c);
                    index = i;
                }
            }
        }
        return index;
    }
    public  Node buildTree(String s1){
        //base
        if(s1.isEmpty()) return  null;
        if(s1.length() == 1) return new Node(s1.charAt(0));
        System.out.println(s1);
        int index = getIndex(s1);
        //get rid of ()
        while(index == s1.length()){
            s1 = s1.substring(1,s1.length()-1).trim();
            index = getIndex(s1);
        }
        Node root = new Node(s1.charAt(index));
        root.left = buildTree(s1.substring(0,index));
        root.right = buildTree(s1.substring(index+1,s1.length()));
        return root;
    }
    public  ArrayList<Character> printPreTree(Node root){
        ArrayList<Character> ret = new ArrayList<Character>();
        if(root == null) return ret;
        ret.addAll(printPreTree(root.left));
        ret.addAll(printPreTree(root.right));
        ret.add(root.c);
        return ret;
    }

    public  int evaluateTree(Node root){
        if(Character.isDigit(root.c)) return Integer.parseInt(root.c+"");
        int left = evaluateTree(root.left);
        int right = evaluateTree( root.right);
        return helper(left,right,root.c);
    }
    public  int helper(int left, int right, char c){
        if(c == '-') return left- right;
        else if(c =='+') return left+ right;
        else if(c == '*') return left*right;
        else return left/right;
    }
    public static void main(String[] agrs){
//        String s = "1-(1-(2-3-(4+3-1)))";
        String s1 = "((3+5*2)+3)/5+6/4*2+3/4+(7*7)+2*4/2+3";
        Caculator caculator = new Caculator();
//        LinkedList<String> list = caculator.toRPN(s1);
//        System.out.println(list);
//        System.out.println(caculator.evaluate(list));



        Node root = caculator.buildTree(s1);
        System.out.println(caculator.evaluateTree(root));
//        System.out.println(caculator(s1));
//        String s1 = "1*1";
//        System.out.println(caculatorII(s1));
    }
 }

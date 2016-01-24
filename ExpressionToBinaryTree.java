package LeetCode2015.ExpressionCaculator;

import java.util.ArrayList;

class ExpNode{
    char c;
    ExpNode left;
    ExpNode right;
    ExpNode(char c){
        this.c =c ;
        left = null;
        right =null;
    }
}
/* 1. 分割字符串，如 1+（2*3+2）-1
   2. 找根节点： 这里 + 和 - 都可以作为根，但是要有优先级，比如（） > *  > +-
   3. 找到根节点，分割字符串，为了出去空格，每一次的string都要trim一下
   4.此例中 左字串则是1+（2*3+2） 右子串则为1
* */

//this programming hasn't deal with 234 , but assume each node's value is char
 public class ExpressionToBinaryTree {
    public static ExpNode buildTree(String s){
        //base
        s = s.trim();
        if(s.length() == 1) return new ExpNode(s.charAt(0));
        if(s.isEmpty()) return null;
        //process
        int index = getIndex(s); //get index with lowest priority
        if(index == s.length()) { //if not,meaning we have pathensis
            s= s.substring(1,s.length()-1); // remove them
            s = s.trim();//trim blank and redo the process;
            index = getIndex(s);
        }

        ExpNode root = new ExpNode(s.charAt(index));
        root.left = buildTree(s.substring(0,index)); //left subtree
        root.right = buildTree(s.substring(index+1,s.length())); //righe subtree
        return root;
    }
    public static int getIndex(String s){
        if(s.length() <2) return 0;
        int index = s.length();
        int value = Integer.MAX_VALUE;
        int counter =0;
        for(int i =0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '(') counter++;
            else if(c == ')') counter--;
            else if(counter==0){
                if(getPrority(c) <= value){
                    value = getPrority(c);
                    index = i;
                }
            }
        }
        return index;
    }
    public static int getPrority(char c){ //garantee the priority
        if(c == '+' || c=='-') return 1;
        else if(c=='*' || c== '/') return 2;
        else return Integer.MAX_VALUE;
    }
    public static void main(String[] args){
//        String s = "8-(3+5)*(5-6/2 )+2";
        String s = "((3+5*2)+3)/5+6/4*2+3/4+(7*7)+2*4/2+3";
//        System.out.println(s.substring(0,1));
//        System.out.println(s.substring(1,s.length()));
        ExpNode root = buildTree(s);
        System.out.println(printPreTree(root));
        System.out.println(printInTree(root));
        System.out.println(evaluate(root));
//        System.out.println(getIndex("6/2"));
    }

    public static int evaluate(ExpNode root){
        if(Character.isDigit(root.c)) return Integer.parseInt(root.c+"");
        int left = evaluate(root.left);
        int right = evaluate( root.right);
        return helper(left,right,root.c);
    }
    public static int helper(int left, int right, char c){
        if(c == '-') return left- right;
        else if(c =='+') return left+ right;
        else if(c == '*') return left*right;
        else return left/right;
    }

    public static ArrayList<Character> printPreTree(ExpNode root){
        ArrayList<Character> ret = new ArrayList<Character>();
        if(root == null) return ret;
        ret.addAll(printPreTree(root.left));
        ret.addAll(printPreTree(root.right));
        ret.add(root.c);
        return ret;
    }
    public static ArrayList<Character> printInTree(ExpNode root){
        ArrayList<Character> ret = new ArrayList<Character>();
        if(root == null) return ret;
        ret.addAll(printInTree(root.left));
        ret.add(root.c);
        ret.addAll(printInTree(root.right));

        return ret;
    }
}

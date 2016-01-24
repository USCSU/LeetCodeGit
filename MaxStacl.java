package LeetCode;

import java.util.Stack;

/**
 * Created by Garvin on 10/19/2014.
 */
public class MaxStacl extends Stack<Integer> {
    Stack<Integer> s2;
    public MaxStacl(){
        s2 = new Stack<Integer>();
    }
    public void push(int i){
        if(i>=max())
            s2.push(i);
        super.push(i);
    }
    public Integer pop(){
        if(super.size() ==0) return Integer.MIN_VALUE;
        int peek = super.pop();
        if(peek == max())
            s2.pop();
        return peek;
    }
    private Integer max(){
        if(s2.isEmpty()) return Integer.MIN_VALUE;
        return s2.peek();
    }
    public Integer peekMax(){
        return max();
    }
    public Integer popMax(){
        if(super.size() ==0) return Integer.MIN_VALUE;

        int max = s2.pop();
        Stack<Integer> temp = new Stack<Integer>();
        while(super.peek()!=max){
            temp.push(super.pop());
        }
        super.pop();
        while(!temp.isEmpty())
            super.push(temp.pop());
        return max;

    }
    public static void main(String[] args){
        MaxStacl s = new MaxStacl();
        int[] array = {15,15,3,4,5,3,2,6};
        for(int i:array)
            s.push(i);
        System.out.println(s);
        System.out.println(s.s2);

        System.out.println(s.peekMax());
        System.out.println(s.popMax());
        System.out.println(s);
        System.out.println(s.s2);

    }


}

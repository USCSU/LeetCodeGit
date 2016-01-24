package LeetCode2015;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Trace_route on 7/18/15.
 */
 class MyStack {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    private int peek;
    MyStack(){
        q1 = new LinkedList<Integer>();
        q2 = new LinkedList<Integer>();
    }
    // Push element x onto stack.
    public void push(int x) {
        q1.add(x);
        peek = x;
    }

    // Removes the element on top of the stack.
    public void pop() {
        if(q1.isEmpty()) return;
        while (q1.size()>1) {
            peek = q1.poll();
            q2.add(peek);
        }
        q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    // Get the top element.
    public int top() {
        if(q1.isEmpty()) return -1;
        return peek;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return q1.isEmpty();
    }
    public String toString(){
        return q1.toString();
    }
}
public class ImplementStackUsingQueue {
    public static void main(String[] args) {
       int[] array = {1, 2, 3, 4, 5};
       MyStack stack = new MyStack();
       for(int i: array) stack.push(i);
        System.out.print(stack.toString());
        while(!stack.empty()) {
            System.out.println(stack.top());
            stack.pop();
        }
    }

}

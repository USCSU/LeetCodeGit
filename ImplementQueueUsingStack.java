package LeetCode2015;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Trace_route on 7/18/15.
 */
 class MyQueue {
     Stack<Integer> stack1;
    Stack<Integer> stack2;
    int peek;
    public MyQueue(){
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    // Push element x to the back of queue.
    public void push(int x) {
        if(stack1.isEmpty()) peek = x;
        stack1.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() throws EmptyStackException{
        if(stack1.isEmpty()) throw new EmptyStackException();
        while(!stack1.isEmpty()) stack2.push(stack1.pop());
        stack2.pop();
        peek = stack2.isEmpty()?Integer.MAX_VALUE:stack2.peek();
        while(!stack2.isEmpty()) stack1.push(stack2.pop());
    }

    // Get the front element.
    public int peek() throws EmptyStackException{
        if(stack1.isEmpty()) throw new EmptyStackException();
        return peek;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return stack1.isEmpty();
    }
}
public class ImplementQueueUsingStack{
    public static void main(String[] args){
        MyQueue queue = new MyQueue();
        int[] array = {1,2,3,4,5};
        for(int i:array)
            queue.push(i);
        System.out.println(queue.stack1.toString());
        while(!queue.empty()) {
            System.out.println(queue.peek());
            queue.pop();
        }
    }
}

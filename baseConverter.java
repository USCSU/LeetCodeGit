package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Garvin on 9/7/2014.
 */
public class baseConverter {
    static String conversion(int M, int N) // return string, accept two integers
    {
        Queue<Integer> deque = new LinkedList<Integer>();
        while (M >= N)  // now the repetitive loop is clearly seen
        {
            deque.add(M%N);    // store a digit
            M = M/N;    // find new M
        }
        deque.add(M);
        // now it's time to collect the digits together
        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            int val = deque.poll();
            char ch = val < 10 ? (char)('0' + val) : (char)('a' + (val - 10));
            sb.append(ch);
        }
        return sb.toString();
    }
}

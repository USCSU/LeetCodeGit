package LeetCode2015;

import java.util.PriorityQueue;

/**
 * Created by Trace_route on 7/10/15.
 */
public class KthLargestItem {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        for(int i:nums){
            if(q.size()<k) q.add(i);
            else{
                if(i > q.peek()){
                    q.poll();
                    q.add(i);
                }
            }
        }
        return q.poll();
    }

    public static void main(String[] args) {
        int[] num = { 3,2,1,5,6,4};
        int k = 3;
        System.out.print(findKthLargest(num,k));
    }

}
package LeetCode2015;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 * given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

 Ensure that numbers within the set are sorted in ascending order.


 Example 1:

 Input: k = 3, n = 7

 Output:

 [[1,2,4]]

 Example 2:

 Input: k = 3, n = 9

 Output:

 [[1,2,6], [1,3,5], [2,3,4]]

 */
public class CombinationSumIII {
    public static void main(String[] args){
        System.out.println(combinationSum3(3, 15));
        System.out.println(combinationSum(3, 15));
    }
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<LinkedList<Integer>> ret = new LinkedList<LinkedList<Integer>>();
        helper(k,n,1,new LinkedList<Integer>(),ret);
        return (List)ret;
    }
    public static void helper(int k, int target, int cur, LinkedList<Integer> collector, List<LinkedList<Integer>> ret){
        //base
        if( target ==0 && k ==0){
            ret.add(new LinkedList<Integer>(collector));
            return;
        }
        if(target<0 || cur >9) return;
        //choose current
            collector.add(cur);
            helper(k-1,target-cur,cur+1,collector,ret);
            collector.removeLast();
            helper(k,target,cur+1,collector,ret);
    }



    public static List<List<Integer>> combinationSum(int k, int n){
        if( k <=0 ) return null;
        return helper(k,n,1,new LinkedList<Integer>());
    }
    public static List<List<Integer>> helper(int k, int target, int cur, List<Integer> collector){
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        //base
        if(target == 0 && k==0){
            ret.add(new LinkedList<Integer>(collector));
            return ret;
        }
        if(cur >9  || target<0) return ret;
        //choose current
        collector.add(cur);
        ret.addAll(helper(k-1,target-cur,cur+1,collector));
        collector.remove(collector.size()-1);

        //not choose current
        ret.addAll(helper(k,target,cur+1,collector));
        return ret;
    }


}

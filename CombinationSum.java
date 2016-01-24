package LeetCode2015;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.

 The same repeated number may be chosen from C unlimited number of times.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 A solution set is:
 [7]
 [2, 2, 3]
 */
public class CombinationSum {
    public static void main(String[] args){
        int[] array = {2,3,6,7};
        int target = 7;
        System.out.print(combinationSum(array,target));
    }
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) return null;
        return new LinkedList<List<Integer>>(helper(candidates,0,target, new LinkedList<Integer>()));
    }
    public static HashSet<List<Integer>> helper(int[] candidates, int index, int target, LinkedList<Integer> collector ){
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        //base
        if(target ==0)
            set.add(new LinkedList<Integer>(collector));

        if(index == candidates.length || target<0) return new HashSet<List<Integer>>();
        set.addAll(helper(candidates, index + 1, target, collector)); //not choose current one
        collector.add(candidates[index]); //choose current one
        set.addAll(helper(candidates,index,target-candidates[index],collector));
        collector.removeLast();
        return set;
    }
}

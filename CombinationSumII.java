package LeetCode2015;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.

 Each number in C may only be used once in the combination.

 Note:
 All numbers (including target) will be positive integers.
 Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 The solution set must not contain duplicate combinations.
 For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 A solution set is:
 [1, 7]
 [1, 2, 5]
 [2, 6]
 [1, 1, 6]
 */
public class CombinationSumII {
    public static void main(String[] args){
        int[] array = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.print(combinationSum2(array, target));
    }
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates == null || candidates.length == 0) return null;
        Arrays.sort(candidates);
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
        set.addAll(helper(candidates,index+1,target-candidates[index],collector));
        collector.removeLast();
        return set;
    }
}

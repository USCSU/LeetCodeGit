package LeetCode2015;

import java.util.ArrayList;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 For example,
 If n = 4 and k = 2, a solution is:

 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Combination {
    public static void main(String[] args){
        System.out.println(combine(4,2));
        System.out.println(combine1(4,2));
    }
    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        combinehelper(n,1,k,new ArrayList<Integer>(),res);
        return res;
    }
    public static void combinehelper(int n,int index,int k, ArrayList<Integer> collector, ArrayList<ArrayList<Integer>> ret){
        //base
        if(k==0){
            ret.add(new ArrayList<Integer>(collector));
            return;
        }
        if(index >n || k <0 ) return;
//        if(index == n+1) return;
        //get current
        collector.add(index);
        combinehelper(n, index+1, k - 1, collector, ret);
        collector.remove(collector.size()-1);
        //not get current
        combinehelper(n,index+1,k,collector,ret);
    }



    public static ArrayList<ArrayList<Integer>> combine1(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        combinehelper1(n,k,1,new ArrayList<Integer>(),res);
        return res;
    }
    public static void combinehelper1(int n, int k, int index, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> res){
        //base
        //normal exit
        if(k == 0){
            res.add((ArrayList<Integer>)path.clone());
            return;
        }
        // index == n && k!=0
        if(index-1 == n) return;
        combinehelper1(n,k,index+1,path,res);
        path.add(index);
        combinehelper1(n,k-1,index+1,path,res);
        path.remove(path.size()-1);
    }
}

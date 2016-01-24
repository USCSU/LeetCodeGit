package LeetCode2015;

import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

class BinarySTreeNode{
    int val;
    BinarySTreeNode left;
    BinarySTreeNode right;
    BinarySTreeNode(int val){
        this.val = val;
    }
}
class BinarySearchTree{
    BinarySTreeNode root;
    BinarySearchTree(){};
    BinarySearchTree(int val){
        root = new BinarySTreeNode(val);
    }
    public void insert(int val){
        root = insert(val,root);
    }
    private BinarySTreeNode insert(int val, BinarySTreeNode root){
        if(root == null) return new BinarySTreeNode(val);
        if(val == root.val) return root;
        else if(val > root.val) root.right = insert(val,root.right);
        else  root.left = insert(val,root.left);
        return root;
    }
    public void remove(int val){
        root = remove(val,root);
    }
    private BinarySTreeNode remove(int val, BinarySTreeNode root){
        if(root == null) return null;
        if(val < root.val) root.left = remove(val,root.left);
        else if(val > root.val) root.right = remove(val, root.right);
        else{
            if(root.left != null && root.right !=null){
                root.val = root.right.val;
                root.right = remove(root.val,root.right);
            }else{
                root = root.left == null?root.right:root.left;
            }
        }
        return root;
    }
    private boolean has(int low, int high, BinarySTreeNode root){
        if(root == null) return false;
        if(root.val >= low && root.val <= high) return true;
        else if(root.val > high) return has(low,high,root.left);
        else if(root.val<low) return has(low,high,root.right);
        return false;
    }
    public boolean has(int low, int high){
        return has(low,high,root);
    }
}
public class ContainDup {
    public static void main(String[] args){
        int[] nums = {1,5,-2,-4,0};
        int[] nums1 = {-1,-1};
        System.out.print(containsDuplicate(nums));
        System.out.print(containsNearbyAlmostDuplicate(nums1,1,-1)); // 0 is k , t is 1
        BinarySearchTree bst = new BinarySearchTree();
    }
    public static boolean containsDuplicate(int[] nums) {
        if(nums == null || nums.length==0) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num:nums){
            if(set.contains(num)) return true;
            set.add(num);
        }
        return false;
    }
    public static boolean containsDuplicates(int[] nums){
        if(nums == null || nums.length==0) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int num:nums) set.add(num);
        return set.size()<nums.length;

    }
    /*Given an array of integers and an integer k,
    find out whether there there are two distinct indices i and j in the array
    such that nums[i] = nums[j] and the difference between i and j is at most k.*/
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length <2) return false;
        HashMap<Integer,LinkedList<Integer>> map = new HashMap<Integer, LinkedList<Integer>>();
        for(int i = 0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                LinkedList<Integer> list = new LinkedList<Integer>();
                list.add(i);
                map.put(nums[i],list);
            }else{
                LinkedList<Integer> list = map.get(nums[i]);
                for(int index:list){
                    if(i - index <=k) return true;
                }
                list.add(i);
            }
        }
        return false;
    }
    public static boolean containsNearbyDuplicateSimple(int[] nums, int k) {
        if(nums == null || nums.length < 2) return false;
        HashSet<Integer> set = new HashSet<Integer>();
        for(int i =0;i<nums.length;i++){
            if(i>=k+1) set.remove(nums[i-k-1]);
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }

    /*Given an array of integers, find out whether there are two distinct indices i and j in the array
    such that the difference between nums[i] and nums[j] is at most t and
    the difference between i and j is at most k.
    * */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(nums==null || nums.length < 2) return false;
        BinarySearchTree bst = new BinarySearchTree();
        for(int i =0;i<nums.length;i++){
            if(i>=k+1) bst.remove(nums[i-k-1]);
            long cur = nums[i];
            long low = Math.max(cur-t,Integer.MIN_VALUE);
            long high = Math.min(cur+t,Integer.MAX_VALUE);
            if(bst.has((int)low,(int)high)) return true;
            bst.insert(nums[i]);
        }
        return false;
    }
}

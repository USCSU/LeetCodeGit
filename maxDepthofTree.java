package LeetCode;

/**
 * Created by Garvin on 9/8/2014.
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
        left = null;
        right=null;
    }
}
public class maxDepthofTree {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}

package LeetCode2015;

/**
 * Created by Trace_route on 7/18/15.
 */
public class CountTreeNode {
    public static int countNodes(TreeNode root) {
        int count = 0;
        if(root == null) return 0;
        count++;
        count+=countNodes(root.left);
        count+=countNodes(root.right);
        return count;
    }

    public static int countNodesII(TreeNode root){
        if(root == null) return 0;
        TreeNode left = root, right = root;
        int leftLevel = 0, rightLevel =0;
        while(left!=null) {left = left.left; leftLevel++;}
        while(right!=null) {right = right.right; rightLevel++;}
        if(leftLevel == rightLevel) return (int)(Math.pow(2,leftLevel)-1);
        return 1+countNodesII(root.left)+countNodesII(root.right);
    }
    public static int countNodeIII(TreeNode root){
        if(root == null) return 0;
        int depth = 0;
        TreeNode runner = root;
        while(runner!=null){
            depth ++;
            runner = runner.left;
        }
        int left = 0, right = (1<<(depth-1))-1;
        while(left <= right){
            int mid = (left+right)>>1;
            if(hasLeaves(root,mid,depth-1))
                left = mid +1;
            else
                right = mid -1;
        }
       return (1<<(depth-1))+right;
    }
    public static boolean hasLeaves(TreeNode root, int mid, int step) {
        while(step-->0){
            int mask = 1<<step;
            if(root == null) return false;
            if((mid & mask)==0) root  = root.left;
            else root = root.right;
        }
        return root!=null;
    }
    public static void main(String[] args){
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);
        TreeNode h = new TreeNode(8);
        TreeNode i = new TreeNode(9);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;
        System.out.println(countNodes(a));
        System.out.println(countNodesII(a));
        System.out.println(countNodeIII(a));

    }

}

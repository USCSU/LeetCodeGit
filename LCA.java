package LeetCode;

/**
 * Created by Garvin on 9/21/2014.
 */

public class LCA {
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return null;
        }

        if (root.equals(a) || root.equals(b)) {
            // if at least one matched, no need to continue
            // this is the LCA for this root
            return root;
        }

        TreeNode l = lowestCommonAncestor(root.left, a, b);
        TreeNode r = lowestCommonAncestor(root.right, a, b);

        if (l != null && r != null) {
            return root;  // nodes are each on a seaparate branch
        }

        // either one node is on one branch,
        // or none was found in any of the branches
        return l != null ? l : r;
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
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        g.right = h;
        System.out.println(lowestCommonAncestor(a,f,h).val);
    }
}

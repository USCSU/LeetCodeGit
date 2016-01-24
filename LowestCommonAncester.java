package LeetCode2015;


public class LowestCommonAncester {
    public static BSTNode lowestCommonAncestor(BSTNode root, BSTNode p, BSTNode q) {
        if(root == null) return null;
        if(root == p || root == q) return root;
        BSTNode left = lowestCommonAncestor(root.left,p,q);
        BSTNode right = lowestCommonAncestor(root.right,p,q);
        if(left!=null && right!=null ) return root;
        return left==null?right:left;
    }
    public static void main(String[] args){
         BST bst = new BST();
        bst.insert(6);
        bst.insert(2);
        bst.insert(8);
        bst.insert(1);
        bst.insert(4);
        bst.insert(7);
        bst.insert(9);
        bst.insert(3);
        bst.insert(5);

        int val1 = 3;
        int val2 = 1;
        BSTNode root = bst.root;
        System.out.print(bst.traverse(root));
        BSTNode p = bst.find(val1);
        BSTNode q = bst.find(val2);
        System.out.print(lowestCommonAncestor(root,p,q).val);
    }
}

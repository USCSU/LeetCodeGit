package LeetCode2015;

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val){
        this.val = val;
    }
}
public class invertTree {
    public static TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    //Wrong implementation
    public static TreeNode invertTree1WrongImplementation(TreeNode root){
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        TreeNode temp = left;
        left = right;
        right = temp;
        return root;
    }
    public static void print(TreeNode root){
        if(root == null) return;
        System.out.print(root.val+" ");
        print(root.left);
        print(root.right);
    }
    public static void main(String[] args){
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        c.left = b;b.left = a;
        c.right = d;d.right = e;
        print(c);
        invertTree(c);
        System.out.println();
        print(c);
    }

}

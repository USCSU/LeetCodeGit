package LeetCode;
class Tree{
    int val;
    Tree left;
    Tree right;
    Tree(int val){
        this.val = val;
        left = null;
        right = null;
    }
}
public class SameTree {
       public static boolean sameTree(Tree p, Tree q){
           if(p == null && q==null) return true;
           if(p == null || q==null) return false;
           if(p.val != q.val) return false;
           return  sameTree(p.left,q.left) && sameTree(p.right,q.right);
       }
}


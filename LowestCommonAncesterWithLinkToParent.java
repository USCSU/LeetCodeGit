package LeetCode;

/**
 * Created by Garvin on 10/19/2014.
 */
class TreeNodeWithLink{
    int val;
    TreeNodeWithLink parent;
    TreeNodeWithLink left;
    TreeNodeWithLink right;
    public TreeNodeWithLink(int val){
        this.val = val;
        parent = null;
        left = null;
        right = null;
    }
}
public class LowestCommonAncesterWithLinkToParent {
    public static TreeNodeWithLink getCommonAncester(TreeNodeWithLink root, TreeNodeWithLink a, TreeNodeWithLink b){
        if(root == null || a ==null || b==null) return new TreeNodeWithLink(-1); //error node
        int d1 = getDepth(root,a);
        int d2 = getDepth(root,b);
        if(d1 == -1 || d2 == -1) return new TreeNodeWithLink(-1); // node is not in the tree
        if(d1<d2)
            return helper(root,a,b, d2-d1);
        else
            return helper(root,b,a,d1-d2);

    }
    public static TreeNodeWithLink helper(TreeNodeWithLink root,TreeNodeWithLink a, TreeNodeWithLink b, int distance){
        while(distance!=0) {
            b=b.parent;
            distance--;
        }
        while(a!=b){
            a = a.parent;
            b = b.parent;
        }
        return a;
    }
    public static int getDepth(TreeNodeWithLink root, TreeNodeWithLink a){
        int depth = 0;
        while(a!=null && a!=root){
            a=a.parent;
            depth++;
        }

        return a==null?-1:depth;
    }
    public static void main(String[] args){
        TreeNodeWithLink a = new TreeNodeWithLink(1);
        TreeNodeWithLink b = new TreeNodeWithLink(2);
        TreeNodeWithLink c = new TreeNodeWithLink(3);
        TreeNodeWithLink d = new TreeNodeWithLink(4);
        TreeNodeWithLink e = new TreeNodeWithLink(5);
        TreeNodeWithLink f = new TreeNodeWithLink(6);
        TreeNodeWithLink g = new TreeNodeWithLink(7);
        TreeNodeWithLink h = new TreeNodeWithLink(8);
        a.left = b;
        a.right = c;
        b.parent =a;
        c.parent = a;
        b.left = d;
        b.right =e;
        d.parent =b;
        e.parent =b;
        c.left = f;
        c.right = g;
        f.parent = c;
        g.parent =c;
        System.out.println(getCommonAncester(a,f,g).val);
        System.out.println(getCommonAncester(a,g,b).val);
    }
}

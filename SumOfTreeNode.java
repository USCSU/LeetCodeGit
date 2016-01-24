package LeetCode;

import java.util.ArrayList;

/**
 * Created by Garvin on 10/14/2014.
 */
public  class SumOfTreeNode {


    public static int getSumOfTreeNode(TreeNode root){
        if(root == null) return 0;

        return getSumOfTreeNode(root.left)+root.val+getSumOfTreeNode(root.right);
    }
    public static int getSumOfTreeNodeByLevel(TreeNode root, int level){
        if(root == null) return 0;
        return getSumOfTreeNodeByLevel(root.right,level+1)+ root.val*level + getSumOfTreeNodeByLevel(root.left,level+1);
    }

    public ArrayList<Integer> spiralOrder(int[][] array){
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(array == null) return res;
        int row = array.length;
        int col = array[0].length;
        int top = 0, bottom = row-1, left = 0, right = col-1;
        while(true){
            for(int i = left;i<=right;i++)
                res.add(array[top][i]);
            if(++top > bottom) break;

            for(int i = top;i<=bottom;i++)
                res.add(array[i][right]);
            if(--right<left) break;

            //right - left
            for(int i = right;i>=left;i--)
                res.add(array[bottom][i]);
            if(--bottom < top) break;

            //bottom - up
            for(int i = bottom;i>=top;i--)
                res.add(array[i][left]);
            if(++left>right) break;
        }
        return res;
    }

    public int[][] generateMatrix(int n) {
        int k = 1;
        int[][] res = new int[n][n];
        int top = 0, bottom = n-1, left = 0, right = n-1;
        while(left<=right && top<=bottom){
            for(int i = left;i<right;i++)
                res[top][i] = k++;
            for(int i = top;i<bottom;i++)
                res[i][right] = k++;
            for(int i = right;i>left;i--)
                res[bottom][i] = k++;
            for(int i = bottom;i>top;i--)
                res[i][left] = k++;

            top++;
            bottom--;
            left ++;
            right--;
        }
        return res;
    }

    public static void main(String[] args) {

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        c.left = b;
        b.left = a;
        c.right = d;
        d.right = e;
        System.out.println(getSumOfTreeNode(a));
        System.out.println(getSumOfTreeNode(b));
        System.out.println(getSumOfTreeNode(c));
        System.out.println(getSumOfTreeNode(d));
        System.out.println(getSumOfTreeNode(e));
        System.out.println("-----------");
        System.out.println(getSumOfTreeNodeByLevel(a, 1));
        System.out.println(getSumOfTreeNodeByLevel(b, 1));
        System.out.println(getSumOfTreeNodeByLevel(c, 1));
        System.out.println(getSumOfTreeNodeByLevel(d, 1));
        System.out.println(getSumOfTreeNodeByLevel(e, 1));
        SumOfTreeNode re  = new SumOfTreeNode();
    }
}

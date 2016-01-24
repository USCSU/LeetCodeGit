package LeetCode2015;

import sun.plugin.javascript.navig.Array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

class BSTNode {
    int val;
    BSTNode left;
    BSTNode right;
    BSTNode(int val) {
        this.val = val;
    }
    BSTNode(){
        val = 0;
    }
}
class BST{
    BSTNode root;

    public void insert(int val){
        root = insert(root,val);
    }
    private BSTNode insert(BSTNode cur, int val){
        if(cur == null) {
            cur = new BSTNode(val);
            return cur;
        }
        if(val == cur.val) return cur;
        else if(val > cur.val) cur.right = insert(cur.right,val);
        else cur.left = insert(cur.left,val);
        return cur;
    }

    //very bad example: 指向堆里的指针，在栈里被销毁，所以无法返回
    private void insert1(BSTNode cur, int val){
        if(cur == null){
            cur = new BSTNode(val);
            return;
        }
        if(val == cur.val) return;
        else if(val > cur.val) insert1(cur.right,val);
        else insert1(cur.left,val);

    }
    public boolean has(int val){
        return has(root,val);
    }
    private boolean has(BSTNode cur, int val){
        if(cur == null) return false;
        if(val == cur.val) return true;
        else if(val > cur.val) return has(cur.right,val);
        else return has(cur.left,val);
    }
    public void remove(int val){
        root = remove(root,val);
    }
    private BSTNode remove(BSTNode cur, int val){
        if(cur == null) return cur;
        if(val > cur.val) cur.right = remove(cur.right,val);
        else if(val < cur.val) cur.left = remove(cur.left,val);
        else{
            if(cur.left!=null && cur.right !=null){
                cur.val = cur.left.val;
                cur.left = remove(cur.left,cur.val);
            }else{
                cur = cur.left == null?cur.right:cur.left;
            }
        }
        return cur;
    }
    public BSTNode find(int val){
        return find(val,root);
    }
    private BSTNode find(int val,BSTNode cur){
        if(root == null) return null;
        if(val == cur.val) return cur;
        else if(val > cur.val) return find(val,cur.right);
        else  return find(val,cur.left);
    }
    public ArrayList<Integer> traverse(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        ret.add(root.val);
        ret.addAll(traverse(root.left));
        ret.addAll(traverse(root.right));
        return ret;
    }
}
class Int{
    int val;
    Int(int val){
        this.val = val;
    }

}
public class KthNodeInBST {
    //preorder iterative
    public static ArrayList<Integer> preOrder(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        while(!stack.isEmpty() || root!=null){
            while(root!=null){
                ret.add(root.val);
                stack.push(root);
                root = root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                root = root.right;
            }
        }
        return ret;
    }
    public static ArrayList<Integer> preOrderII(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            ret.add(root.val);
            if(root.right!=null) stack.push(root.right);
            if(root.left!=null) stack.push(root.left);
        }
        return ret;
    }

    public static ArrayList<Integer> inOrder(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        while(!stack.isEmpty()||root!=null){
            while(root!=null){
                stack.push(root);
                root=root.left;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                ret.add(root.val);
                root = root.right;
            }
        }
        return ret;
    }
    public static ArrayList<Integer> postOrder(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        while(!stack.isEmpty() || root !=null){
            while(root!=null){
                ret.add(root.val);
                stack.push(root);
                root = root.right;
            }
            if(!stack.isEmpty()){
                root = stack.pop();
                root = root.left;
            }
        }
        Collections.reverse(ret);
        return ret;
    }
    public static ArrayList<Integer> postOrderII(BSTNode root){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        if(root == null) return ret;
        Stack<BSTNode> stack = new Stack<BSTNode>();
        stack.push(root);
        while(!stack.isEmpty()){
            root = stack.pop();
            ret.add(root.val);
            if(root.left!=null) stack.push(root.left);
            if(root.right!=null) stack.push(root.right);
        }
        Collections.reverse(ret);
        return ret;
    }

    public static void findPath(BSTNode root,Stack<BSTNode> stack){
        while(root!=null){
            stack.push(root);
            if(root.left!=null){
                root = root.left;
            }
            else root = root.right;
        }
    }
    public static ArrayList<Integer> postOrderIII(BSTNode root){
        Stack<BSTNode> stack = new Stack<BSTNode>();
        ArrayList<Integer> ret = new ArrayList<Integer>();
        findPath(root,stack);
        while(!stack.isEmpty()){
            root = stack.pop();
            ret.add(root.val);
            if(!stack.isEmpty()&&stack.peek().left == root){
                findPath(stack.peek().right,stack);
            }
        }
        return ret;
    }
    //iterative is useful : inorder
    public  static BSTNode findKthElement(BSTNode cur, int k){
       Stack<BSTNode> stack = new Stack<BSTNode>();
        while(!stack.isEmpty()||cur!=null){
            while(cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            //cur ==null
            if(!stack.isEmpty()){
                cur = stack.pop();
                if(k-- == 1) return cur;
                cur = cur.right;
            }
        }
        return null;

    }

    public static void main(String[] args) {
       BST bst = new BST();
        bst.insert(3);
        bst.insert(2);
        bst.insert(4);
        bst.insert(1);
        bst.insert(5);
        System.out.println(bst.traverse(bst.root));
        System.out.println(bst.has(5));
        System.out.println(bst.has(8));
//        bst.remove(4);
//        System.out.println(bst.traverse(bst.root));
//        bst.remove(1);
//        System.out.println(bst.traverse(bst.root));
        for(int i = 5;i>0;i--)
        System.out.println(findKthElement(bst.root,i).val);
        //preOrder iterative 1
        System.out.println(preOrder(bst.root));
        System.out.println(preOrderII(bst.root));
        System.out.println(inOrder(bst.root));
        System.out.println(postOrder(bst.root));
        System.out.println(postOrderII(bst.root));
        System.out.println(postOrderIII(bst.root));

    }


}

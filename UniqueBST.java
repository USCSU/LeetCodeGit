package LeetCode;

/**
 * Created by Garvin on 9/22/2014.
 */
public class UniqueBST {
    public static int UniqueBST(int n){
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i =2;i<=n;i++){
            for(int j = 0;j<i;j++){
                res[i] += res[j]*res[i-j-1];
            }
        }
        return res[n];
    }
    public static void main(String[] args){
        System.out.println(UniqueBST(2));
        System.out.println(UniqueBST(3));
        System.out.println(UniqueBST(4));

    }
}

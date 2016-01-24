package LeetCode;

/**
* Created by Garvin on 9/4/2014.
*/
public class findMedian {
    public static double findMedianSortedArrays(int[] a, int[] b){
        int m = a.length,n=b.length;
        if(((m+n)&1)==1)
            return helper(a,0,m,b,0,n,(m+n)/2+1);
        else{
            return (helper(a,0,m,b,0,n,(m+n)/2+1)+helper(a,0,m,b,0,n,(m+n)/2))/2.0;
        }
    }
    public static double helper(int[] a, int low, int m, int[] b, int start, int n, int k){
        if(m>n) return helper(b,start,n,a,low,m,k);
        if(m==0) return b[start+k-1];
        if(k==1) return Math.min(a[low],b[start]);
        int pa = Math.min(k/2,m);
        int pb = k-pa;
        if(a[low+pa-1]<b[start+pb-1]){//a[0] --> a[low+pa-1] will not be the answer, cut off
            return helper(a,low+pa,m-pa,b,start,n,k-pa);
        }else if(a[low+pa-1]>b[start+pb-1]){//cut off b's 1st half
            return helper(a,low,m,b,start+pb,n-pb,k-pb);
        }else{
            return a[low+pa-1];
        }
    }
    public static void main(String[] args){
        int[] a = {1,3,5,7};
        int[] b = {2,4,6,8};
        System.out.println(findMedianSortedArrays(a,b));
    }
}

package LeetCode;

/**
 * Created by Garvin on 10/19/2014.
 */
public class FindMinimumInRotatedArray {
    public static int findMin(int[] num, int low, int high){
        //error checking
        if(low==high) return num[low];
        //base
        if(num[low] < num[high])
            return num[low];
        int mid = low + (high-low)/2;
        if(num[mid] >= num[low])
            return findMin(num, mid+1,high);
        else
            return findMin(num,low,mid);
    }
    static int findMin1(int num[] )
    {
        int start=0,end=num.length-1;

        while (start<end) {
            if (num[start]<num[end])
                return num[start];

            int mid = (start+end)/2;

            if (num[mid]>=num[start]) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        System.out.println("!!!");
        return num[start];
    }
    public int findMin2(int[] A) {
        int L = 0, R = A.length - 1;
        while (L < R && A[L] >= A[R]) {
            int M = (L + R) / 2;
            if (A[M] > A[R]) {
                L = M + 1;
            } else if (A[M] < A[L]) {
                R = M;
            } else {   // A[L] == A[M] == A[R]
                L = L + 1;
            }
        }
        return A[L];
    }
    public static int findMin3(int[] num){
        int low = 0, high = num.length -1;
        while(low < high && num[low] >=num[high]){
            int mid = (low+high)/2;
            if(num[mid] > num[high]){ // right side ordered
                low = mid + 1;
            }else if(num[mid] < num[low]){ //left side ordered
                high = mid;
            }else{
                low = low +1;
            }
        }
        return num[low];
    }
    public static void main(String[] args){
        int[] num = {5, 6, 1, 2, 3, 4};
//        System.out.println(findMin(num, 0, num.length-1));
//        System.out.println(findMin1(num));
        System.out.println(findMin3(num));
        int[] num1 = {1, 2, 3, 4};
        System.out.println(findMin(num1, 0, num1.length-1));
        System.out.println(findMin3(num1));
//        System.out.println(findMin1(num1));
        int[] num2 = {2, 3, 4, 5, 6, 7, 8, 1};
        System.out.println(findMin(num2, 0, num2.length-1));
        System.out.println(findMin3(num2));
//        System.out.println(findMin1(num2));
    }
}

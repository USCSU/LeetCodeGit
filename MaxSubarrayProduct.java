package LeetCode;

/**
 * Created by Garvin on 9/25/2014.
 */
public class MaxSubarrayProduct {
    public static int maxProduct(int[] A) {
        int loc_min = 1, loc_max = 1;   // Make sure thoese local min/max values are greater than or equal to 1 all the time.
        int glb_max = A[0];
        for (int i : A) {
            if (i > 0) {
                glb_max = Math.max(glb_max, loc_max * i);
                loc_max *= i;
                loc_min *= i;
            } else if (i < 0) {
                glb_max = Math.max(glb_max, loc_min * i);
                int temp = loc_max;
                loc_max = Math.max(loc_min * i, 1);
                loc_min = temp * i;
            } else {    // i == 0.
                glb_max = Math.max(glb_max, 0);
                loc_max = 1;
                loc_min = 1;
            }
            System.out.println("local Max: "+loc_max );
            System.out.println("local Min: "+loc_min );
            System.out.println("global Max: "+glb_max );
            System.out.println("-----------");
        }

        return glb_max;
    }
    public static boolean willAdditionOverflow(Long left, Long right) {
        if (right < 0 && right != Integer.MIN_VALUE) {
            return willSubtractionOverflow(left, -right);
        } else {
            return (~(left ^ right) & (left ^ (left + right))) < 0;
        }
    }
    public static boolean willSubtractionOverflow(Long left, Long right) {
        if (right < 0) {
            return willAdditionOverflow(left, -right);
        } else {
            return ((left ^ right) & (left ^ (left - right))) < 0;
        }
    }
    public static void main(String[] args){
        int[] a = {-2,0,1};
//        int[] a = {2,3,-2,4};
        maxProduct(a);
     }
}

package LeetCode2015;

/**
 * Created by Trace_route on 7/7/15.
 */
public class ComputeArea {
    static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int p1x = Math.max(A,E);
        int p1y = Math.max(B,F);
        int p2x = Math.min(C,G);
        int p2y = Math.min(D,H);
        return (D-B)*(C-A) + (G-E)*(H-F) - (p1x>p2x||p1y>p2y?0:(p2x - p1x)*(p2y - p1y));
    }
    public static void main(String[] args){
        System.out.println(computeArea(-2, -2, 2, 2, -1, 4, 1, 6));
    }
}

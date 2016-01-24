package LeetCode;

import java.util.HashMap;

/**
 * Created by Trace_route on 11/24/14.
 */
public class maxPoint {
    public static double slope(Point p1, Point p2){
        int x = p2.x - p1.x;
        int y = p2.y - p1.y;
        if(y == 0) return Double.POSITIVE_INFINITY;
        return (double)y/(double)x;
    }
    public static boolean equal(Point x, Point y){
        return x.x == y.x && x.y == y.y;
    }
    public static int maxPoints(Point[] points) {
        if(points == null || points.length<1) return 0;
        int max = 0;
        HashMap<Double,Integer> map = new HashMap<Double, Integer>();
        for(int i =0;i<points.length-1;i++) {
            int equal = 1;
            for(int j=i+1;j<points.length;j++){
                if(equal(points[i],points[j])){
                    equal++;
                    continue;
                }
                double slope = slope(points[i],points[j]);
                if(map.containsKey(slope)){
                    map.put(slope,map.get(slope)+1);
                }else{
                    map.put(slope,1);
                }
            }
            int tmp = 0;
            for(Integer val:map.values()){
                tmp = Math.max(tmp,val);
            }
            max = Math.max(max, tmp+equal);
            map.clear();
        }
        return max;
    }
    public static void main(String[] args){
        Point a = new Point(0,0);
        Point b = new Point(1,1);
        Point c = new Point(2,2);
        Point[] p = {a,b,c};
        System.out.println(maxPoints(p));
    }
}

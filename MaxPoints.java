package LeetCode;

import java.util.HashMap;

/**
 * Created by Garvin on 10/16/2014.
 */
class Point{
    int x;
    int y;
    Point(){
        x = 0;
        y =0;
    }
    Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}
public class MaxPoints {
    public static int maxPoints(Point[] points) {
        int max = 0;
        HashMap<Double,Integer> map = new HashMap<Double, Integer>();
        for(int i =0;i<points.length-1;i++){
            int equal =1;
            for(int j =i+1;j<points.length;j++){
                if(equals(points[i],points[j])){
                    equal++;
                    continue;
                }
                double slope = getSlope(points[i],points[j]);
                if(map.containsKey(slope)){
                    map.put(slope,map.get(slope)+1);
                }else{
                    map.put(slope,1);
                }
            }
            int tmp = 0;
              for (Integer val : map.values()) {
                  tmp = Math.max(tmp, val);
              }
              max = Math.max(max, tmp + equal);
              map.clear();
        }
        return max;
    }
    public static Double getSlope(Point a, Point b){
        int x = b.x - a.x;
        int y = b.y - a.y;
        if(x == 0) return Double.POSITIVE_INFINITY;
        return (double)y/(double)x;
    }
    public static boolean equals(Point a, Point b){
        if(a.x == b.x && a.y == b.y) return true;
        return false;
    }

    public static void main(String[] args){
        Point a = new Point(0,0);
        Point b = new Point(1,1);
        Point c = new Point(2,3);
        Point[] p = {a,b,c};
        System.out.println(maxPoints(p));
    }

}

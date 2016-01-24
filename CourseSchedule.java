package LeetCode2015;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Trace_route on 7/7/15.
 */
public class CourseSchedule {
    //dfs post order
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //traverse every course, and find if there is any cycle
        //if so, return false; if not, return true;
        HashMap<Integer,LinkedList<Integer>> graph = prepross(prerequisites, numCourses);
        int[] checker = new int[numCourses];
        for(int i =0;i<numCourses;i++){
            if(checker[i] == 0) //some graph is a forest
                if(cycleExist(checker,graph,i)) return false; //if cycle exist, false
        }
        return true;
    }
    public static boolean cycleExist(int[] checker, HashMap<Integer,LinkedList<Integer>> graph, int index){
        LinkedList<Integer> nodes = graph.get(index);
        for(int node:nodes){
            if(checker[node] == -1) return true;
            if(checker[node] == 1) continue;
            checker[node] = -1;
            if(cycleExist(checker,graph,node)) return true;
            checker[node] = 1;
        }
        return false;
    }
    //build adjacent list
    public static HashMap<Integer,LinkedList<Integer>> prepross(int[][] matrix, int num){
        HashMap<Integer,LinkedList<Integer>> ret = new HashMap<Integer, LinkedList<Integer>>();
        for(int i =0;i<num;i++) ret.put(i,new LinkedList<Integer>());
        for(int[] course:matrix){
            ret.get(course[1]).add(course[0]);
        }
        return ret;
    }
    //course schedule II
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer,LinkedList<Integer>> graph = prepross(prerequisites, numCourses);
        int[] checker = new int[numCourses];
//        int[] ret = new int[numCourses];
        ArrayList<Integer> collector = new ArrayList<Integer>();
        for(int i =0;i<numCourses;i++){
            if(checker[i] == 0)
                if(getOrder(checker,collector,graph,i)) return new int[0]; //if cycle exist, false
        }
        int[]  ret = new int[collector.size()];
        for(int i =0;i<ret.length;i++)
            ret[i] = collector.get(ret.length-i-1);
        return ret;
    }

    public static boolean getOrder(int[] checker, ArrayList<Integer> collector, HashMap<Integer,LinkedList<Integer>> graph , int index){
        LinkedList<Integer> nodes = graph.get(index);
        if(checker[index] == -1) return true;
        if(checker[index] == 1) return false;
        checker[index] = -1;
        for(int node: nodes ){
            if(getOrder(checker,collector,graph,node)) return true;
        }
        checker[index] = 1;
        collector.add(index);
        return false;
    }
    public static void main(String[] agrs){
//        int[][] pre = {{1,0},{2,0},{3,1},{3,2}};
        int[][] pre = {{0,1}};
        int num = 2;
        System.out.println(canFinish(num,pre));
        System.out.println(Arrays.toString(findOrder(num, pre)));
    }
}

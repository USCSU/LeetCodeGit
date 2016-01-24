package LeetCode;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Garvin on 9/20/2014.
 */
public class Tarjan_Alg {
    static int index = 0;
    public static ArrayList<ArrayList<Integer>> tarjan(List<Integer>[] g){
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int len = g.length;
        int[] low = new int[len];
        boolean[] visited = new boolean[len];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i =0;i<len;i++){
            if(!visited[i])
                dfs(g,res,visited,stack,low,i);
        }
        return res;

    }
    public static void dfs(List<Integer>[] g,ArrayList<ArrayList<Integer>> res, boolean[] visited, Stack<Integer> stack, int[] low, int u){
        low[u] = index++;
        visited[u] = true;
        stack.push(u);
        int min = low[u];
        for(Integer v:g[u]){
            if(!visited[v])
                dfs(g,res,visited,stack,low,v);
            if(low[v]<min){
                min = low[v];
            }
        }
        //means there is strong connected component,should keep checking
        if(low[u] > min){
            low[u] = min;
            return;
        }
        //pop strong connected component
        ArrayList<Integer> scc = new ArrayList<Integer>();
        int node;
        do{
            System.out.println("+++"+u);
            node = stack.pop();
            scc.add(node);
//            low[node] = g.length;
        }while(node!=u);
        res.add(scc);
    }

    //tarjan own version
    public static ArrayList<ArrayList<Integer>> tarjanFinalVersion(List<Integer>[] g){
        int n = g.length;
        boolean[] onstack = new boolean[n];
        int[] low = new int[n];
        int[] cur = new int[n];
        Stack<Integer> stack = new Stack<Integer>();
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[n];
        for(int i =0;i<n;i++){
            if(!visited[i])
                helperTarjan(g,res,stack,onstack,low,cur,i,visited);
        }
        return res;
    }
    public static void helperTarjan(List<Integer>[] g, ArrayList<ArrayList<Integer>> res, Stack<Integer> stack, boolean[] onstack,int[] low, int[] cur,int u,boolean[] visited){
        onstack[u] = true;
        visited[u] = true;
        low[u] = cur[u] = ++index;
        stack.push(u);
        for(Integer v:g[u]){
            if(!visited[v]){
                helperTarjan(g,res,stack,onstack,low,cur,v,visited);
                low[u] = Math.min(low[u],low[v]);
            }else if(onstack[u]){
                low[u] = Math.min(low[u],cur[v]);
            }
        }
        if(low[u] == cur[u]){
            ArrayList<Integer> temp = new ArrayList<Integer>();
            int w;
            do{
                w = stack.pop();
                temp.add(w);
                onstack[w] = false;
                low[w] = g.length;
            }while(u!=w);
            res.add(temp);
         }

    }
    public static void main(String[] args){
        List<Integer>[] g = new List[6];
        for(int i =0;i<6;i++)
            g[i] = new LinkedList<Integer>();

//        g[0] .add(2);
//        g[0] .add(1);
//        g[1].add(3);
//        g[2].add(3);
//        g[3].add(0);
//        g[3].add(5);
//        g[2].add(4);
//        g[4].add(5);
        g[0].add(1);
        g[1].add(2);
        g[2].add(0);
//        System.out.println(tarjanWiki(g));
        System.out.println(tarjanFinalVersion(g));

    }
}

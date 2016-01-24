package LeetCode;


import java.util.*;

public class TopologicalSortTest {
    public static String KahnTopoSort(Node[] allNodes){
        ArrayList<String> res = new ArrayList<String>();
        LinkedList<Node> zeroIn = new LinkedList<Node>();
        //fill in all node with zero in degree
        for(Node n:allNodes){
            if(n.inEdges.isEmpty())
                zeroIn.add(n);
        }
        //topo sort
        while(!zeroIn.isEmpty()){
            Node top = zeroIn.removeFirst();
            for(Edge e: top.outEdges){
                e.to.inEdges.remove(e);
                if(e.to.inEdges.isEmpty())
                    zeroIn.add(e.to);
            }
            res.add(top.toString());
        }
        //detect cycle
        boolean cycle = false;
        for(Node n:allNodes){
            if(!n.inEdges.isEmpty()) {
                cycle = true;
                break;
            }
        }
        //deal with cycle
        if(cycle) return "Cycle detected in Kahn algorithm";
        else      return res.toString();
    }
    //normal DFS in topological sort : no cycle detected
    public static String DFSTopoSort(List<Integer>[] graph){
        Stack<Integer> res = new Stack<Integer>();
        boolean[] vistited = new boolean[graph.length];
        //for each element, search path.
        for(int i =0;i<graph.length;i++){
            if(!vistited[i])
                DFSTopoSortHelper(graph,res,vistited,i);
        }
        Collections.reverse(res);
        return res.toString();
    }

    public static void DFSTopoSortHelper(List<Integer>[] graph, Stack<Integer> res, boolean[] visited, int index){
        visited[index] = true;
        for(Integer i:graph[index]){
            if(!visited[i])
                DFSTopoSortHelper(graph,res,visited,i);
        }
        res.add(index);
    }

    //simple function to detect cycle with updated DFS
    public static boolean detectCycle(List<Integer>[] graph){
        int[] visited = new int[graph.length];
        for(int i =0;i<graph.length;i++){
            if(detectCycleHelper(graph,visited,i))
                return true;
        }
        return false;
    }
    public static boolean detectCycleHelper(List<Integer>[] graph, int[] visited, int index){
        visited[index] = graph[index].isEmpty()?1:-1;
        for(Integer i:graph[index]){
            if(visited[i] == -1) return true;
            visited[i] = -1;
            if(detectCycleHelper(graph,visited,i)) return true;
            visited[i] = 1;
        }
        return false;
    }

    //Topological Sort with Cycle detector
    public static String TopoSortWithCycleDetector(List<Integer>[] graph){
        int[] visited = new int[graph.length];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i =0;i<graph.length;i++){
            if(visited[i] ==0)
                if(TopoSortWithCycleDetectorHelper(graph,visited,stack,i))
                    return "Cycle detected in Detector func";
        }
        Collections.reverse(stack);
        return stack.toString();
    }
    public static boolean TopoSortWithCycleDetectorHelper(List<Integer>[] graph,int[] visited, Stack<Integer> res,int index){
        visited[index] = graph[index].isEmpty()?1:-1;
        for(Integer i:graph[index]){
            if(visited[i] == -1) return true;
            if(visited[i] ==0){
                visited[i] = -1;
                if(TopoSortWithCycleDetectorHelper(graph,visited,res,i)) return true;
                visited[i] = 1;
            }
        }
        res.add(index);
        return false;
    }



    //test of detector
    public static String topoDetector(List<Integer>[] g){
        int[] visited = new int[g.length];
        LinkedList<Integer> res = new LinkedList<Integer>();
        for(int i =0;i<g.length;i++){
            if(visited[i] == 0)
                if(topoDetectorHelper(g,res,visited,i)) return "Cycle detected in All new topo test";
        }
        Collections.reverse(res);
        return res.toString();
    }
    public static boolean topoDetectorHelper(List<Integer>[] g,LinkedList<Integer> list, int[] visited,int index){
        visited[index] = g[index].isEmpty()?1:-1;
        for(Integer i:g[index]){
            if(visited[i] == -1) return true;
            if(visited[i] == 0){
                visited[i] = -1;
                if(topoDetectorHelper(g,list,visited,i)) return true;
                visited[i] = 1;
            }
        }
        list.add(index);
        return false;
    }

    static int index = 0;
    public static ArrayList<ArrayList<Integer>> checkCycleWithTarjan(List<Integer>[] g){
        int len = g.length;
        int[] low = new int[len];
        int[] cur = new int[len];
        boolean[] onstack = new boolean[len];
        boolean[] visited = new boolean[len];
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i =0;i<g.length;i++){
            if(!visited[i]){
                tarjanCycleHelper(g, low, cur, onstack, visited, stack, res,i);
            }
        }
       return res;
    }

    //low指的是能到达的最早序号是什么，cur是时间戳。如果low = cur,说明到达联通分量的根，输出所有栈中元素，停止条件是u和v相等。时间复杂度 O（n+m）
     //https://www.byvoid.com/blog/scc-tarjan
    public static void tarjanCycleHelper(List<Integer>[] g, int[] low, int[] cur, boolean[] onstack, boolean[] visited,Stack<Integer> stack, ArrayList<ArrayList<Integer>> res, int u){
        low[u] = cur[u] = ++index;
        onstack[u] = true;
        visited[u] = true;
        stack.push(u);
        for(Integer v:g[u]){
            if(!visited[v]){
                tarjanCycleHelper(g,low,cur,onstack,visited,stack,res,v);
                low[u] = Math.min(low[u],low[v]);
            }else if(onstack[v]){
                low[u] = Math.min(low[u],cur[v]);
            }
        }
        if(low[u] == cur[u]){

            ArrayList<Integer> temp = new ArrayList<Integer>();
            int w;
            do{
                w= stack.pop();
                temp.add(w);
                onstack[w] = false;
                low[w] = g.length;
            }while(u!=w);
//            if(temp.size()>1)
                res.add(temp);
        }
    }
    public static void main(String[] args){
        Node seven = new Node("7");
        Node five = new Node("5");
        Node three = new Node("3");
        Node eleven = new Node("11");
        Node eight = new Node("8");
        Node two = new Node("2");
        Node nine = new Node("9");
        Node ten = new Node("10");
        seven.addEdge(eleven).addEdge(eight);
        five.addEdge(eleven);
        three.addEdge(eight).addEdge(ten);
        eleven.addEdge(two).addEdge(nine).addEdge(ten);
        eight.addEdge(nine).addEdge(ten);
        Node a = new Node("1");
        Node b = new Node("2");
        Node c = new Node("3");
        a.addEdge(b);
        b.addEdge(c);
        c.addEdge(a);
        Node[] allNodes = {seven, five, three, eleven, eight, two, nine, ten};
        Node[] allNodesCycle = {a,b,c};
        System.out.println("Kahn algorithm for Topological sort:");
        System.out.println(KahnTopoSort(allNodes));
        System.out.println(KahnTopoSort(allNodesCycle));



        //-------
        int n = 8;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        g[0].add(2);g[0].add(3);g[0].add(4);
        g[2].add(4);g[2].add(5);g[2].add(6);
        g[3].add(5);
        g[4].add(7);

        List<Integer>[] g1 = new List[3];
        for (int i = 0; i < 3; i++) {
            g1[i] = new ArrayList<Integer>();
        }
        g1[0].add(1);g1[1].add(2);g1[2].add(0);


        System.out.println("DFS for topological sort:");
        System.out.println(DFSTopoSort(g));
        //g1 is a graph contains cycle
        System.out.println(DFSTopoSort(g1));
        System.out.println("Detect Cycle in simple function:");
        System.out.println(detectCycle(g1));
        //Final version
        System.out.println("Topological sort with cycle detector");
        System.out.println(checkCycleWithTarjan(g1));
        System.out.println(checkCycleWithTarjan(g));
        System.out.println(topoDetector(g1));
        System.out.println(topoDetector(g));
    }
}

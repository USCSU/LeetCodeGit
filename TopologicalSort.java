package LeetCode;

/**
* Created by Garvin on 9/17/2014.
*/
import java.util.*;

public class TopologicalSort {

    //Kahn topological sort algorithm
    public static String getTopologicalSort(Node[] allNodes){

        ArrayList<String> output = new ArrayList<String>();
        LinkedList<Node> zeroInNode = new LinkedList<Node>();
        //find all zero in node
        for(Node n:allNodes){
            if(n.inEdges.size()==0)
                zeroInNode.add(n);
        }
        //get topological sort
        while(!zeroInNode.isEmpty()){
            Node temp = zeroInNode.removeFirst();
            output.add(temp.toString());
            for(Edge e:temp.outEdges){
                e.to.inEdges.remove(e);
                if(e.to.inEdges.isEmpty())
                    zeroInNode.add(e.to);
            }
        }
        //detect cycle
        boolean cycle = false;
        for(Node n:allNodes){
            if(n.inEdges.size()!=0){
                cycle = true;
                break;
            }

        }
        if(cycle)
            return "cycle detected";
        else{
            return output.toString();
        }
    }
    public static String DFSTopo(List<Integer>[] graph){
        //output result
        Stack<Integer> stack = new Stack<Integer>();
        //check visited or not to avoid abundant check
        boolean[] visited = new boolean[graph.length];
        //check every digit of graph
        for(int i =0;i<graph.length;i++){
            if(!visited[i]) {
                helper(graph, i, stack, visited);
            }
        }
        Collections.reverse(stack);
        return stack.toString();
    }
    public static void helper(List<Integer>[] graph,int index, Stack<Integer> res,boolean[] visited){
        visited[index] = true;
        for(Integer i:graph[index]){
            if(!visited[i])
                helper(graph,i,res,visited);
        }
        res.add(index);
    }
    public static boolean detectCylcle(List<Integer>[] graph){
        int[] visited = new int[graph.length];
        for(int i =0 ;i<graph.length;i++){

            if(helper(graph,visited,i)) return true;
        }
        return false;
    }
    public static boolean helper(List<Integer>[] graph,int[] visited, int index){
        for(Integer i:graph[index]){
            if(visited[i] == -1) return true;
            visited[i] = -1;
            helper(graph,visited,i);
            visited[i] = 1;
        }
        return false;
    }
    static boolean cycle= false;
    public static String DFSTopoWithCycleDetectionVoid(List<Integer>[] graph){

        //output result
        Stack<Integer> stack = new Stack<Integer>();
        //check visited or not to avoid abundant check
        int[] visited = new int[graph.length];
        //check every digit of graph
        for(int i =0;i<graph.length;i++){
            if(visited[i] ==0) {
                helperWtihCycleDetectionVoid(graph, i, stack, visited);
                if(cycle) return "cycle detected";
            }
        }
        Collections.reverse(stack);
        return stack.toString();
    }
    public static void helperWtihCycleDetectionVoid(List<Integer>[] graph,int index, Stack<Integer> res,int[] visited){
        if(cycle) return;
        for(Integer i:graph[index]){
            if(visited[i] == -1){
                cycle = true;
                return;
            }
            if(visited[i]==0) {
                visited[i] = -1;
                helperWtihCycleDetectionVoid(graph,i,res,visited);
                visited[i] = 1;
            }
        }
        res.add(index);
    }
    public static String DFSTopoWithCycleDetection(List<Integer>[] graph){

        //output result
        Stack<Integer> stack = new Stack<Integer>();
        //check visited or not to avoid abundant check
        int[] visited = new int[graph.length];
        //check every digit of graph
        for(int i =0;i<graph.length;i++){
            if(visited[i] ==0) {
//                helperWtihCycleDetection(graph, i, stack, visited);
//                if(cycle) return "cycle detected";
                if(helperWtihCycleDetection(graph, i, stack, visited))
                    return "cycle dte";
            }
        }
        Collections.reverse(stack);
        return stack.toString();
    }
    public static boolean helperWtihCycleDetection(List<Integer>[] graph,int index, Stack<Integer> res,int[] visited){
        for(Integer i:graph[index]){
            if(visited[i] == -1){
//                cycle = true;
//                return;
                return true;
            }
            if(visited[i]==0) {
                visited[i] = -1;
                if(helperWtihCycleDetection(graph,i,res,visited)) return true;
                visited[i] = 1;
            }
        }
        res.add(index);
        return false;
    }



    public static void main(String[] args) {
        Node a = new Node("1");
        Node b = new Node("2");
        Node c = new Node("3");
        a.addEdge(b);
        b.addEdge(c);
        c.addEdge(a);
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

        Node[] allNodes = {a, b, c};
//        Node[] allNodes = {seven, five, three, eleven, eight, two, nine, ten};
        System.out.println(getTopologicalSort(allNodes));
//
        int n = 8;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        g[0].add(2);g[0].add(3);g[0].add(4);
        g[2].add(4);g[2].add(5);g[2].add(6);
        g[3].add(5);
        g[4].add(7);

//        g[0].add(1);g[1].add(2);g[2].add(0);
        System.out.println(DFSTopo(g));
        System.out.println(DFSTopoWithCycleDetection(g));
        System.out.println(DFSTopoWithCycleDetectionVoid(g));
    }

}

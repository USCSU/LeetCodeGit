package LeetCode;

/**
* Created by Garvin on 9/17/2014.
*/
import java.util.*;

class Node{
    public   String name;
    public final HashSet<Edge> inEdges;
    public final HashSet<Edge> outEdges;
    public Node(){
        inEdges = new HashSet<Edge>();
        outEdges = new HashSet<Edge>();
    }
    public Node(String name) {
        this.name = name;

        inEdges = new HashSet<Edge>();
        outEdges = new HashSet<Edge>();
    }
    public Node addEdge(Node node){
        Edge e = new Edge(this, node);
        outEdges.add(e);
        node.inEdges.add(e);
        return this;
    }
    @Override
    public boolean equals(Object obj) {
        Node n = (Node)obj;
        return n.name == this.name;
    }
    public String toString() {
        return name;
    }
}

 class Edge{
    public final Node from;
    public final Node to;
    public Edge(){
        from = null;
        to = null;
    }
    public Edge(Node from, Node to) {
        this.from = from;
        this.to = to;
    }
    @Override
    public boolean equals(Object obj) {
        Edge e = (Edge)obj;
        return e.from == from && e.to == to;
    }
}
public class KahnTopologicalSort {
    public static String Kahan(Node[] all){
        LinkedList<Node> zero = new LinkedList<Node>();
        ArrayList<String> output = new ArrayList<String>();
        //fill zero
        for(Node node:all){
            if(node.inEdges.isEmpty())
                zero.add(node);
        }
        while(!zero.isEmpty()){
            Node top = zero.removeFirst();
            for(Edge e: top.outEdges){
                e.to.inEdges.remove(e);
                if(e.to.inEdges.isEmpty())
                    zero.add(e.to);
            }
        }
        //detect cycle
        boolean neg = false;
        for(Node d: all)
            if(!d.inEdges.isEmpty()){
                neg = true;
                break;
            }
        if(neg) return "cycle detected";
        else return output.toString();
    }
    public static String DFS(List<Integer>[] g){
        boolean[] visited = new boolean[g.length];
        LinkedList<Integer> res = new LinkedList<Integer>();
        for(int i = 0;i<g.length;i++){
            if(!visited[i])
                DFSHelper(g,res,visited,i);
        }
        Collections.reverse(res);
        return res.toString();
    }
    public static void DFSHelper(List<Integer>[] g, LinkedList<Integer> res, boolean[] visited, int index){
        visited[index] = true;
        for(Integer i:g[index]){
            if(!visited[i])
                DFSHelper(g,res,visited,i);
        }
        res.add(index);
    }

    public static boolean DfsCycle(List<Integer>[] g){
        int[] visited = new int[g.length];
        for(int i =0;i<g.length;i++){
            if(visited[i] == 0)
                if(DfsCycle(g, visited, i)) return true;
        }
        return false;
    }
    public static boolean DfsCycle(List<Integer>[] g, int[] visited, int index){
        visited[index] = -1;
        for(Integer i:g[index]){
            if(visited[i] == -1) return false;
            visited[i] = -1;
            if(DfsCycle(g,visited,i)) return true;
            visited[i] = 1;
        }
        return false;
    }

    public static String topoDetec(List<Integer>[] g){
        int[] visited = new int[g.length];
        LinkedList<Integer> res = new LinkedList<Integer>();
        for(int i =0;i<g.length;i++){
            if(visited[i] == 0)
                if(detecHelper(g,visited,res,i)) return "cycle detected";
        }
        Collections.reverse(res);
        return res.toString();

    }

    public static boolean detecHelper(List<Integer>[] g, int[] visited, LinkedList<Integer> res,int index){
        visited[index] = g[index].isEmpty()?1:-1;
        for(Integer i : g[index]){
            if(visited[i] == -1) return false;
            if(visited[i] == 0)
                visited[i] = -1;
                if(detecHelper(g,visited,res,i)) return true;
                visited[i] = 1;
        }
        res.add(index);
        return false;
    }
public static String kahanTopoSort(Node[] allNodes){
    //set zeroInNode and output
    LinkedList<Node> zero = new LinkedList<Node>();
    ArrayList<String> output = new ArrayList<String>();
    //filter and gather information for zero in degree nodes
    for(Node d:allNodes){
        if(d.inEdges.size() == 0)
            zero.add(d);
    }
    // process
    while(!zero.isEmpty()){
        Node node = zero.removeFirst();
        HashSet<Edge> set = node.outEdges;
        for(Edge e:set){
            e.to.inEdges.remove(e);
            if(e.to.inEdges.size()==0)
                zero.add(e.to);
        }
    }
    //detect cycle
    boolean cycle = false;
    for(Node d: allNodes){
        if(!d.inEdges.isEmpty()) {
            cycle = true;
            break;
        }
    }
    if(cycle)
        return "cycle detected";
    else
        return output.toString();


}

    public static String getTopologicalOutput(Node[] allNodes){
        LinkedList<Node> zeroInNode = new LinkedList<Node>();
        ArrayList<String> output = new ArrayList<String>();
        for(Node n:allNodes){
            if(n.inEdges.size()==0) {
                zeroInNode.add(n);
            }
        }
        while(!zeroInNode.isEmpty()){
            Node top = zeroInNode.removeFirst();

            output.add(top.toString());
            HashSet<Edge> outedge = top.outEdges;
            for(Edge e:outedge){
                e.to.inEdges.remove(e);
                if(e.to.inEdges.size()==0)
                    zeroInNode.add(e.to);
            }
        }
        boolean cycle = false;
        for(Node n:allNodes){
            if(n.inEdges.size()!=0) {
                cycle = true;
                break;
            }
        }
        if(cycle)
            return "cycle existed";
        else{
            return output.toString();
        }
    }
    public static void main(String[] args) {
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


//    Node[] allNodes = {a,b,c};
        Node[] allNodes = {seven, five, three, eleven, eight, two, nine, ten};
       System.out.println( getTopologicalOutput(allNodes));

    }
}

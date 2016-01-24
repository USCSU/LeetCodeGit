package LeetCode;

import java.util.*;

/**
 * Created by Garvin on 10/5/2014.
 */
class Vertex{
    char val;
    private ArrayList<GEdge> adjacents;
    private Vertex parent;
    private int len;
    boolean visited;
    public Vertex(char val){
        this.val = val;
        adjacents = new ArrayList<GEdge>();
        parent = null;
        len = Integer.MAX_VALUE;
        visited = false;
    }
    public void addEdge(Vertex to, int val){
        this.adjacents.add(new GEdge(this,to,val));
    }
    public ArrayList<GEdge> getAdjacents(){
        return adjacents;
    }
    public Vertex getParent(){
        return parent;
    }
    public void setParent(Vertex parent){
        this.parent = parent;
    }
    public int getLen(){
        return len;
    }
    public void setLen( int len){
        this.len = len;
    }


}
class GEdge{
    Vertex from;
    Vertex to;
    int val;
    public GEdge(Vertex from, Vertex to, int val){
        this.from = from;
        this.to = to;
        this.val = val;
    }
}
public class Dijkstra {
    public static void dijstra(Vertex start, HashSet<Vertex> g){
        Comparator<Vertex> cmp = new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.getLen() - o2.getLen();
            }
        };
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(g.size(),cmp);
        pq.add(start);
        start.setLen(0);
        while(!pq.isEmpty()){
            Vertex top = pq.poll();
            top.visited = true;
            ArrayList<GEdge> adjacent = top.getAdjacents();
            for(GEdge item:adjacent){
                if(item.to.visited) continue;
                if(item.from.getLen()+item.val < item.to.getLen()){
                    item.to.setParent(item.from);
                    item.to.setLen(item.from.getLen()+item.val);
                }
                pq.add(item.to);
            }
        }
    }
    public static void getPath( Vertex end){
        if(end.getParent()==null) return;
        System.out.println(end.val);
        getPath(end.getParent());
    }
    public static void main(String[] args){
        HashSet<Vertex> graph = new HashSet<Vertex>();
        Vertex a = new Vertex('A');
        Vertex b = new Vertex('B');
        Vertex c = new Vertex('C');
        Vertex d= new Vertex('D');
        Vertex e = new Vertex('E');
        Vertex f = new Vertex('F');
        Vertex g = new Vertex('G');
        Vertex h = new Vertex('H');
        a.addEdge(b,20);
        a.addEdge(d,80);
        a.addEdge(g,90);
        b.addEdge(f,10);
        c.addEdge(h,20);
        c.addEdge(f,50);
        c.addEdge(d,10);
        d.addEdge(c,10);
        d.addEdge(g,20);
        e.addEdge(g,30);
        e.addEdge(b,50);
        f.addEdge(c,10);
        f.addEdge(d,40);
        g.addEdge(a,20);
        graph.add(a);
        graph.add(b);
        graph.add(c);
        graph.add(d);
        graph.add(e);
        graph.add(f);
        graph.add(g);
        graph.add(h);

        dijstra(a, graph);
        getPath(g);

    }
}

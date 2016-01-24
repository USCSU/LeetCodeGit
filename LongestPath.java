package LeetCode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Garvin on 9/22/2014.
 */
class WeightNode{
    public   String name;
    public int total;
    public final HashSet<WeightEdge> inEdges;
    public final HashSet<WeightEdge> outEdges;
    public WeightNode(String name) {
        this.name = name;
        total =0;
        inEdges = new HashSet<WeightEdge>();
        outEdges = new HashSet<WeightEdge>();
    }
    public WeightNode addEdge(WeightNode node, int weight){
        WeightEdge e = new WeightEdge(this, node, weight);
        outEdges.add(e);
        node.inEdges.add(e);
        return this;
    }
    @Override
    public boolean equals(Object obj) {
        WeightNode n = (WeightNode)obj;
        return n.name == this.name;
    }
    public String toString() {
        return name;
    }
}

class WeightEdge{
    public final WeightNode from;
    public final WeightNode to;
    public int weight;

    public WeightEdge(WeightNode from, WeightNode to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
    @Override
    public boolean equals(Object obj) {
        WeightEdge e = (WeightEdge)obj;
        return e.from == from && e.to == to&&e.weight == weight;
    }
}

public class LongestPath {
    public static int getLongestPath(WeightNode [] allNode){
        LinkedList<WeightNode> zero = new LinkedList<WeightNode>();
        LinkedList<WeightNode> res = new LinkedList<WeightNode>();
        int[] path = new int[allNode.length];
        boolean cycle = false;
        //fill zero
        for(WeightNode node:allNode){
            if(node.inEdges.isEmpty())
                zero.add(node);
        }
        //get topological sort order
        while(!zero.isEmpty()){
            WeightNode top = zero.removeFirst();
            res.add(top);

            for(WeightEdge e:top.outEdges){
                e.to.inEdges.remove(e);
                e.to.total = Math.max(e.from.total+e.weight,e.to.total);
                if(e.to.inEdges.isEmpty())
                    zero.add(e.to);
            }
        }
        //cycle detected
        for(WeightNode node:allNode){
            if(!node.inEdges.isEmpty()){
                cycle = true;
                break;
            }
        }
        if(cycle) return -1;
        else {
            for(WeightNode t:res)
                System.out.println(t.toString()+" --> "+ t.total);
            return res.getLast().total;
        }

    }
    public static void main(String[] args){
        WeightNode zero = new WeightNode("0");
        WeightNode one = new WeightNode("1");
        WeightNode two = new WeightNode("2");
        WeightNode three = new WeightNode("3");
        WeightNode four = new WeightNode("4");
        WeightNode five = new WeightNode("5");
        WeightNode six = new WeightNode("6");
        WeightNode seven = new WeightNode("7");
        zero.addEdge(two,7);
        zero.addEdge(one,6);
        two.addEdge(four,4);
        one.addEdge(four,5);
        one.addEdge(three,3);
        four.addEdge(three,3);
        four.addEdge(five,4);
        four.addEdge(six,3);
        three.addEdge(five,2);
        three.addEdge(seven,5);
        five.addEdge(seven,2);
        six.addEdge(seven,4);
        WeightNode[] allNode = {zero,one,two,three,four,five,six,seven};
        System.out.println(getLongestPath(allNode));

    }
}

package LeetCode;

import java.util.HashMap;

/**
 * Created by Garvin on 10/22/2014.
 */
class DoubleLinkedNode{
    DoubleLinkedNode next;
    DoubleLinkedNode pre;
    int key;
    int value;
    public DoubleLinkedNode(int key, int value){
        this.key = key;
        this.value = value;
    }
}
public class LRU {
    HashMap<Integer,DoubleLinkedNode> map = new HashMap<Integer, DoubleLinkedNode>();
    DoubleLinkedNode head;
    DoubleLinkedNode end;
    int len;
    int capcity;
    public LRU(int capcity){
        this.capcity = capcity;
        len =0;
    }
    public void removeNode(DoubleLinkedNode node){
        DoubleLinkedNode next = node.next;
        DoubleLinkedNode pre = node.pre;
        if(pre!=null) pre.next = next;
        else head=next;

        if(next!=null) next.pre = pre;
        else end = pre;
    }
    public void setHead(DoubleLinkedNode node){
        node.next = head;
        node.pre = null;
        if(head!=null) head.pre = node;
        head = node;
        if(end == null) end = node;
    }
    public int get(int key){
        if(map.containsKey(key)){
            removeNode(map.get(key));
            setHead(map.get(key));
            return map.get(key).value;
        }else{
            return  -1;
        }
    }
    public void set(int key, int val){
        if(map.containsKey(key)){
            DoubleLinkedNode cur = map.get(key);
            cur.value = val;
            removeNode(cur);
            setHead(cur);
        }else{
            DoubleLinkedNode node = new DoubleLinkedNode(key,val);
            if(len < capcity){
                len++;
                map.put(key,node);
                setHead(node);
            }else{
                removeNode(end);
                end = end.pre;
                if(end!=null)
                    end.next = null;
                map.put(key,node);
                setHead(node);
            }
        }
    }
}

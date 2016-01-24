package LeetCode2015;

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}
public class ReverseList {
    public static ListNode reverse(ListNode head){
        ListNode dummy = new ListNode(-1);

        while(head!=null){
            ListNode temp = head;
            head = head.next;
            temp.next = dummy.next;
            dummy.next = temp;
        }

        return dummy.next;
    }


    public static ListNode reverseRecur(ListNode head){
        if(head == null) return null;
        if(head.next ==null) return head;
        ListNode next = head.next;
        ListNode newHead = reverseRecur(head.next);
        next.next = head;
        head.next = null;
        return newHead;
    }
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3); a.next = b; b.next =c ;
        ListNode d = new ListNode(4); c.next = d;
        ListNode root = reverseRecur(a);
        while(root!=null) {
            System.out.println(root.val);
            root = root.next;
        }
    }
}

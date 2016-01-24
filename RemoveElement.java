package LeetCode2015;


public class RemoveElement {
    public static ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode runner = dummy;
        while(runner.next!=null){
            if(runner.next.val == val){
                runner.next = runner.next.next;
            }else{
                runner = runner.next;
            }
        }
        return dummy.next;
    }
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(6);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(4);
        ListNode f = new ListNode(5);
        ListNode g = new ListNode(6);
        a.next = b;b.next =c;c.next=d;d.next=e;e.next=f;f.next = g;
        ListNode head = removeElements(a,6);
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}

package LeetCode2015;

/**
 * Created by Trace_route on 7/10/15.
 */
public class isPalindrome {
    public static boolean isPalindrome(ListNode head) {
         if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead= reverse(slow.next);
        slow.next = null;
        while(newHead!=null){
            if(newHead.val != head.val) return false;
            newHead = newHead.next;
            head = head.next;
        }
        return true;
    }
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
    public static void main(String[] args){
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d= new ListNode(2);
        ListNode e = new ListNode(2);
        a.next = b; b.next =c;c.next =d;d.next =e;
        System.out.println(isPalindrome(a));
    }
}

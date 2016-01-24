package LeetCode;

/**
 * Created by Garvin on 9/8/2014.
 */
class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
        next = null;
    }
}
public class addBinary {
    public static ListNode addTwoNumbers(ListNode r1, ListNode r2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        int carry = 0;
        while(r1!=null || r2!=null || carry !=0){
            int val1 = r1==null?0:r1.val;
            int val2 = r2 == null?0:r2.val;
            int total = val1+val2+carry;
            ListNode temp = new ListNode(total%10);
            carry = total/10;
            current.next = temp;
            current = current.next;
            if(r1!=null)
                r1 = r1.next;
            if(r2!=null)
                r2 = r2.next;
        }
        return dummy.next;
    }
    public static  void main(String[] args){
        ListNode r1 = new ListNode(2);
        r1.next = new ListNode(4);
        r1.next.next = new ListNode(3);
        ListNode r2 = new ListNode(5);
        r2.next = new ListNode(6);
        r2.next.next = new ListNode(4);
        ListNode head = addTwoNumbers(r1,r2);
        while(head!=null) {
            System.out.println(head.val);
            head = head.next;
        }
    }
}

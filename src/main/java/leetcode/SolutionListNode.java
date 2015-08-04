package leetcode;

/**
 * Created by xzbang on 2015/4/6.
 */
public class SolutionListNode {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }

        ListNode node;
        if(l1.val>l2.val){
            node=l2;
            l2=l2.next;
        }else{
            node=l1;
            l1=l1.next;
        }
        ListNode head = node;
        while(l1!=null&&l2!=null){
            if(l1.val>l2.val){
                node.next=l2;
                l2=l2.next;
                node=node.next;
            }else{
                node.next=l1;
                l1=l1.next;
                node=node.next;
            }
        }
        while(l1!=null){
            node.next=l1;
            l1=l1.next;
            node=node.next;
        }
        while(l2!=null){
            node.next=l2;
            l2=l2.next;
            node=node.next;
        }
        return head;
    }
    //Given 1->2->3->3->4->4->5, return 1->2->5.
    public ListNode deleteDuplicates(ListNode head) {
        ListNode first=new ListNode(0),second=head;
        if(head==null||head.next==null){
            return head;
        }
        first.next=head;
        second = second.next;
        while(second!=null){
            if(first.next.val==second.val){
                while(second.next!=null){
                    if(second.val==second.next.val){
                        second=second.next;
                    }else{
                        break;
                    }
                }
                if(head==first.next) {
                    first.next = second.next;
                    head=second.next;
                }else{
                    first.next = second.next;
                }
                second=second.next;
                if(second==null){
                    return head;
                }else{
                    second=second.next;
                }
            }else{
                first=first.next;
                second=second.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SolutionListNode solution = new SolutionListNode();
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(1);
        ListNode l4 = new ListNode(2);
        ListNode l5 = new ListNode(5);
        l1.next=l2;l2.next=l3;l3.next=l4;l4.next=l5;
//        System.out.println(solution.mergeTwoLists(l1,l2).val);
        System.out.println(solution.deleteDuplicates(l1).val);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}

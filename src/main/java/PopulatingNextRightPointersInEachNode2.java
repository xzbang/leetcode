/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
 *
 Follow up for problem "Populating Next Right Pointers in Each Node".

 What if the given tree could be any binary tree? Would your previous solution still work?

 Note:

 You may only use constant extra space.
 For example,
 Given the following binary tree,
     1
    /  \
   2    3
  / \    \
 4   5    7
 After calling your function, the tree should look like:
     1 -> NULL
    /  \
   2 -> 3 -> NULL
  / \    \
 4-> 5 -> 7 -> NULL
 *
 *解题思路：
 * 解法1：递归+栈，未实现，不符合题中常数空间的要求
 * 解法2：
 * 依次从最高层遍历到最底层，每次保存当前遍历过程中的父节点
 * 利用上一层（即父节点）中计算过得next的left或right来获取当前节点的next
 *
 * Created by xzbang on 2015/5/19.
 */
public class PopulatingNextRightPointersInEachNode2 {
    //Definition for binary tree with next pointer.
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }

    public void connect(TreeLinkNode root) {
        if(root==null){
            return;
        }
        root.next=null;
        if(root.left==null&&root.right==null){
            return;
        }

        /**
         * father:存储当前遍历层的上一层中的节点，即current的父亲节点或父亲节点的下一个节点
         * current：存储当前遍历的节点
         * first：存储当前遍历层的起始节点
         */
        TreeLinkNode father,current,first;
        father=root;
        if(root.left!=null){
            first=root.left;
        }else{
            first=root.right;
        }
        while(first!=null){
            current = first;
            //如果父亲节点具有左右节点，左子树的next指向右子树，father右移
            if(father.left!=null&&father.right!=null){
                father.left.next=father.right;
            }
            father=father.next;
            while(father!=null){
                if(current.next!=null){//表示当前在左子树并且已经指向右子树
                    current=current.next;
                }else{//表示当前节点的next需要指向父亲的下一个节点的子节点
                    if(father.left!=null) {
                        current.next = father.left;
                        current = current.next;
                    }else if(father.right!=null){
                        current.next = father.right;
                        current=current.next;
                    }
                    //连好后，father继续右移
                    if(father.left!=null&&father.right!=null){
                        father.left.next=father.right;
                    }
                    father=father.next;
                }
            }
            //father下移
            father=first;
            first=null;
            //判断是否还有下一层，并且将first指向下一层的第一个节点
            while(father!=null){
                if(father.left!=null){
                    first=father.left;
                    break;
                }else if(father.right!=null){
                    first = father.right;
                    break;
                }else{
                    father=father.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        PopulatingNextRightPointersInEachNode2 p = new PopulatingNextRightPointersInEachNode2();
        PopulatingNextRightPointersInEachNode2.TreeLinkNode t1 = p.new TreeLinkNode(1);
        PopulatingNextRightPointersInEachNode2.TreeLinkNode t2 = p.new TreeLinkNode(2);
        PopulatingNextRightPointersInEachNode2.TreeLinkNode t3 = p.new TreeLinkNode(3);
        t1.left=t2;
        t1.right=t3;
        p.connect(t1);
        System.out.println(t2.next.val);
    }
}

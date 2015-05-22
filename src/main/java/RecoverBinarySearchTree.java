/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 Two elements of a binary search tree (BST) are swapped by mistake.

 Recover the tree without changing its structure.

 Note:
 A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.


 OJ's Binary Tree Serialization:
 The serialization of a binary tree follows a level order traversal,
 where '#' signifies a path terminator where no node exists below.

 Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
 The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 *
 * 解题思路：递归（不是真正的常数空间，在栈内递归需要不断入栈，最坏O(n)，在堆上是常数空间）
 * 先序遍历，记录两次异常点
 * 第一次异常点记录前后两个点，第二次异常点记录后一个点
 * 具体参见代码中注释实例
 *
 * Created by xzbang on 2015/5/21.
 */
public class RecoverBinarySearchTree {

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    TreeNode tn1 = null,tn2 = null,prev = new TreeNode(Integer.MIN_VALUE);
    public void recoverTree(TreeNode root) {
        search(root);
        if(tn1!=null&&tn2!=null){
            int tmp = tn1.val;
            tn1.val=tn2.val;
            tn2.val = tmp;
        }
    }

    /**
     * 考虑先序遍历结果：（6,2,3,4,1,7）
     * 第一次出现异常时：6>=2，6在前，（同时应该标记2）
     * 第二次出现异常时：4>=1，1在后
     * 应该交换：6<-->1
     * @param treeNode
     */
    private void search(TreeNode treeNode) {
        if(treeNode==null){
            return;
        }
        search(treeNode.left);
        //第一次出现异常时加前面的点，第二次出现异常时加后面的点
        if(prev.val>=treeNode.val){
            if(tn1==null){
                tn1=prev;
                tn2=treeNode;//如果测试用例为{3,1，#，2，#}（2,1,3）时，没有这行结果不对
            }else{
                tn2=treeNode;
            }
        }

        //也可以如下写法，同时也可以解决上述测试用例的问题
        //以下两个if语句分开写，正好将第一次出现异常处的前后两个节点加入标记，亮点
        /*if (tn1 == null && prev.val >= treeNode.val) {
            tn1 = prevElement;
        }
        if (tn1 != null && prev.val >= treeNode.val) {
            tn2 = root;
        }*/

        prev=treeNode;
        search(treeNode.right);
    }

    public static void main(String[] args) {
        RecoverBinarySearchTree rbst = new RecoverBinarySearchTree();
        RecoverBinarySearchTree.TreeNode t1 = rbst.new TreeNode(1);
        RecoverBinarySearchTree.TreeNode t2 = rbst.new TreeNode(2);
        RecoverBinarySearchTree.TreeNode t3 = rbst.new TreeNode(3);
//        RecoverBinarySearchTree.TreeNode t4 = rbst.new TreeNode(4);
//        RecoverBinarySearchTree.TreeNode t5 = rbst.new TreeNode(5);
//        t2.left=t5;
//        t2.right=t3;
//        t3.right=t4;
//        t4.right=t1;
//        t3.right=t2;
//        t2.right=t1;
        t3.left=t1;
        t1.left=t2;
        rbst.recoverTree(t3);
        System.out.println(t2.val);
    }
}

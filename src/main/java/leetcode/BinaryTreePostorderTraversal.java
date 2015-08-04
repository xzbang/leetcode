package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 Given a binary tree, return the postorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3},
 1
  \
   2
  /
 3
 return [3,2,1].

 Note: Recursive solution is trivial, could you do it iteratively?
 *
 * 解题思路：递归实现简单，以下实现非递归算法
 * 首先将根节点加入临时栈
 * 每次判断临时栈是否为空，如果不为空，则取出栈顶节点插入结果表第一个
 * 然后对该节点的左右子树进行判断，将不为空的左右子节点“先左后右”加入临时栈
 *
 * Created by xzbang on 2015/5/15.
 */
public class BinaryTreePostorderTraversal {

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if(root==null)
            return result;
        Stack<TreeNode> transit = new Stack<TreeNode>();//临时栈，存储待解析子树的节点
        transit.push(root);
        while(!transit.isEmpty()){
            TreeNode node = transit.pop();
            result.add(0,node.val);
            //先左后右进入临时栈，出栈结果则为先右后左，而最终结果则为先左后右
            if(node.left!=null)
                transit.push(node.left);
            if(node.right!=null)
                transit.push(node.right);
        }
        return result;
    }

    public static void main(String[] args) {
        BinaryTreePostorderTraversal binaryTreePostorderTraversal = new BinaryTreePostorderTraversal();

        //注意内部类的.new方法
        BinaryTreePostorderTraversal.TreeNode node1 = binaryTreePostorderTraversal.new TreeNode(2);
        TreeNode node2 = binaryTreePostorderTraversal.new TreeNode(3);
        TreeNode root = binaryTreePostorderTraversal.new TreeNode(1);

        root.right=node1;
        root.left=node2;
        List<Integer> list = binaryTreePostorderTraversal.postorderTraversal(root);
        System.out.println(list);
    }

}

import java.util.HashMap;

/**
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/
 *
 Given a binary tree, find the maximum path sum.

 The path may start and end at any node in the tree.

 For example:
 Given the below binary tree,
   1
  / \
 2   3
 Return 6.
 *
 * 解题思路：
 * 每颗子树有两种值：从该子树的左子树走到右子树取到了最大值，或者该子树在最大路径上
 * 注意当子树的最大值为负数时与0取最大值，表示放弃该子树
 *
 * Created by xzbang on 2015/5/19.
 */
public class BinaryTreeMaximumPathSum {
    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    int maxValue = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        subMaxPathSum(root);
        return maxValue;
    }

    private int subMaxPathSum(TreeNode node) {
        if(node==null)
            return 0;
        int left = Math.max(0, subMaxPathSum(node.left));//与0取最大表示当该节点取得负数时，不取该节点
        int right = Math.max(0, subMaxPathSum(node.right));
        maxValue = Math.max(maxValue,left+right+node.val);
        return Math.max(left,right)+node.val;
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum binaryTreeMaximumPathSum = new BinaryTreeMaximumPathSum();
        BinaryTreeMaximumPathSum.TreeNode a = binaryTreeMaximumPathSum.new TreeNode(1);
        BinaryTreeMaximumPathSum.TreeNode b = binaryTreeMaximumPathSum.new TreeNode(2);
        BinaryTreeMaximumPathSum.TreeNode c = binaryTreeMaximumPathSum.new TreeNode(3);
        a.left=b;
        a.right=c;
        System.out.println(binaryTreeMaximumPathSum.maxPathSum(a));
    }
}

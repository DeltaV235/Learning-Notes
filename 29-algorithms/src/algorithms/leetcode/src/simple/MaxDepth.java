package simple;

/**
 * 二叉树的最大深度
 * <a href="https://leetcode.cn/problems/maximum-depth-of-binary-tree/"/>
 */
public class MaxDepth {
    int maxDepth = 0;

    public int maxDepth(TreeNode root) {
        calMaxDepth(root, 0);
        return maxDepth;
    }

    public void calMaxDepth(TreeNode root, int currentDepth) {
        if (root == null) {
            maxDepth = Math.max(maxDepth, currentDepth);
            return;
        }
        calMaxDepth(root.left, currentDepth + 1);
        calMaxDepth(root.right, currentDepth + 1);
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

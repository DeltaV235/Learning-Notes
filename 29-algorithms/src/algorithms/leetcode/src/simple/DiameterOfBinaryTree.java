package simple;

/**
 * 二叉树的直径
 * <a href="https://leetcode.cn/problems/diameter-of-binary-tree/solutions/139683/er-cha-shu-de-zhi-jing-by-leetcode-solution/"/>
 */
public class DiameterOfBinaryTree {
    private int maxDepth = 0;
    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {

        traversalOuter(root);
        return maxDiameter;
    }

    private void traversalOuter(TreeNode root) {
        if (root == null) return;
        maxDepth = 0;
        traversal(root.left, 1);
        int maxLeftDepth = maxDepth;
        maxDepth = 0;
        traversal(root.right, 1);
        int maxRightDepth = maxDepth;
        maxDiameter = Math.max(maxLeftDepth + maxRightDepth, maxDiameter);

        traversalOuter(root.left);
        traversalOuter(root.right);
    }

    private void traversal(TreeNode root, int currentDepth) {
        if (root == null) {
            maxDepth = Math.max(currentDepth - 1, maxDepth);
            return;
        }
        traversal(root.left, currentDepth + 1);
        traversal(root.right, currentDepth + 1);
    }


    class TreeNode {
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

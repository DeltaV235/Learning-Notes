package deltav.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeTraversal {
    public static void levelOrderTraversal(TreeNode root) {
        System.out.println("level order traversal");
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            int val = treeNode.val;
            System.out.println(val);
            if (treeNode.left != null)
                queue.add(treeNode.left);
            if (treeNode.right != null)
                queue.add(treeNode.right);
        }
    }

    /**
     * 前序遍历
     *
     * @param treeNode root of binary tree
     */
    public static void preOrder(TreeNode treeNode) {
        if (treeNode == null)
            return;
        System.out.println(treeNode.val);
        preOrder(treeNode.left);
        preOrder(treeNode.right);
    }

    public static void inOrder(TreeNode treeNode) {
        if (treeNode == null)
            return;
        inOrder(treeNode.left);
        System.out.println(treeNode.val);
        inOrder(treeNode.right);
    }

    public static void postOrder(TreeNode treeNode) {
        if (treeNode == null)
            return;
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        System.out.println(treeNode.val);
    }

    public static void main(String[] args) {
        TreeNode prefectBinaryTree = TreeNode.initPrefectBinaryTree();

        BinaryTreeTraversal.levelOrderTraversal(prefectBinaryTree);

        System.out.println("pre order traversal");
        BinaryTreeTraversal.preOrder(prefectBinaryTree);

        System.out.println("in order traversal");
        BinaryTreeTraversal.inOrder(prefectBinaryTree);

        System.out.println("post order traversal");
        BinaryTreeTraversal.postOrder(prefectBinaryTree);
    }
}

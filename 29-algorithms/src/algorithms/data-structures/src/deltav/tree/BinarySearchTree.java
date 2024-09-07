package deltav.tree;

public class BinarySearchTree {
    private TreeNode root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode search(int num) {
        TreeNode cur = root;
        while (cur != null) {
            if (num < cur.val)
                cur = cur.left;
            else if (num > cur.val)
                cur = cur.right;
            else
                break;
        }
        return cur;
    }

    public void insert(int num) {
        if (root == null) {
            root = new TreeNode(num);
            return;
        }

        TreeNode pre = null, cur = root;

        while (cur != null) {
            pre = cur;
            if (num < cur.val)
                cur = cur.left;
            else if (num > cur.val)
                cur = cur.right;
            else
                return;
        }

        if (num < pre.val)
            pre.left = new TreeNode(num);
        else
            pre.right = new TreeNode(num);
    }

    public void remove(int num) {
        if (root == null)
            return;

        TreeNode pre = null, cur = root;

        while (cur != null) {
            if (cur.val == num)
                break;
            pre = cur;
            if (num < cur.val)
                cur = cur.left;
            else
                cur = cur.right;
        }

        if (cur == null)
            return;

        if (cur.left == null || cur.right == null) {
            TreeNode next = cur.left != null ? cur.left : cur.right;
            if (cur != root) {
                if (pre.left == cur)
                    pre.left = next;
                else
                    pre.right = next;
            } else
                root = next;
        } else {
            TreeNode next = cur.right;
            while (next.left != null) {
                next = next.left;
            }

            remove(next.val);
            cur.val = next.val;
        }
    }

    public static BinarySearchTree generateBinarySearchTree() {
        TreeNode root = new TreeNode();
        TreeNode treeNode1 = new TreeNode();
        TreeNode treeNode2 = new TreeNode();
        TreeNode treeNode3 = new TreeNode();
        TreeNode treeNode4 = new TreeNode();
        TreeNode treeNode5 = new TreeNode();
        TreeNode treeNode6 = new TreeNode();
        TreeNode treeNode7 = new TreeNode();
        TreeNode treeNode8 = new TreeNode();
        TreeNode treeNode9 = new TreeNode();
        TreeNode treeNode10 = new TreeNode();
        TreeNode treeNode11 = new TreeNode();
        TreeNode treeNode12 = new TreeNode();
        TreeNode treeNode13 = new TreeNode();
        TreeNode treeNode14 = new TreeNode();

        root.left = treeNode1;
        root.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.left = treeNode5;
        treeNode2.right = treeNode6;

        treeNode3.left = treeNode7;
        treeNode3.right = treeNode8;
        treeNode4.left = treeNode9;
        treeNode4.right = treeNode10;

        treeNode5.left = treeNode11;
        treeNode5.right = treeNode12;

        treeNode6.left = treeNode13;
        treeNode6.right = treeNode14;

        root.val = 8;
        treeNode1.val = 4;
        treeNode2.val = 12;
        treeNode3.val = 2;
        treeNode4.val = 6;
        treeNode5.val = 10;
        treeNode6.val = 14;
        treeNode7.val = 1;
        treeNode8.val = 3;
        treeNode9.val = 5;
        treeNode10.val = 7;
        treeNode11.val = 9;
        treeNode12.val = 11;
        treeNode13.val = 13;
        treeNode14.val = 15;

        return new BinarySearchTree(root);
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = BinarySearchTree.generateBinarySearchTree();
        System.out.println("binarySearchTree.search(12) = " + binarySearchTree.search(12));
        System.out.println("binarySearchTree.search(4) = " + binarySearchTree.search(4));

        System.out.println("------------------------------------------------");
        binarySearchTree.insert(16);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.insert(2);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        BinarySearchTree emptyBinarySearchTree = new BinarySearchTree();
        emptyBinarySearchTree.insert(15);
        BinaryTreeTraversal.inOrder(emptyBinarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.remove(16);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.remove(1);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.remove(2);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.remove(4);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);

        System.out.println("------------------------------------------------");
        binarySearchTree.remove(8);
        BinaryTreeTraversal.inOrder(binarySearchTree.root);
    }
}

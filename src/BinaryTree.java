public class BinaryTree {
    private TreeNode root;
    public BinaryTree(int[] data) {
        root = createBinaryTree(data, 1);
    }
    private TreeNode createBinaryTree(int[] data, int i) {
        if (data.length == 0) return null;
        if (data.length == 1) return new TreeNode(data[0]);
        TreeNode root = new TreeNode(data[i]);
        if (2 * i < data.length) {
            root.left = createBinaryTree(data, 2*i);
        }
        if (2 * i + 1 < data.length) {
            root.right = createBinaryTree(data, 2*i+1);
        }
        return root;
    }

    public TreeNode getRoot() {
        return root;
    }

    // Pre order traversal (Node -> Left -> Right)
    public void preOrder(TreeNode root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // Pre order traversal (Left -> Node -> Right)
    public void inOrder(TreeNode root) {
        if (root == null) return;

        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // Post order traversal (Left -> Right -> Node)
    public void postOrder(TreeNode root) {
        if (root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }
}

class TreeNode {
    int data;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data) {
        this.data = data;
    }
}

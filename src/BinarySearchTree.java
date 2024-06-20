public class BinarySearchTree {

    /*
        The smallest data is in the left most node of BST
        The biggest data is in the right most node of BST
    */

    private TreeNode root;
    public BinarySearchTree(int[] array) {
        createBST(array);
    }
    private void createBST(int[] array) {
        for (int j : array) {
            root = insertIterative(this.root, j);
        }

    }
    public TreeNode getRoot() {
        return this.root;
    }

    /*
        Recursive Insert -> Time Complexity: O(log n); Space Complexity: O(log n)
        Iterative Insert -> Time Complexity: O(log n); Space Complexity: O(1)
    */
    public TreeNode insert(TreeNode root, int data) {
        if (root == null) return new TreeNode(data);
        if (root.data > data) root.left = insert(root.left, data);
        else root.right = insert(root.right, data);
        return root;
    }
    public TreeNode insertIterative(TreeNode root, int data) {
        TreeNode temp = new TreeNode(data);
        TreeNode current = root;
        TreeNode parent = null;

        // Search for the Parent Node
        while (current != null) {
            parent = current;
            if (current.data > data) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }

        // Insert the new node while maintaining the properties of BST
        if (parent == null) return temp;
        if (parent.data > data) {
            parent.left = temp;
        }
        else {
            parent.right = temp;
        }
        return root;
    }
    public boolean search(TreeNode root, int data) {
        if (root == null) return false;
        if (root.data == data) return true;
        if (root.data > data) return search(root.left, data);
        return search(root.right, data);
    }
    public TreeNode delete(TreeNode root, int data) {
        if (root == null) return null;
        if (root.data > data) root.left = delete(root.left, data);
        else if (root.data < data) root.right = delete(root.right, data);
        else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Find the smallest node in the right subtree or,
            // Find the biggest node in the left subtree
            root.data = minVal(root.right).data;
            root.right = delete(root.right, root.data);
        }
        return root;
    }
    private TreeNode minVal(TreeNode root) {
        if (root == null || root.left == null) return root;
        return minVal(root.left);
    }

    // Inorder traversal of BST returns a sorted array!
    public void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }
    public void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }
    public void printPostorder(TreeNode root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }
}

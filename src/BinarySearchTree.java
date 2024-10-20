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

    /*
    * A Binary Search Tree is a binary tree in which all the nodes follow the below-mentioned properties −
    * The left sub-tree of a node has a key less than its parent node's key.
    * The right sub-tree of a node has a key greater than its parent node's key.
    * The left and right sub-tree each must also be a binary search tree.
    * There must be no duplicate nodes.
    */

    // Now, let's check if the given tree is a BST or not
    // Approach 1: Using a range of values
    public boolean isBST1(TreeNode root) {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private boolean isBSTUtil(TreeNode root, int min, int max) {
        if (root == null) return true;
        if (root.data < min || root.data > max) return false;
        return isBSTUtil(root.left, min, root.data - 1) && isBSTUtil(root.right, root.data + 1, max);
    }

    // Approach 2: Using Inorder Traversal
    private int prev = Integer.MIN_VALUE;
    public boolean isBST2(TreeNode root) {
        if (root == null) return true;
        if (!isBST2(root.left)) return false;
        if (root.data <= prev) return false;
        prev = root.data;
        return isBST2(root.right);
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

    // Some popular problems on BST
    // find the floor of a given key in a BST, which is the greatest element in the BST which is smaller than the given key
    public int getFloor(TreeNode root, int key) {
        int floor = Integer.MAX_VALUE;
        while (root != null) {
            if (root.data == key) return root.data;
            if (root.data > key) root = root.left;
            else {
                floor = root.data;
                root = root.right;
            }
        }
        return floor;
    }

    // find the ceil of a given key in a BST, which is the smallest element in the BST which is greater than the given key
    public int getCeil(TreeNode root, int key) {
        int ceil = Integer.MIN_VALUE;
        while (root != null) {
            if (root.data == key) return root.data;
            if (root.data < key) root = root.right;
            else {
                ceil = root.data;
                root = root.left;
            }
        }
        return ceil;
    }
}

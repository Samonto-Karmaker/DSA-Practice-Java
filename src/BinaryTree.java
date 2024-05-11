import java.util.*;
import java.util.LinkedList;

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

    public int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
    public int size(TreeNode root) {
        if (root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
    public int maxNode(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.data, Math.max(maxNode(root.left), maxNode(root.right)));
    }

    // Some important questions on Binary trees

    // Level order traversal
    // Approach 1: O(n^2)
    /*
    * First figure out how to print one level
    * Use a loop to print all levels
    */
    private void printCurrentLevel(TreeNode root, int level) {
        if (root == null) return;
        if (level == 1) System.out.print(root.data + " ");
        printCurrentLevel(root.left, level - 1);
        printCurrentLevel(root.right, level - 1);
    }
    public void levelOrder1(TreeNode root) {
        int height = height(root);
        for(int i = 1; i <= height; i++) {
            printCurrentLevel(root, i);
        }
    }
    // Approach 2: O(n)
    // Using Queue
    public void levelOrder2(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
    }

    // Left view of a binary tree
    private void leftViewUtil(TreeNode root, int[] list, int level) {
        if (root == null) return;
        if (list[level] == 0) list[level] = root.data;
        leftViewUtil(root.left, list, level + 1);
        leftViewUtil(root.right, list, level + 1);
    }
    public void leftView(TreeNode root, int height) {
        int[] list = new int[height];
        leftViewUtil(root, list, 0);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    // Right view of a binary tree
    private void rightViewUtil(TreeNode root, int[] list, int level) {
        if (root == null) return;
        if (list[level] == 0) list[level] = root.data;
        rightViewUtil(root.right, list, level + 1);
        rightViewUtil(root.left, list, level + 1);
    }
    public void rightView(TreeNode root, int height) {
        int[] list = new int[height];
        rightViewUtil(root, list, 0);
        for (int i : list) {
            System.out.print(i + " ");
        }
    }

    // Top view of a binary tree
    public void topView(TreeNode root) {
        if (root == null) return;

        Queue<HorizontalDistance> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();

        // Horizontal distance
        HorizontalDistance h = new HorizontalDistance(root, 0);
        queue.offer(h);
        while (!queue.isEmpty()) {
            HorizontalDistance temp = queue.poll();
            int hd = temp.hd;
            if(!map.containsKey(hd)) {
                map.put(temp.hd, temp.node.data);
            }
            if (temp.node.left != null) {
                HorizontalDistance l = new HorizontalDistance(temp.node.left, hd - 1);
                queue.offer(l);
            }
            if (temp.node.right != null) {
                HorizontalDistance r = new HorizontalDistance(temp.node.right, hd + 1);
                queue.offer(r);
            }
        }
        for (int i : map.values()) System.out.print(i + " ");
    }

    // Bottom view of a binary tree
    public void bottomView(TreeNode root) {
        if (root == null) return;

        Queue<HorizontalDistance> queue = new LinkedList<>();
        Map<Integer, Integer> map = new TreeMap<>();

        // Horizontal distance
        HorizontalDistance h = new HorizontalDistance(root, 0);
        queue.offer(h);
        while (!queue.isEmpty()) {
            HorizontalDistance temp = queue.poll();
            int hd = temp.hd;
            map.put(temp.hd, temp.node.data);

            if (temp.node.left != null) {
                HorizontalDistance l = new HorizontalDistance(temp.node.left, hd - 1);
                queue.offer(l);
            }
            if (temp.node.right != null) {
                HorizontalDistance r = new HorizontalDistance(temp.node.right, hd + 1);
                queue.offer(r);
            }
        }
        for (int i : map.values()) System.out.print(i + " ");
    }

    /*
    * Convert a binary tree into a doubly linked list (in place)
    * Flatten a binary tree
    * left -> prev; right -> next
    * In order traversal
    */
    private TreeNode prev = null, head = null;
    public TreeNode flatten(TreeNode root) {
        flattenUtil(root);
        return head;
    }
    private void flattenUtil(TreeNode root) {
        if (root == null) return;
        flattenUtil(root.left);
        if (prev == null) {
            head = root;
        }
        else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        flattenUtil(root.right);
    }

    /*
    * Diameter of a binary tree
    * Maximum width of a binary tree
    * The maximum distance from one node to another
    */

    // Time Complexity: O(n^2)
    public int maxWidth(TreeNode root) {
        if (root == null) return 0;
        int leftWidth = maxWidth(root.left);
        int rightWidth =  maxWidth(root.right);
        int currentWidth = height(root.left) + height(root.right) + 1;
        return Math.max(leftWidth, Math.max(rightWidth, currentWidth));
    }

    // Time Complexity: O(n)
    private int ans = 0;
    public int maxWidthOptimized(TreeNode root) {
        maxWidthUtil(root);
        return ans;
    }
    private int maxWidthUtil(TreeNode root) {
        if (root == null) return 0;
        int leftWidth = maxWidthUtil(root.left);
        int rightWidth = maxWidthUtil(root.right);
        ans = Math.max(ans, leftWidth + rightWidth + 1);
        return Math.max(leftWidth, rightWidth) + 1;
    }

    // Lowest Common Ancestor of 2 nodes of a binary tree
    /*
    * Use pre-order traversal (N L R)
    * if current node == n1 or current node == n2, return current node
    * if n1 and n2 are in different side of the current node, return current node
    * if n1 and n2 are in the same side of the current node, return that side of the current node
    */
    public TreeNode lca(TreeNode root, int n1, int n2) {
        if (root == null) return null;
        if (root.data == n1 || root.data == n2) return root;
        TreeNode left = lca(root.left, n1, n2);
        TreeNode right = lca(root.right, n1, n2);
        if (left != null && right != null) return root;
        return left != null ? left : right;
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

/*
* This class helps to store the horizontal distance of a TreeNode
* With respect to the root TreeNode
* Useful to find the Top View and Bottom View of a Binary Tree
*/
class HorizontalDistance {
    TreeNode node;
    int hd;
    public HorizontalDistance(TreeNode node, int hd) {
        this.node = node;
        this.hd = hd;
    }
}

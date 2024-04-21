public class LinkedList {

    private Node head;

    public LinkedList() {
        head = null;
    }
    public LinkedList(int data) {
        head = new Node(data);
    }

    public Node getHead() {
        return head;
    }
    public void setHead(Node head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return head == null;
    }
    public int size() {
        int size = 0;
        for (Node current = head; current != null; current = current.next) size++;
        return size;
    }
    public int get(int index) {
        if (isEmpty() || index < 0 || index >= size()) return -1;
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    public void add(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }
    public void push(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public void insertAt(int index, int data) {
        if (index < 0 || index > size()) return;
        if (index == 0) {
            push(data);
            return;
        }
        if(index == size() - 1) {
            add(data);
            return;
        }
        Node newNode = new Node(data);
        Node current = head;
        for (int i = 0; i < index - 1; i++) current = current.next;
        Node temp = current.next;
        current.next = newNode;
        newNode.next = temp;
    }
    public void removeLast() {

        // If the list is empty
        if (isEmpty()) return;

        // If there is only 1 element in the list
        if (head.next == null) head = null;

        // If there are multiple elements in the list
        Node current = head;
        Node prev = null;
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        prev.next = null;
    }
    public void removeFirst() {
        if (isEmpty()) return;
        head = head.next;
    }
    public void removeAt(int index) {
        if (isEmpty() || size() <= index || index < 0) return;
        Node current = head;
        Node prev = null;
        for (int i = 0; i < index && current != null; i++) {
            prev = current;
            current = current.next;
        }
        if (prev == null) {
            removeFirst();
            return;
        }
        if (current == null) {
            removeLast();
            return;
        }
        prev.next = current.next;
    }
    public void removeData(int data) {
        if (isEmpty()) return;
        int i = 0;
        for (Node current = head; current != null; current = current.next) {
            if (current.data == data) removeAt(i);
            else i++;
        }
    }
    public int find(int data) {
        int i = 0;
        for (Node current = head; current != null; current = current.next) {
            if (current.data == data) return i;
            i++;
        }
        return -1;
    }
    public void print() {
        for (int i = 0; i < size(); i++) System.out.print(get(i) + " ");
        System.out.println();
    }

    // Some important questions on LinkedList

    // Reverse a LinkedList
    public void reverseIterative() {
        Node current = head;
        Node prev = null;
        while (current != null) {
            Node temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = prev;
    }
    public Node reverseRecursive(Node head) {
        if(head == null || head.next == null) return head;
        Node newHead = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // Find the middle node
    // Using fast-slow pointer
    public Node midNode() {
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // Palindrome List
    public boolean isPalindrome() {
        if(head == null) return false;
        if(head.next == null) return true;

        // find the mid and reverse the 2nd half of the list
        Node mid = midNode();
        Node last = reverseRecursive(mid.next);
        Node current = head;
        while (last != null) {
            if(last.data != current.data) return false;
            last = last.next;
            current = current.next;
        }
        return true;
    }

    // Detect Cycle in a LinkedList
    // Floyd's Cycle Detection Algorithm (Using fast-slow pointers)
    public Node detectCycle() {
        if(head == null) return null;
        Node fast = head;
        Node slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        return slow;
    }

    // Find the node where cycle starts
    public Node detectStartCycle() {
        if(head == null) return null;
        Node meet = detectCycle();
        if(meet == null) return null;
        Node start = head;
        while(start != meet) {
            start = start.next;
            meet = meet.next;
        }
        return start;
    }
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

public class LinkedList {

    private Node head;

    public LinkedList() {
        head = null;
    }
    public LinkedList(int data) {
        head = new Node(data);
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
}

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }
}

/**
 * Node class for the linked implementation of a Deque.
 * Each node stores a data value and pointers to the previous and next nodes.
 */
public class Node {
    int data;
    Node next;
    Node prev;

    // constructor
    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

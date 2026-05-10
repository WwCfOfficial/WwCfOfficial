/**
 * Linked (doubly-linked list) implementation of a Double-Ended Queue (Deque).
 * Uses Node objects connected via prev and next pointers.
 */
public class LinkedDeque {

    private Node front;  // pointer to the first node
    private Node rear;   // pointer to the last node
    private int count;   // number of elements

    // constructor – creates an empty deque
    public LinkedDeque() {
        front = null;
        rear = null;
        count = 0;
    }

    // check if the deque is empty
    public boolean isEmpty() {
        return (count == 0);
    }

    // return the number of elements
    public int size() {
        return count;
    }

    // insert an element at the front
    public void insertFront(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        count++;
        System.out.println("Inserted " + data + " at front.");
    }

    // insert an element at the rear
    public void insertRear(int data) {
        Node newNode = new Node(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        count++;
        System.out.println("Inserted " + data + " at rear.");
    }

    // remove and return the front element
    public int removeFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from front.");
            return -1;
        }
        int data = front.data;
        front = front.next;
        if (front != null) {
            front.prev = null;
        } else {
            rear = null; // deque became empty
        }
        count--;
        System.out.println("Removed " + data + " from front.");
        return data;
    }

    // remove and return the rear element
    public int removeRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from rear.");
            return -1;
        }
        int data = rear.data;
        rear = rear.prev;
        if (rear != null) {
            rear.next = null;
        } else {
            front = null; // deque became empty
        }
        count--;
        System.out.println("Removed " + data + " from rear.");
        return data;
    }

    // peek at the front element without removing
    public int peekFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        }
        return front.data;
    }

    // peek at the rear element without removing
    public int peekRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        }
        return rear.data;
    }

    // display all elements from front to rear
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }
        System.out.print("Deque contents (front -> rear): ");
        Node current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // quick demo
    public static void main(String[] args) {
        LinkedDeque dq = new LinkedDeque();

        dq.insertRear(10);
        dq.insertRear(20);
        dq.insertFront(5);
        dq.insertFront(3);
        dq.display();

        dq.removeFront();
        dq.removeRear();
        dq.display();

        System.out.println("Front element: " + dq.peekFront());
        System.out.println("Rear element : " + dq.peekRear());
        System.out.println("Size         : " + dq.size());
    }
}

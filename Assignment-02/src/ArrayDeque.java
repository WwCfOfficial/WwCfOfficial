/**
 * Array-based (Contiguous) implementation of a Double-Ended Queue (Deque).
 * Uses a circular array so both ends can grow without wasting space.
 */
public class ArrayDeque {

    private int[] deque;   // array that holds the elements
    private int front;     // index of the front element
    private int rear;      // index of the rear element
    private int count;     // current number of elements
    private int maxSize;   // maximum capacity

    // constructor – creates an empty deque with given capacity
    public ArrayDeque(int size) {
        maxSize = size;
        deque = new int[maxSize];
        front = 0;
        rear = -1;
        count = 0;
    }

    // check if deque is empty
    public boolean isEmpty() {
        return (count == 0);
    }

    // check if deque is full
    public boolean isFull() {
        return (count == maxSize);
    }

    // return the number of elements
    public int size() {
        return count;
    }

    // insert an element at the front
    public void insertFront(int item) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert at front.");
            return;
        }
        // move front backwards in a circular manner
        front = (front - 1 + maxSize) % maxSize;
        deque[front] = item;
        if (count == 0) {
            rear = front; // first element, so rear also points here
        }
        count++;
        System.out.println("Inserted " + item + " at front.");
    }

    // insert an element at the rear
    public void insertRear(int item) {
        if (isFull()) {
            System.out.println("Deque is full. Cannot insert at rear.");
            return;
        }
        rear = (rear + 1) % maxSize;
        deque[rear] = item;
        if (count == 0) {
            front = rear; // first element
        }
        count++;
        System.out.println("Inserted " + item + " at rear.");
    }

    // remove and return the front element
    public int removeFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from front.");
            return -1;
        }
        int item = deque[front];
        front = (front + 1) % maxSize;
        count--;
        System.out.println("Removed " + item + " from front.");
        return item;
    }

    // remove and return the rear element
    public int removeRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty. Cannot remove from rear.");
            return -1;
        }
        int item = deque[rear];
        rear = (rear - 1 + maxSize) % maxSize;
        count--;
        System.out.println("Removed " + item + " from rear.");
        return item;
    }

    // peek at the front element without removing
    public int peekFront() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        }
        return deque[front];
    }

    // peek at the rear element without removing
    public int peekRear() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return -1;
        }
        return deque[rear];
    }

    // display all elements from front to rear
    public void display() {
        if (isEmpty()) {
            System.out.println("Deque is empty.");
            return;
        }
        System.out.print("Deque contents (front -> rear): ");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % maxSize;
            System.out.print(deque[index] + " ");
        }
        System.out.println();
    }

    // quick demo
    public static void main(String[] args) {
        ArrayDeque dq = new ArrayDeque(5);

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

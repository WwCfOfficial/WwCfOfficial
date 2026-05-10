import java.util.Scanner;

/**
 * Real-world problem: Browser History Navigation using a Deque.
 *
 * This program simulates forward/backward navigation in a web browser.
 * A deque is used because we need to add and remove pages from both ends:
 *   - When visiting a new page, it goes to the front of the "forward" deque.
 *   - Going back moves the current page to a forward-stack and pops from back-stack.
 *   - Going forward does the reverse.
 *
 * Uses the linked deque approach since the browsing history can grow
 * without a fixed limit.
 */
public class BrowserHistory {

    // simple linked-list based deque for storing page URLs
    private Node front;
    private Node rear;
    private int count;

    public BrowserHistory() {
        front = null;
        rear = null;
        count = 0;
    }

    public boolean isEmpty() {
        return (count == 0);
    }

    public int size() {
        return count;
    }

    // push a page to the rear (top of stack behaviour)
    public void pushRear(String page) {
        Node newNode = new Node(page);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        count++;
    }

    // pop a page from the rear
    public String popRear() {
        if (isEmpty()) return null;
        String page = rear.page;
        rear = rear.prev;
        if (rear != null) {
            rear.next = null;
        } else {
            front = null;
        }
        count--;
        return page;
    }

    // peek at the rear page
    public String peekRear() {
        if (isEmpty()) return null;
        return rear.page;
    }

    // clear all entries
    public void clear() {
        front = null;
        rear = null;
        count = 0;
    }

    // display the history from bottom to top
    public void display() {
        if (isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        Node current = front;
        int i = 1;
        while (current != null) {
            System.out.println("  " + i + ". " + current.page);
            current = current.next;
            i++;
        }
    }

    // ---- inner Node class (stores a String instead of int) ----
    private static class Node {
        String page;
        Node next;
        Node prev;

        Node(String page) {
            this.page = page;
            this.next = null;
            this.prev = null;
        }
    }

    // ====================== MAIN PROGRAM ======================
    public static void main(String[] args) {

        BrowserHistory backStack = new BrowserHistory();
        BrowserHistory forwardStack = new BrowserHistory();
        String currentPage = null;

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        System.out.println("=== Browser History Navigation (Deque Demo) ===");
        System.out.println("Commands: visit <url> | back | forward | show | quit");
        System.out.println();

        // demo with preset inputs so the output is predictable for the report
        String[] demoInputs = {
            "visit google.com",
            "visit youtube.com",
            "visit github.com",
            "visit stackoverflow.com",
            "show",
            "back",
            "back",
            "show",
            "forward",
            "show",
            "visit reddit.com",
            "show",
            "quit"
        };

        for (String input : demoInputs) {
            System.out.println("> " + input);
            String[] parts = input.split(" ", 2);
            String command = parts[0].toLowerCase();

            switch (command) {
                case "visit":
                    if (parts.length < 2) {
                        System.out.println("Please provide a URL.");
                        break;
                    }
                    String url = parts[1];
                    if (currentPage != null) {
                        backStack.pushRear(currentPage);
                    }
                    currentPage = url;
                    forwardStack.clear(); // new visit clears forward history
                    System.out.println("Visiting: " + currentPage);
                    break;

                case "back":
                    if (backStack.isEmpty()) {
                        System.out.println("No pages to go back to.");
                    } else {
                        forwardStack.pushRear(currentPage);
                        currentPage = backStack.popRear();
                        System.out.println("Went back to: " + currentPage);
                    }
                    break;

                case "forward":
                    if (forwardStack.isEmpty()) {
                        System.out.println("No pages to go forward to.");
                    } else {
                        backStack.pushRear(currentPage);
                        currentPage = forwardStack.popRear();
                        System.out.println("Went forward to: " + currentPage);
                    }
                    break;

                case "show":
                    System.out.println("--- Current page: " + currentPage + " ---");
                    System.out.println("Back stack:");
                    backStack.display();
                    System.out.println("Forward stack:");
                    forwardStack.display();
                    break;

                case "quit":
                    running = false;
                    System.out.println("Exiting browser.");
                    break;

                default:
                    System.out.println("Unknown command.");
            }
            System.out.println();
        }

        sc.close();
    }
}

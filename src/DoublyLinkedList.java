import java.util.Scanner;

/**
 * Doubly linked list implementation.
 * Each node stores data and references to both previous and next nodes.
 */
public class DoublyLinkedList {
    private static class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;

    /** Returns true when the list has no nodes (head is null). */
    public boolean isEmpty() {
        return head == null;
    }

    /** Insert at Beginning - the new node becomes the head; both prev and next links are updated. O(1). */
    public void insertBeginning(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    /** Insert at End - attaches after the tail and moves tail. O(1) because the tail is stored. */
    public void insertEnd(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    /** Insert at Position (1-based). Valid positions: 1 to count+1. Returns false for an invalid position. */
    public boolean insertAtPosition(int value, int position) {
        if (position < 1) return false;
        if (position == 1) {
            insertBeginning(value);
            return true;
        }
        Node current = head;
        for (int i = 1; current != null && i < position - 1; i++) current = current.next;
        if (current == null) return false;
        if (current == tail) {
            insertEnd(value);
            return true;
        }
        Node node = new Node(value);
        node.next = current.next;
        node.prev = current;
        current.next.prev = node;
        current.next = node;
        return true;
    }

    /** Insert After a Node - inserts after the FIRST node holding 'target'. Returns false if not found. */
    public boolean insertAfter(int target, int value) {
        Node current = head;
        while (current != null && current.data != target) current = current.next;
        if (current == null) return false;
        if (current == tail) {
            insertEnd(value);
            return true;
        }
        Node node = new Node(value);
        node.next = current.next;
        node.prev = current;
        current.next.prev = node;
        current.next = node;
        return true;
    }

    /** Insert Before a Node - inserts before the FIRST node holding 'target'. Returns false if not found. */
    public boolean insertBefore(int target, int value) {
        Node current = head;
        while (current != null && current.data != target) current = current.next;
        if (current == null) return false;
        if (current == head) {
            insertBeginning(value);
            return true;
        }
        Node node = new Node(value);
        node.prev = current.prev;
        node.next = current;
        current.prev.next = node;
        current.prev = node;
        return true;
    }

    /** Delete from Beginning - head moves to the next node. O(1). Returns false if the list is empty. */
    public boolean deleteBeginning() {
        if (head == null) return false;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        return true;
    }

    /** Delete from End - removes the last node. Returns false if the list is empty. */
    public boolean deleteEnd() {
        if (tail == null) return false;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        return true;
    }

    /** Delete from Position (1-based). Returns false for an invalid position or an empty list. */
    public boolean deleteAtPosition(int position) {
        if (position < 1 || head == null) return false;
        if (position == 1) return deleteBeginning();
        Node current = head;
        for (int i = 1; current != null && i < position; i++) current = current.next;
        if (current == null) return false;
        if (current == tail) return deleteEnd();
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return true;
    }

    /** Delete by Value - removes the FIRST node holding 'value'. Returns false if not found. */
    public boolean deleteByValue(int value) {
        Node current = head;
        while (current != null && current.data != value) current = current.next;
        if (current == null) return false;
        if (current == head) return deleteBeginning();
        if (current == tail) return deleteEnd();
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return true;
    }

    /** Search - returns the 1-based position of the first match, or -1 if not found. O(n). */
    public int search(int value) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data == value) return position;
            current = current.next;
            position++;
        }
        return -1;
    }

    /** Update - replaces the first node holding oldValue with newValue. Returns false if not found. */
    public boolean update(int oldValue, int newValue) {
        Node current = head;
        while (current != null) {
            if (current.data == oldValue) {
                current.data = newValue;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Forward text, for example: NULL <- 10 <-> 20 <-> 30 <-> NULL */
    public String forwardText() {
        if (head == null) return "NULL";
        StringBuilder sb = new StringBuilder("NULL <- ");
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.data).append(" <-> ");
        }
        sb.append("NULL");
        return sb.toString();
    }

    /** Backward text (starts at tail and follows prev links). */
    public String backwardText() {
        if (tail == null) return "NULL";
        StringBuilder sb = new StringBuilder("NULL <- ");
        for (Node current = tail; current != null; current = current.prev) {
            sb.append(current.data).append(" <-> ");
        }
        sb.append("NULL");
        return sb.toString();
    }

    /** Display Forward - traverse from head using next links. */
    public void displayForward() {
        System.out.println(head == null ? "List is empty." : forwardText());
    }

    /** Display Backward - traverse from tail using prev links. */
    public void displayBackward() {
        System.out.println(tail == null ? "List is empty." : backwardText());
    }

    /** Count Nodes - traverses the whole list and counts the nodes. O(n). */
    public int count() {
        int count = 0;
        for (Node current = head; current != null; current = current.next) count++;
        return count;
    }

    /** Find Maximum - traverses once and keeps the largest value. Returns null for an empty list. */
    public Integer maximum() {
        if (head == null) return null;
        int max = head.data;
        for (Node current = head.next; current != null; current = current.next)
            if (current.data > max) max = current.data;
        return max;
    }

    /** Find Minimum - traverses once and keeps the smallest value. Returns null for an empty list. */
    public Integer minimum() {
        if (head == null) return null;
        int min = head.data;
        for (Node current = head.next; current != null; current = current.next)
            if (current.data < min) min = current.data;
        return min;
    }

    /** Find Middle - slow pointer moves 1 step, fast pointer moves 2 steps; when fast ends, slow is the middle.
     * For an even number of nodes, the SECOND middle node is returned. */
    public Integer middle() {
        if (head == null) return null;
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    /** Reverse - swaps prev and next in every node, then swaps head and tail. O(n), extra space O(1). */
    public void reverse() {
        Node current = head;
        while (current != null) {
            Node oldNext = current.next;
            current.next = current.prev;
            current.prev = oldNext;
            current = oldNext;
        }
        Node temp = head;
        head = tail;
        tail = temp;
    }

    /**
     * Sort - ascending order using Bubble Sort (swaps the data of neighbouring nodes).
     * Only data is swapped, so the prev and next links stay valid.
     * Time O(n^2). Stops early when a full pass makes no swap.
     */
    public void sort() {
        if (head == null || head.next == null) return; // empty or single node: already sorted
        boolean swapped;
        Node lastSorted = null; // nodes from here to the end are already in place
        do {
            swapped = false;
            Node current = head;
            while (current.next != lastSorted) {
                if (current.data > current.next.data) {
                    int temp = current.data;
                    current.data = current.next.data;
                    current.next.data = temp;
                    swapped = true;
                }
                current = current.next;
            }
            lastSorted = current;
        } while (swapped);
    }

    /**
     * Remove Duplicates - keeps the first occurrence of every value.
     * For each node, every later node with the same value is unlinked
     * (both prev and next links are repaired, head and tail are updated).
     * Time O(n^2), extra space O(1). Returns how many nodes were removed.
     */
    public int removeDuplicates() {
        int removed = 0;
        Node outer = head;
        while (outer != null) {
            Node inner = outer.next;
            while (inner != null) {
                Node following = inner.next; // remember before unlinking
                if (inner.data == outer.data) {
                    if (inner.prev != null) inner.prev.next = inner.next; else head = inner.next;
                    if (inner.next != null) inner.next.prev = inner.prev; else tail = inner.prev;
                    removed++;
                }
                inner = following;
            }
            outer = outer.next;
        }
        return removed;
    }

    /** Runs the menu for this list until the user chooses Exit. */
    public void runMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("********** Operation on Doubly Linked List **********");
            System.out.println();
            System.out.println("1. Insert at Beginning");
            System.out.println("2. Insert at End");
            System.out.println("3. Insert at Position");
            System.out.println("4. Insert After a Node");
            System.out.println("5. Insert Before a Node");
            System.out.println();
            System.out.println("6. Delete from Beginning");
            System.out.println("7. Delete from End");
            System.out.println("8. Delete from Position");
            System.out.println("9. Delete by Value");
            System.out.println();
            System.out.println("10. Search");
            System.out.println("11. Update");
            System.out.println("12. Display Forward");
            System.out.println("13. Display Backward");
            System.out.println("14. Count Nodes");
            System.out.println("15. Find Maximum");
            System.out.println("16. Find Minimum");
            System.out.println("17. Find Middle Element");
            System.out.println("18. Reverse");
            System.out.println("19. Sort");
            System.out.println("20. Remove Duplicates");
            System.out.println("21. Exit");
            System.out.println();
            int choice = Main.readInt(sc, "Enter the choice:- ");
            if (choice == 21) {
                System.out.println("Returning to the main menu.");
                return;
            }
            switch (choice) {
                case 1 -> insertBeginning(Main.readInt(sc, "Enter value: "));
                case 2 -> insertEnd(Main.readInt(sc, "Enter value: "));
                case 3 -> {
                    int value = Main.readInt(sc, "Enter value: ");
                    int pos = Main.readInt(sc, "Enter 1-based position: ");
                    System.out.println(insertAtPosition(value, pos) ? "Inserted." : "Invalid position.");
                }
                case 4 -> {
                    int target = Main.readInt(sc, "Insert after which value? ");
                    int value = Main.readInt(sc, "Enter new value: ");
                    System.out.println(insertAfter(target, value) ? "Inserted." : "Target not found.");
                }
                case 5 -> {
                    int target = Main.readInt(sc, "Insert before which value? ");
                    int value = Main.readInt(sc, "Enter new value: ");
                    System.out.println(insertBefore(target, value) ? "Inserted." : "Target not found.");
                }
                case 6 -> System.out.println(deleteBeginning() ? "Deleted." : "List is empty.");
                case 7 -> System.out.println(deleteEnd() ? "Deleted." : "List is empty.");
                case 8 -> System.out.println(deleteAtPosition(Main.readInt(sc, "Enter position: ")) ? "Deleted." : "Invalid position or empty list.");
                case 9 -> System.out.println(deleteByValue(Main.readInt(sc, "Enter value: ")) ? "Deleted." : "Value not found.");
                case 10 -> {
                    int pos = search(Main.readInt(sc, "Enter value to search: "));
                    System.out.println(pos == -1 ? "Value not found." : "Value found at position " + pos + ".");
                }
                case 11 -> {
                    int oldValue = Main.readInt(sc, "Value to update: ");
                    int newValue = Main.readInt(sc, "New value: ");
                    System.out.println(update(oldValue, newValue) ? "Updated." : "Value not found.");
                }
                case 12 -> displayForward();
                case 13 -> displayBackward();
                case 14 -> System.out.println("Node count: " + count());
                case 15 -> System.out.println(maximum() == null ? "List is empty." : "Maximum: " + maximum());
                case 16 -> System.out.println(minimum() == null ? "List is empty." : "Minimum: " + minimum());
                case 17 -> System.out.println(middle() == null ? "List is empty." : "Middle element: " + middle());
                case 18 -> {
                    if (count() < 2) {
                        System.out.println("Nothing to reverse (list has fewer than 2 nodes).");
                    } else {
                        reverse();
                        System.out.println("List reversed.");
                        displayForward();
                    }
                }
                case 19 -> {
                    if (count() < 2) {
                        System.out.println("Nothing to sort (list has fewer than 2 nodes).");
                    } else {
                        sort();
                        System.out.println("List sorted in ascending order.");
                        displayForward();
                    }
                }
                case 20 -> System.out.println(removeDuplicates() + " duplicate node(s) removed.");
                default -> System.out.println("Invalid choice. Please select from 1 to 21.");
            }
        }
    }
}

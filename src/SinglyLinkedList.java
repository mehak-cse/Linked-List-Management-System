import java.util.Scanner;

/**
 * Singly linked list implementation for the Linked List Management System.
 * Each node stores data and a reference to the next node.
 */
public class SinglyLinkedList {
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    /** Returns true when the list has no nodes (head is null). */
    public boolean isEmpty() {
        return head == null;
    }

    /** Insert at Beginning - the new node becomes the head. O(1). */
    public void insertBeginning(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
    }

    /** Insert at End - walks to the last node and attaches the new node. O(n) because there is no tail. */
    public void insertEnd(int value) {
        Node node = new Node(value);
        if (head == null) {
            head = node;
            return;
        }
        Node current = head;
        while (current.next != null) current = current.next;
        current.next = node;
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
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        return true;
    }

    /** Insert After a Node - inserts after the FIRST node holding 'target'. Returns false if not found. */
    public boolean insertAfter(int target, int value) {
        Node current = head;
        while (current != null && current.data != target) current = current.next;
        if (current == null) return false;
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        return true;
    }

    /** Insert Before a Node - inserts before the FIRST node holding 'target'. Returns false if not found. */
    public boolean insertBefore(int target, int value) {
        if (head == null) return false;
        if (head.data == target) {
            insertBeginning(value);
            return true;
        }
        Node current = head;
        while (current.next != null && current.next.data != target) current = current.next;
        if (current.next == null) return false;
        Node node = new Node(value);
        node.next = current.next;
        current.next = node;
        return true;
    }

    /** Delete from Beginning - head moves to the next node. O(1). Returns false if the list is empty. */
    public boolean deleteBeginning() {
        if (head == null) return false;
        head = head.next;
        return true;
    }

    /** Delete from End - removes the last node. Returns false if the list is empty. */
    public boolean deleteEnd() {
        if (head == null) return false;
        if (head.next == null) {
            head = null;
            return true;
        }
        Node current = head;
        while (current.next.next != null) current = current.next;
        current.next = null;
        return true;
    }

    /** Delete from Position (1-based). Returns false for an invalid position or an empty list. */
    public boolean deleteAtPosition(int position) {
        if (position < 1 || head == null) return false;
        if (position == 1) return deleteBeginning();
        Node current = head;
        for (int i = 1; current.next != null && i < position - 1; i++) current = current.next;
        if (current.next == null) return false;
        current.next = current.next.next;
        return true;
    }

    /** Delete by Value - removes the FIRST node holding 'value'. Returns false if not found. */
    public boolean deleteByValue(int value) {
        if (head == null) return false;
        if (head.data == value) return deleteBeginning();
        Node current = head;
        while (current.next != null && current.next.data != value) current = current.next;
        if (current.next == null) return false;
        current.next = current.next.next;
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

    /** Returns the list as text, for example: 10 -> 20 -> 30 -> NULL */
    public String asText() {
        StringBuilder sb = new StringBuilder();
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.data).append(" -> ");
        }
        sb.append("NULL");
        return sb.toString();
    }

    /** Display - traverse from head to NULL and print the list. */
    public void display() {
        if (head == null) {
            System.out.println("List is empty.");
        } else {
            System.out.println(asText());
        }
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
        return slow.data; // For an even-length list, returns the second middle.
    }

    /** Reverse - flips every next link using previous, current and nextNode. O(n), extra space O(1). */
    public void reverse() {
        Node previous = null, current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }
        head = previous;
    }

    /**
     * Sort - ascending order using Bubble Sort (swaps the data of neighbouring nodes).
     * After every pass the largest remaining value reaches its final place.
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
     * For each node, every later node with the same value is unlinked.
     * Time O(n^2), extra space O(1). Returns how many nodes were removed.
     */
    public int removeDuplicates() {
        int removed = 0;
        Node outer = head;
        while (outer != null) {
            Node inner = outer;
            while (inner.next != null) {
                if (inner.next.data == outer.data) {
                    inner.next = inner.next.next; // skip the duplicate node
                    removed++;
                } else {
                    inner = inner.next;
                }
            }
            outer = outer.next;
        }
        return removed;
    }

    /** Runs the menu for this list until the user chooses Exit. */
    public void runMenu(Scanner sc) {
        while (true) {
            System.out.println();
            System.out.println("********** Operation on Singly Linked List **********");
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
            System.out.println("12. Display");
            System.out.println("13. Count Nodes");
            System.out.println("14. Find Maximum");
            System.out.println("15. Find Minimum");
            System.out.println("16. Find Middle Element");
            System.out.println("17. Reverse");
            System.out.println("18. Sort");
            System.out.println("19. Remove Duplicates");
            System.out.println("20. Exit");
            System.out.println();
            int choice = Main.readInt(sc, "Enter the choice:- ");
            if (choice == 20) {
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
                case 12 -> display();
                case 13 -> System.out.println("Node count: " + count());
                case 14 -> System.out.println(maximum() == null ? "List is empty." : "Maximum: " + maximum());
                case 15 -> System.out.println(minimum() == null ? "List is empty." : "Minimum: " + minimum());
                case 16 -> System.out.println(middle() == null ? "List is empty." : "Middle element: " + middle());
                case 17 -> {
                    if (count() < 2) {
                        System.out.println("Nothing to reverse (list has fewer than 2 nodes).");
                    } else {
                        reverse();
                        System.out.println("List reversed.");
                        display();
                    }
                }
                case 18 -> {
                    if (count() < 2) {
                        System.out.println("Nothing to sort (list has fewer than 2 nodes).");
                    } else {
                        sort();
                        System.out.println("List sorted in ascending order.");
                        display();
                    }
                }
                case 19 -> System.out.println(removeDuplicates() + " duplicate node(s) removed.");
                default -> System.out.println("Invalid choice. Please select from 1 to 20.");
            }
        }
    }
}

/**
 * EdgeCaseTests
 * -------------
 * Runs every edge case required by the project (Section 10) against both
 * the Singly and the Doubly Linked List, and prints PASS or FAIL for each.
 * Run it with:  java -cp out EdgeCaseTests
 */
public class EdgeCaseTests {

    private static int passed = 0;
    private static int failed = 0;

    // ------------------------------------------------------------------
    // Small checking helpers
    // ------------------------------------------------------------------
    private static void check(String testName, Object expected, Object actual) {
        boolean ok = (expected == null) ? (actual == null) : expected.equals(actual);
        if (ok) {
            passed++;
            System.out.println("[PASS] " + testName);
        } else {
            failed++;
            System.out.println("[FAIL] " + testName);
            System.out.println("       expected: " + expected);
            System.out.println("       actual  : " + actual);
        }
    }

    private static SinglyLinkedList singly(int... values) {
        SinglyLinkedList list = new SinglyLinkedList();
        for (int v : values) {
            list.insertEnd(v);
        }
        return list;
    }

    private static DoublyLinkedList doubly(int... values) {
        DoublyLinkedList list = new DoublyLinkedList();
        for (int v : values) {
            list.insertEnd(v);
        }
        return list;
    }

    // ------------------------------------------------------------------
    // Main
    // ------------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   LINKED LIST MANAGEMENT SYSTEM - TEST REPORT");
        System.out.println("==================================================");

        demoSingly();
        demoDoubly();
        testSingly();
        testDoubly();

        System.out.println();
        System.out.println("==================================================");
        System.out.println("Total tests : " + (passed + failed));
        System.out.println("Passed      : " + passed);
        System.out.println("Failed      : " + failed);
        System.out.println("==================================================");

        if (failed > 0) {
            System.exit(1);
        }
    }

    // ==================================================================
    // DEMONSTRATIONS (same as the example output in the assignment)
    // ==================================================================
    private static void demoSingly() {
        System.out.println();
        System.out.println("---------- DEMO: Singly Linked List ----------");
        SinglyLinkedList list = singly(40, 10, 30, 20, 50);
        System.out.println("Before:");
        list.display();
        list.sort();
        System.out.println("After Sort:");
        list.display();
        list.reverse();
        System.out.println("After Reverse:");
        list.display();
    }

    private static void demoDoubly() {
        System.out.println();
        System.out.println("---------- DEMO: Doubly Linked List ----------");
        DoublyLinkedList list = doubly(40, 10, 30, 20);
        list.sort();
        System.out.println("Forward:");
        list.displayForward();
        System.out.println("Backward:");
        list.displayBackward();
    }

    // ==================================================================
    // TESTS FOR THE SINGLY LINKED LIST
    // ==================================================================
    private static void testSingly() {
        System.out.println();
        System.out.println("---------- TESTS: Singly Linked List ----------");

        // 1. Empty linked list
        SinglyLinkedList empty = new SinglyLinkedList();
        check("S01 Empty list displays NULL", "NULL", empty.asText());
        check("S02 Empty list count is 0", 0, empty.count());
        check("S03 Delete from beginning on empty list fails", false, empty.deleteBeginning());
        check("S04 Delete from end on empty list fails", false, empty.deleteEnd());
        check("S05 Maximum of empty list is null", null, empty.maximum());
        check("S06 Minimum of empty list is null", null, empty.minimum());
        check("S07 Middle of empty list is null", null, empty.middle());
        check("S08 Search in empty list returns -1", -1, empty.search(5));

        // 2. One-node list
        SinglyLinkedList one = singly(7);
        check("S09 One-node list displays correctly", "7 -> NULL", one.asText());
        check("S10 One-node middle is the node itself", 7, one.middle());
        one.deleteEnd();
        check("S11 Delete end of one-node list gives empty list", "NULL", one.asText());

        // 3. Insert / delete at position 1
        SinglyLinkedList p1 = singly(20, 30);
        p1.insertAtPosition(10, 1);
        check("S12 Insert at position 1", "10 -> 20 -> 30 -> NULL", p1.asText());
        p1.deleteAtPosition(1);
        check("S13 Delete at position 1", "20 -> 30 -> NULL", p1.asText());

        // 4. Insert at the last valid position
        SinglyLinkedList last = singly(10, 20);
        check("S14 Insert at position count+1 succeeds", true, last.insertAtPosition(30, 3));
        check("S15 List after last-position insert", "10 -> 20 -> 30 -> NULL", last.asText());

        // 5. Invalid / out-of-range position
        SinglyLinkedList inv = singly(10, 20);
        check("S16 Insert at position 0 fails", false, inv.insertAtPosition(5, 0));
        check("S17 Insert at position 99 fails", false, inv.insertAtPosition(5, 99));
        check("S18 Insert at negative position fails", false, inv.insertAtPosition(5, -3));
        check("S19 Delete at position 0 fails", false, inv.deleteAtPosition(0));
        check("S20 Delete at position 99 fails", false, inv.deleteAtPosition(99));
        check("S21 List unchanged after invalid operations", "10 -> 20 -> NULL", inv.asText());

        // 6. Delete a value that does not exist
        SinglyLinkedList nf = singly(10, 20, 30);
        check("S22 Delete missing value fails", false, nf.deleteByValue(99));
        check("S23 List unchanged after failed delete", "10 -> 20 -> 30 -> NULL", nf.asText());
        check("S24 Insert after missing value fails", false, nf.insertAfter(99, 1));
        check("S25 Insert before missing value fails", false, nf.insertBefore(99, 1));
        check("S26 Update missing value fails", false, nf.update(99, 1));

        // 7. Duplicate values
        SinglyLinkedList dup = singly(10, 20, 10, 30, 20, 10);
        check("S27 Search returns first occurrence", 1, dup.search(10));
        dup.deleteByValue(10);
        check("S28 Delete by value removes only first match", "20 -> 10 -> 30 -> 20 -> 10 -> NULL", dup.asText());
        check("S29 Remove duplicates returns removed count", 2, dup.removeDuplicates());
        check("S30 Remove duplicates keeps first occurrences", "20 -> 10 -> 30 -> NULL", dup.asText());

        // 8. Zero and negative values
        SinglyLinkedList neg = singly(0, -5, 10, -20, 0);
        check("S31 Maximum with negatives and zero", 10, neg.maximum());
        check("S32 Minimum with negatives and zero", -20, neg.minimum());
        neg.sort();
        check("S33 Sort with negatives and zero", "-20 -> -5 -> 0 -> 0 -> 10 -> NULL", neg.asText());

        // 9. Sort an empty or single-node list
        SinglyLinkedList se = new SinglyLinkedList();
        se.sort();
        check("S34 Sort empty list is safe", "NULL", se.asText());
        SinglyLinkedList s1 = singly(5);
        s1.sort();
        check("S35 Sort single-node list is safe", "5 -> NULL", s1.asText());

        // 10. Reverse an empty or single-node list
        SinglyLinkedList re = new SinglyLinkedList();
        re.reverse();
        check("S36 Reverse empty list is safe", "NULL", re.asText());
        SinglyLinkedList r1 = singly(5);
        r1.reverse();
        check("S37 Reverse single-node list is safe", "5 -> NULL", r1.asText());

        // Extra checks for normal behaviour
        SinglyLinkedList n = singly(10, 20, 30);
        n.insertAfter(20, 25);
        n.insertBefore(10, 5);
        check("S38 Insert after and before a node", "5 -> 10 -> 20 -> 25 -> 30 -> NULL", n.asText());
        check("S39 Middle of odd-length list", 20, n.middle());
        n.deleteEnd();
        check("S40 Middle of even-length list (second middle)", 20, n.middle());
        n.reverse();
        check("S41 Reverse normal list", "25 -> 20 -> 10 -> 5 -> NULL", n.asText());
        n.update(20, 21);
        check("S42 Update a value", "25 -> 21 -> 10 -> 5 -> NULL", n.asText());
        n.deleteEnd();
        check("S43 Delete from end", "25 -> 21 -> 10 -> NULL", n.asText());
        n.deleteAtPosition(2);
        check("S44 Delete from middle position", "25 -> 10 -> NULL", n.asText());
    }

    // ==================================================================
    // TESTS FOR THE DOUBLY LINKED LIST
    // ==================================================================
    private static void testDoubly() {
        System.out.println();
        System.out.println("---------- TESTS: Doubly Linked List ----------");

        // 1. Empty linked list
        DoublyLinkedList empty = new DoublyLinkedList();
        check("D01 Empty list displays NULL (forward)", "NULL", empty.forwardText());
        check("D02 Empty list displays NULL (backward)", "NULL", empty.backwardText());
        check("D03 Empty list count is 0", 0, empty.count());
        check("D04 Delete from beginning on empty list fails", false, empty.deleteBeginning());
        check("D05 Delete from end on empty list fails", false, empty.deleteEnd());
        check("D06 Maximum of empty list is null", null, empty.maximum());
        check("D07 Middle of empty list is null", null, empty.middle());

        // 2. One-node list
        DoublyLinkedList one = doubly(7);
        check("D08 One-node forward", "NULL <- 7 <-> NULL", one.forwardText());
        check("D09 One-node backward", "NULL <- 7 <-> NULL", one.backwardText());
        one.deleteBeginning();
        check("D10 Delete only node gives empty list", "NULL", one.forwardText());
        one.insertEnd(8);
        check("D11 Insert into emptied list works (head and tail reset)", "NULL <- 8 <-> NULL", one.backwardText());

        // 3. Insert / delete at position 1
        DoublyLinkedList p1 = doubly(20, 30);
        p1.insertAtPosition(10, 1);
        check("D12 Insert at position 1 (forward)", "NULL <- 10 <-> 20 <-> 30 <-> NULL", p1.forwardText());
        check("D13 Insert at position 1 (backward)", "NULL <- 30 <-> 20 <-> 10 <-> NULL", p1.backwardText());
        p1.deleteAtPosition(1);
        check("D14 Delete at position 1 (forward)", "NULL <- 20 <-> 30 <-> NULL", p1.forwardText());
        check("D15 Delete at position 1 (backward)", "NULL <- 30 <-> 20 <-> NULL", p1.backwardText());

        // 4. Insert at the last valid position
        DoublyLinkedList last = doubly(10, 20);
        check("D16 Insert at position count+1 succeeds", true, last.insertAtPosition(30, 3));
        check("D17 Forward after last-position insert", "NULL <- 10 <-> 20 <-> 30 <-> NULL", last.forwardText());
        check("D18 Backward after last-position insert", "NULL <- 30 <-> 20 <-> 10 <-> NULL", last.backwardText());

        // 5. Invalid / out-of-range position
        DoublyLinkedList inv = doubly(10, 20);
        check("D19 Insert at position 0 fails", false, inv.insertAtPosition(5, 0));
        check("D20 Insert at position 99 fails", false, inv.insertAtPosition(5, 99));
        check("D21 Delete at position 0 fails", false, inv.deleteAtPosition(0));
        check("D22 Delete at position 99 fails", false, inv.deleteAtPosition(99));
        check("D23 List unchanged after invalid operations", "NULL <- 10 <-> 20 <-> NULL", inv.forwardText());

        // 6. Delete a value that does not exist
        DoublyLinkedList nf = doubly(10, 20, 30);
        check("D24 Delete missing value fails", false, nf.deleteByValue(99));
        check("D25 Insert after missing value fails", false, nf.insertAfter(99, 1));
        check("D26 Insert before missing value fails", false, nf.insertBefore(99, 1));
        check("D27 Update missing value fails", false, nf.update(99, 1));

        // 7. Duplicate values
        DoublyLinkedList dup = doubly(10, 20, 10, 30, 20, 10);
        check("D28 Search returns first occurrence", 1, dup.search(10));
        check("D29 Remove duplicates returns removed count", 3, dup.removeDuplicates());
        check("D30 Forward after remove duplicates", "NULL <- 10 <-> 20 <-> 30 <-> NULL", dup.forwardText());
        check("D31 Backward after remove duplicates", "NULL <- 30 <-> 20 <-> 10 <-> NULL", dup.backwardText());

        // 8. Zero and negative values
        DoublyLinkedList neg = doubly(0, -5, 10, -20, 0);
        check("D32 Maximum with negatives and zero", 10, neg.maximum());
        check("D33 Minimum with negatives and zero", -20, neg.minimum());
        neg.sort();
        check("D34 Sort with negatives and zero (forward)", "NULL <- -20 <-> -5 <-> 0 <-> 0 <-> 10 <-> NULL", neg.forwardText());
        check("D35 Sort with negatives and zero (backward)", "NULL <- 10 <-> 0 <-> 0 <-> -5 <-> -20 <-> NULL", neg.backwardText());

        // 9. Sort an empty or single-node list
        DoublyLinkedList se = new DoublyLinkedList();
        se.sort();
        check("D36 Sort empty list is safe", "NULL", se.forwardText());
        DoublyLinkedList s1 = doubly(5);
        s1.sort();
        check("D37 Sort single-node list is safe", "NULL <- 5 <-> NULL", s1.forwardText());

        // 10. Reverse an empty or single-node list
        DoublyLinkedList re = new DoublyLinkedList();
        re.reverse();
        check("D38 Reverse empty list is safe", "NULL", re.forwardText());
        DoublyLinkedList r1 = doubly(5);
        r1.reverse();
        check("D39 Reverse single-node list is safe", "NULL <- 5 <-> NULL", r1.forwardText());

        // Extra checks for normal behaviour (both directions verify prev/next links)
        DoublyLinkedList n = doubly(10, 20, 30);
        n.insertAfter(30, 40);   // after the tail
        n.insertBefore(10, 5);   // before the head
        n.insertAfter(20, 25);   // in the middle
        n.insertBefore(30, 27);  // in the middle
        check("D40 Insert after/before (forward)", "NULL <- 5 <-> 10 <-> 20 <-> 25 <-> 27 <-> 30 <-> 40 <-> NULL", n.forwardText());
        check("D41 Insert after/before (backward)", "NULL <- 40 <-> 30 <-> 27 <-> 25 <-> 20 <-> 10 <-> 5 <-> NULL", n.backwardText());
        n.reverse();
        check("D42 Reverse (forward)", "NULL <- 40 <-> 30 <-> 27 <-> 25 <-> 20 <-> 10 <-> 5 <-> NULL", n.forwardText());
        check("D43 Reverse (backward)", "NULL <- 5 <-> 10 <-> 20 <-> 25 <-> 27 <-> 30 <-> 40 <-> NULL", n.backwardText());
        n.deleteEnd();
        n.deleteBeginning();
        check("D44 Delete from both ends (forward)", "NULL <- 30 <-> 27 <-> 25 <-> 20 <-> 10 <-> NULL", n.forwardText());
        check("D45 Delete from both ends (backward)", "NULL <- 10 <-> 20 <-> 25 <-> 27 <-> 30 <-> NULL", n.backwardText());
        n.deleteAtPosition(3);
        check("D46 Delete from middle position", "NULL <- 30 <-> 27 <-> 20 <-> 10 <-> NULL", n.forwardText());
        n.deleteByValue(27);
        check("D47 Delete by value", "NULL <- 30 <-> 20 <-> 10 <-> NULL", n.forwardText());
        check("D48 Middle element", 20, n.middle());
    }
}

import java.util.Scanner;

/**
 * Entry point for the Linked List Management System.
 *
 * Shows the main menu and sends the user to the Singly Linked List menu,
 * the Doubly Linked List menu or the comparison screen.
 * The two lists are created once, so their data stays available
 * when the user returns to the main menu.
 */
public class Main {

    private static final String LINE = "==================================================";

    // When true (run with the argument --echo), typed answers are printed back.
    // This is only used to record a readable sample-output file from piped input.
    private static boolean echoInput = false;

    /**
     * Reads one whole number from the user.
     * Keeps asking until the input is valid, so wrong input never crashes the program.
     */
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!sc.hasNextLine()) { // input ended (for example Ctrl+D): exit politely
                System.out.println();
                System.out.println("No more input. Exiting the program.");
                System.exit(0);
            }
            String input = sc.nextLine().trim();
            if (echoInput) {
                System.out.println(input);
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    public static void main(String[] args) {
        echoInput = args.length > 0 && args[0].equals("--echo");

        Scanner sc = new Scanner(System.in);
        SinglyLinkedList singly = new SinglyLinkedList();
        DoublyLinkedList doubly = new DoublyLinkedList();

        while (true) {
            System.out.println();
            System.out.println(LINE);
            System.out.println("          LINKED LIST MANAGEMENT SYSTEM");
            System.out.println(LINE);
            System.out.println();
            System.out.println("1. Singly Linked List");
            System.out.println("2. Doubly Linked List");
            System.out.println("3. Compare Singly vs Doubly Linked List");
            System.out.println("4. Exit");
            System.out.println();
            int choice = readInt(sc, "Enter your choice: ");

            switch (choice) {
                case 1 -> singly.runMenu(sc);
                case 2 -> doubly.runMenu(sc);
                case 3 -> compare();
                case 4 -> {
                    System.out.println("Thank you for using Linked List Management System.");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Please select from 1 to 4.");
            }
        }
    }

    /** Comparison screen: Singly vs Doubly Linked List. */
    private static void compare() {
        System.out.println();
        System.out.println(LINE);
        System.out.println("     SINGLY vs DOUBLY LINKED LIST - COMPARISON");
        System.out.println(LINE);
        System.out.printf("%-26s %-22s %-22s%n", "Feature", "Singly Linked List", "Doubly Linked List");
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-26s %-22s %-22s%n", "Links in each node", "next only", "prev and next");
        System.out.printf("%-26s %-22s %-22s%n", "References kept by list", "head", "head and tail");
        System.out.printf("%-26s %-22s %-22s%n", "Memory per node", "Less", "More (extra prev)");
        System.out.printf("%-26s %-22s %-22s%n", "Traversal direction", "Forward only", "Forward and backward");
        System.out.printf("%-26s %-22s %-22s%n", "Insert at Beginning", "O(1)", "O(1)");
        System.out.printf("%-26s %-22s %-22s%n", "Insert at End", "O(n)", "O(1) using tail");
        System.out.printf("%-26s %-22s %-22s%n", "Delete from Beginning", "O(1)", "O(1)");
        System.out.printf("%-26s %-22s %-22s%n", "Delete from End", "O(n)", "O(1) using tail");
        System.out.printf("%-26s %-22s %-22s%n", "Search / Count / Display", "O(n)", "O(n)");
        System.out.printf("%-26s %-22s %-22s%n", "Reverse", "3 refs: prev/curr/next", "Swap prev and next");
        System.out.printf("%-26s %-22s %-22s%n", "Bubble Sort", "O(n^2)", "O(n^2)");
        System.out.printf("%-26s %-22s %-22s%n", "Remove Duplicates", "O(n^2)", "O(n^2)");
        System.out.printf("%-26s %-22s %-22s%n", "Coding difficulty", "Simpler", "More links to update");
        System.out.println();
        System.out.println("Summary: A Singly Linked List is simple and uses less memory.");
        System.out.println("A Doubly Linked List uses more memory but allows backward");
        System.out.println("traversal and fast insertion/deletion at the end.");
    }
}

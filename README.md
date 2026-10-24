# Linked List Management System

A menu-driven Java console application that implements a **Singly Linked List** and a **Doubly Linked List** from scratch. Every required operation is written as a separate method.

---

## Objective

To practise the Linked List data structure by building, without any array or built-in collection (`ArrayList`, `LinkedList`, `HashSet`, etc.):

- a Singly Linked List,
- a Doubly Linked List,
- a menu-driven program to work with both, and
- automatic tests for all important edge cases.

## Language Used

**Java** (JDK 17 or higher; tested with JDK 21)

## Features

- Manual node-based implementation: nodes are joined using object references.
- A separate method for every operation.
- 1-based positions for all position-related operations.
- Input validation: wrong input such as letters, invalid positions or missing values never crashes the program.
- Clear messages for empty list, value not found and invalid position.
- Data of each list is kept when you return to the main menu.
- Comparison screen: Singly vs Doubly Linked List.
- Automatic edge-case test class (`EdgeCaseTests`) with 92 checks.

## Project Structure

```
Linked-List-Management-System/
|-- README.md
|-- .gitignore
|-- src/
|   |-- SinglyLinkedList.java    (Node + all singly list operations + its menu)
|   |-- DoublyLinkedList.java    (Node + all doubly list operations + its menu)
|   |-- Main.java                (main menu, comparison screen, safe input)
|   |-- EdgeCaseTests.java       (automatic edge-case tests)
|-- sample-output/
|   |-- output.txt               (test report + demo output)
|   |-- menu-session.txt         (recorded menu session)
|-- docs/
    |-- project-report.pdf       (design and complexity report)
```

## Singly Linked List

Each node has `data` and `next`. The list keeps only a `head` reference.

```
head -> [10 | next] -> [20 | next] -> [30 | NULL]
```

## Doubly Linked List

Each node has `prev`, `data` and `next`. The list keeps both `head` and `tail`.

```
NULL <- [10] <-> [20] <-> [30] -> NULL
         ^head               ^tail
```

## Operations

| Operation | Singly | Doubly |
|-----------|:------:|:------:|
| Insert at Beginning | Yes | Yes |
| Insert at End | Yes | Yes |
| Insert at Position (1-based) | Yes | Yes |
| Insert After a Node | Yes | Yes |
| Insert Before a Node | Yes | Yes |
| Delete from Beginning | Yes | Yes |
| Delete from End | Yes | Yes |
| Delete from Position | Yes | Yes |
| Delete by Value | Yes | Yes |
| Search | Yes | Yes |
| Update | Yes | Yes |
| Display (Forward) | Yes | Yes |
| Display Backward | - | Yes |
| Count Nodes | Yes | Yes |
| Find Maximum | Yes | Yes |
| Find Minimum | Yes | Yes |
| Find Middle Element (slow/fast pointers) | Yes | Yes |
| Reverse | Yes | Yes |
| Sort (Bubble Sort) | Yes | Yes |
| Remove Duplicates | Yes | Yes |

Rules followed:

- Positions start from 1.
- Insert at Position accepts positions `1` to `count + 1`; Delete from Position accepts `1` to `count`.
- Insert After / Before, Delete by Value, Search and Update act on the **first** matching node.
- For an even-length list, **Find Middle** returns the **second** of the two middle nodes.
- **Sort** is Bubble Sort (swaps data of neighbouring nodes).
- **Remove Duplicates** keeps the first occurrence of each value and uses two loops (no extra collection).

## How to Run

Open a terminal inside the project folder (`Linked-List-Management-System`).

**Step 1 - Compile**

```
javac -d out src/*.java
```

**Step 2 - Run the application**

```
java -cp out Main
```

**Step 3 - Run the edge-case tests (optional)**

```
java -cp out EdgeCaseTests
```

## Sample Output

Singly Linked List:

```
Before:
40 -> 10 -> 30 -> 20 -> 50 -> NULL

After Sort:
10 -> 20 -> 30 -> 40 -> 50 -> NULL

After Reverse:
50 -> 40 -> 30 -> 20 -> 10 -> NULL
```

Doubly Linked List:

```
Forward:
NULL <- 10 <-> 20 <-> 30 <-> 40 <-> NULL

Backward:
NULL <- 40 <-> 30 <-> 20 <-> 10 <-> NULL
```

Test summary:

```
Total tests : 92
Passed      : 92
Failed      : 0
```

Complete outputs are in the `sample-output/` folder (`output.txt` and `menu-session.txt`).

## Time Complexity

| Operation | Singly Linked List | Doubly Linked List |
|-----------|:------------------:|:------------------:|
| Insert at Beginning | O(1) | O(1) |
| Insert at End | O(n) (no tail) | O(1) (tail stored) |
| Insert at Position | O(n) | O(n) |
| Insert After / Before a Node | O(n) | O(n) |
| Delete from Beginning | O(1) | O(1) |
| Delete from End | O(n) | O(1) |
| Delete from Position / by Value | O(n) | O(n) |
| Search / Display / Count | O(n) | O(n) |
| Find Max / Min / Middle | O(n) | O(n) |
| Reverse | O(n), extra space O(1) | O(n), extra space O(1) |
| Bubble Sort | O(n^2) | O(n^2) |
| Remove Duplicates | O(n^2) | O(n^2) |

## Edge Cases Tested

- Empty linked list
- One-node list
- Insert / delete at position 1
- Insert at the last valid position
- Invalid or out-of-range position
- Delete a value that does not exist
- Duplicate values
- Zero and negative values
- Sort an empty or single-node list
- Reverse an empty or single-node list

All of these pass in `EdgeCaseTests` (see `sample-output/output.txt`).

## Author

- Name: Mehak
- System ID: 2025497969
- Course: B.Tech CSE
- Batch: 2
- Section: E

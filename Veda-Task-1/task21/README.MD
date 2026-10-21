# Task 21: Stack-Based Undo System

A Java console text editor simulation demonstrating LIFO state management and undo functionality using `java.util.Deque` and `java.util.ArrayDeque`.

## Features
- **LIFO State Management:** Pushes document snapshots before every modification and pops snapshots during undo operations.
- **Multiple Undo Support:** Reverts state through multi-step stack pop traversals.
- **`ArrayDeque` Efficiency:** Preferred over legacy `java.util.Stack` for non-synchronized, high-performance stack operations.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task21/StackUndoSystem.java
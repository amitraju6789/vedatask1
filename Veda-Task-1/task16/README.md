# Task 16: Build a Library Management System

A console-based Java application managing books, members, checkout/return transactions, and dynamic overdue fine calculations using Java Collections.

## Features
- **Book & Member Management:** Encapsulated domain classes with dynamic tracking.
- **Fast Lookups (`HashMap`):** Uses $O(1)$ key-based searching for books and members by unique IDs.
- **Checkout & Return Pipeline:** Manages inventory state and links transactions between books and members.
- **Overdue Tracking:** Automatically calculates late fine charges ($2/day) when books exceed the 14-day limit.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task16/LibraryManagementSystem.java
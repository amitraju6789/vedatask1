# Task 23: Build a File-Based Student Record System

A Java application demonstrating persistent storage, File I/O operations, and stream management using `BufferedReader`, `BufferedWriter`, and `try-with-resources`.

## Features
- **Persistent File Storage:** Reads and writes student data to `students.txt` formatted in structured CSV records.
- **CRUD Operations:** Supports creating new records, reading all entries, searching by Roll Number, and updating persistent state.
- **Robust Exception Handling:** Utilizes `try-with-resources` to ensure input/output streams are closed cleanly without resource leaks or missing-file crashes.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task23/StudentRecordSystem.java
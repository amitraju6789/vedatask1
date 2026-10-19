# Task 19: Build a Unique Email Registry Using HashSet

A Java console application demonstrating set collections and uniqueness constraints by implementing a duplicate-proof email registration system using `java.util.HashSet`.

## Features
- **Email Normalization:** Converts inputs to lowercase and trims whitespace to prevent case-sensitivity duplicate bypasses.
- **`HashSet` Uniqueness:** Guarantees zero duplicate entries with $O(1)$ time complexity for insertions and lookups.
- **Full Management Pipeline:** Register, verify, remove, and list active unique user accounts.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task19/EmailRegistryDemo.java
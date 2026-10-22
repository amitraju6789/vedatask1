# Task 22: Implement Custom Exception Handling

A Java application simulating a Banking Management System with domain-specific custom exception classes and robust `try-catch-finally` error handling routines.

## Custom Exception Classes
- **`InsufficientBalanceException`**: Thrown when withdrawal exceeds current account balance.
- **`InvalidAccountException`**: Thrown when account lookup fails in database map.
- **`InvalidTransactionAmountException`**: Thrown when negative or zero transaction amounts are supplied.

## Features
- **Application-Specific Errors:** Creates explicit, domain-aware error categories extending `java.lang.Exception`.
- **`finally` Execution Guarantee:** Runs audit logs regardless of transaction success or exception throwing.
- **Non-blocking Error Recovery:** Traps exceptions gracefully without crashing the application runtime.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   
```bash
   javac task22/BankingSystem.java
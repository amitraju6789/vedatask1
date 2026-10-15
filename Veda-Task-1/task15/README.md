# Task 15: Create a Payment System Using Interfaces

A Java application demonstrating interfaces and loosely coupled system design by implementing a modular payment framework supporting Credit Card, UPI, and Wallet methods.

## Features
- **Payment Interface (`PaymentMethod`):** Establishes a common contract containing validation and processing methods.
- **Multiple Implementations:** Fully implements specialized transaction channels (`CreditCardPayment`, `UPIPayment`, and `WalletPayment`).
- **Loosely Coupled Design:** Enables flexible, polymorphic payment processing without relying on concrete implementations.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task15/PaymentSystemDemo.java
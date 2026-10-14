# Task 8: Simple ATM Simulation

A console-based Java ATM application supporting account authentication, balance inquiry, money deposits, and cash withdrawals with robust state management.

## Features
- **PIN Authentication:** Enforces a maximum 3-attempt lock mechanism for security.
- **Encapsulation:** Separates banking state logic (`ATMAccount`) from console execution flow (`ATMSimulation`).
- **Transaction Validation:** Guards against negative deposits, zero-amount inputs, and withdrawals exceeding available balance.
- **State Persistence:** Maintains running account balance across multiple menu transactions during session.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task8/ATMSimulation.java
   java task8.ATMSimulation
   ```
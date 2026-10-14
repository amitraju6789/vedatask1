# Veda Technologies - Java Internship Tasks & Projects

Welcome to the **Veda Technologies Java Internship** repository. This repository contains all practical tasks, console applications, and core Java / OOP implementations developed during the internship program.

---

## 📋 Table of Contents
- [Repository Structure](#repository-structure)
- [Summary of Tasks & Projects](#summary-of-tasks--projects)
- [Technologies & Tools Used](#technologies--tools-used)
- [How to Clone and Run](#how-to-clone-and-run)
- [Key Concepts Demonstrated](#key-concepts-demonstrated)
- [Author](#author)

---

## 📁 Repository Structure

```plaintext
JavaProgrammingWorkspace/
├── README.md                                # Root repository overview and guide
├── Veda-Task-1/                             # Main internship assignments and tasks
│   ├── README.md                            # Veda-Task-1 directory index
│   ├── Project-1/
│   │   ├── Calculator.java                  # Task 1: Command-Line Arithmetic Calculator
│   │   ├── Readme.md
│   │   └── project-2/
│   │       ├── GradeCalculator.java         # Task 2: Student Grade & Percentage Calculator
│   │       └── Readme.md
│   ├── task3/
│   │   ├── NumberGuessingGame.java          # Task 3: Interactive Number Guessing Game
│   │   └── README.md
│   ├── task5/
│   │   ├── MultiplicationTable.java         # Task 5: Dynamic Multiplication Table Generator
│   │   └── README.md
│   ├── task6/
│   │   ├── PalindromeChecker.java           # Task 6: String & Number Palindrome Checker
│   │   └── README.md
│   ├── task7/
│   │   ├── PrimeAnalysis.java               # Task 7: Prime Number Analysis (O(√n)) & Range Generator
│   │   └── README.md
│   ├── task8/
│   │   ├── ATMSimulation.java               # Task 8: ATM Simulation with PIN Security & Encapsulation
│   │   └── README.md
│   ├── task10/
│   │   ├── ConstructorOverloadingDemo.java  # Task 10: Constructor Overloading & `this()` Chaining
│   │   └── README.md
│   ├── task11/
│   │   ├── EmployeeManagementSystem.java    # Task 11: Employee Management System (CRUD & OOP)
│   │   └── README.md
│   ├── task12/
│   │   ├── EmployeeHierarchyDemo.java       # Task 12: Employee Inheritance Hierarchy (`extends`, `super()`)
│   │   └── README.md
│   ├── task13/
│   │   ├── RuntimePolymorphismDemo.java     # Task 13: Runtime Polymorphism & Dynamic Method Dispatch
│   │   └── README.md
│   └── task14/
│       ├── ShapeCalculatorDemo.java         # Task 14: Shape Calculator using Abstraction (Abstract Classes)
│       └── README.md
└── code-java-practices/                     # Core Java direct & scenario practice exercises
```

---

## 🚀 Summary of Tasks & Projects

| Task | Module / Project Name | Core Concepts / Features | Location |
| :--- | :--- | :--- | :--- |
| **Task 1** | **Command-Line Calculator** | Arithmetic operations, division-by-zero checks, switch-case, scanner input | `Veda-Task-1/Project-1/` |
| **Task 2** | **Student Grade Calculator** | Arrays, loops, cumulative marks calculation, percentage & grade mapping | `Veda-Task-1/Project-1/project-2/` |
| **Task 3** | **Number Guessing Game** | `java.util.Random`, guessing feedback (`Too High`/`Too Low`), attempt tracker | `Veda-Task-1/task3/` |
| **Task 5** | **Multiplication Table Generator** | Nested loops, single & range-based table generation, formatted output | `Veda-Task-1/task5/` |
| **Task 6** | **Palindrome Checker** | Two-pointer technique for strings, modulo arithmetic for numbers | `Veda-Task-1/task6/` |
| **Task 7** | **Prime Number Analysis** | $O(\sqrt{n})$ optimized primality test, prime range finder | `Veda-Task-1/task7/` |
| **Task 8** | **ATM Simulation System** | Encapsulated account state, PIN authentication (3 attempts limit), balance & cash handling | `Veda-Task-1/task8/` |
| **Task 10** | **Constructor Overloading** | Constructor chaining via `this()`, parameterized instantiation | `Veda-Task-1/task10/` |
| **Task 11** | **Employee Management System** | Complete CRUD operations, `ArrayList`, unique ID validation, encapsulation | `Veda-Task-1/task11/` |
| **Task 12** | **Employee Hierarchy** | Single & hierarchical inheritance, `super()` constructor chaining, overriding | `Veda-Task-1/task12/` |
| **Task 13** | **Runtime Polymorphism** | Parent reference variable, dynamic method dispatch, notification dispatchers | `Veda-Task-1/task13/` |
| **Task 14** | **Shape Calculator** | Abstract class (`Shape`), abstract methods (`area`, `perimeter`), polymorphic shape processing | `Veda-Task-1/task14/` |

---

## 🛠️ Technologies & Tools Used
- **Programming Language:** Java (JDK 8 / 11 / 17 / 21)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Version Control:** Git & GitHub
- **Operating Environment:** Windows PowerShell / CMD / VS Code

---

## 💻 How to Clone and Run

### 1. Clone the Repository
```bash
git clone https://github.com/amitraju6789/vedatask1.git
cd vedatask1
```

### 2. Navigate to Task Folder and Run

#### To run standalone tasks (Project-1, project-2, task3):
```bash
# Example: Task 1 - Calculator
cd Veda-Task-1/Project-1
javac Calculator.java
java Calculator

# Example: Task 2 - Grade Calculator
cd project-2
javac GradeCalculator.java
java GradeCalculator

# Example: Task 3 - Number Guessing Game
cd ../../task3
javac NumberGuessingGame.java
java NumberGuessingGame
```

#### To run packaged tasks (from `Veda-Task-1` directory):
```bash
cd Veda-Task-1

# Task 5: Multiplication Table
javac task5/MultiplicationTable.java
java task5.MultiplicationTable

# Task 6: Palindrome Checker
javac task6/PalindromeChecker.java
java task6.PalindromeChecker

# Task 7: Prime Analysis
javac task7/PrimeAnalysis.java
java task7.PrimeAnalysis

# Task 8: ATM Simulation
javac task8/ATMSimulation.java
java task8.ATMSimulation

# Task 10: Constructor Overloading
javac task10/ConstructorOverloadingDemo.java
java task10.ConstructorOverloadingDemo

# Task 11: Employee Management System
javac task11/EmployeeManagementSystem.java
java task11.EmployeeManagementSystem

# Task 12: Employee Hierarchy
javac task12/EmployeeHierarchyDemo.java
java task12.EmployeeHierarchyDemo

# Task 13: Runtime Polymorphism
javac task13/RuntimePolymorphismDemo.java
java task13.RuntimePolymorphismDemo

# Task 14: Shape Calculator (Abstraction)
javac task14/ShapeCalculatorDemo.java
java task14.ShapeCalculatorDemo
```

---

## 💡 Key Concepts Demonstrated
1. **Encapsulation:** Protecting data using private variables and controlled getters/setters.
2. **Inheritance:** Code reusability via subclassing and constructor propagation using `super()`.
3. **Polymorphism:** Method overriding and runtime dynamic dispatch through base-class references.
4. **Abstraction:** Designing templates using abstract classes and enforcing contract implementation.
5. **Robust Exception & Edge-Case Handling:** Defensive programming against invalid user inputs, division by zero, and negative balance constraints.

---

## 👤 Author
- **Name:** Amit Kumar Singh
- **GitHub:** [@amitraju6789](https://github.com/amitraju6789)
- **Repository:** [vedatask1](https://github.com/amitraju6789/vedatask1)

# Task 13: Implement Runtime Polymorphism

A Java application demonstrating runtime polymorphism and dynamic method dispatch using parent references for child objects.

## Features
- **Parent Class (`NotificationService`):** Defines a common interface method for sending alerts.
- **Child Classes:** Specialized implementations (`EmailNotification`, `SMSNotification`, `PushNotification`) overriding the base method.
- **Dynamic Method Dispatch:** Resolves overridden method calls at runtime depending on the actual instantiated object type.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task13/RuntimePolymorphismDemo.java
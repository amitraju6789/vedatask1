package task21;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class StackUndoSystem {

    // Using Deque interface implemented by ArrayDeque as a Stack
    private final Deque<String> historyStack = new ArrayDeque<>();
    private StringBuilder currentText = new StringBuilder();

    // Save current state snapshot onto stack before performing a modification
    private void saveState() {
        historyStack.push(currentText.toString());
    }

    // 1. Type / Append Text to Document
    public void typeText(String text) {
        saveState(); // Push snapshot to stack
        if (currentText.length() > 0 && !text.startsWith(" ")) {
            currentText.append(" ");
        }
        currentText.append(text);
        System.out.println("[EDITED] Added text: \"" + text + "\"");
    }

    // 2. Clear Document Content
    public void clearText() {
        if (currentText.length() == 0) {
            System.out.println("[INFO] Document is already empty.");
            return;
        }
        saveState();
        currentText.setLength(0);
        System.out.println("[CLEARED] Document content cleared.");
    }

    // 3. Undo Operation (LIFO Pop)
    public boolean undo() {
        if (historyStack.isEmpty()) {
            System.out.println("[UNDO ERROR] No previous history states available to undo.");
            return false;
        }

        // Pop last state from top of stack
        String previousState = historyStack.pop();
        currentText = new StringBuilder(previousState);
        System.out.println("[UNDO SUCCESS] Reverted to previous state.");
        return true;
    }

    // 4. Multiple Undo Operation
    public void undoMultiple(int steps) {
        if (historyStack.isEmpty()) {
            System.out.println("[UNDO ERROR] No previous history states available to undo.");
            return;
        }

        int undoneCount = 0;
        for (int i = 0; i < steps; i++) {
            if (!historyStack.isEmpty()) {
                currentText = new StringBuilder(historyStack.pop());
                undoneCount++;
            } else {
                break;
            }
        }
        System.out.println("[MULTI-UNDO] Successfully reverted " + undoneCount + " step(s).");
    }

    // Display current document state and history depth
    public void displayDocument() {
        System.out.println("\n-----------------------------------------");
        System.out.println("CURRENT DOCUMENT TEXT:");
        if (currentText.length() == 0) {
            System.out.println("<EMPTY DOCUMENT>");
        } else {
            System.out.println("\"" + currentText + "\"");
        }
        System.out.println("-----------------------------------------");
        System.out.println("Undo History Stack Depth: " + historyStack.size() + " state(s)");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StackUndoSystem editor = new StackUndoSystem();

        // Seed initial edits
        editor.typeText("Hello World!");
        editor.typeText("Welcome to Veda Technologies.");

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("    STACK-BASED TEXT EDITOR (ArrayDeque) ");
            System.out.println("=========================================");
            System.out.println("1. Type / Append Text");
            System.out.println("2. Undo Last Operation");
            System.out.println("3. Undo Multiple Steps");
            System.out.println("4. Clear Document");
            System.out.println("5. View Current Document");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter text to type: ");
                        String input = scanner.nextLine();
                        editor.typeText(input);
                        break;

                    case 2:
                        editor.undo();
                        editor.displayDocument();
                        break;

                    case 3:
                        System.out.print("Enter number of steps to undo: ");
                        if (scanner.hasNextInt()) {
                            int steps = scanner.nextInt();
                            scanner.nextLine();
                            editor.undoMultiple(steps);
                            editor.displayDocument();
                        } else {
                            System.out.println("Invalid input! Please enter an integer.");
                            scanner.next();
                        }
                        break;

                    case 4:
                        editor.clearText();
                        break;

                    case 5:
                        editor.displayDocument();
                        break;

                    case 6:
                        System.out.println("\nExiting Text Editor Simulation. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please select between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid menu number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
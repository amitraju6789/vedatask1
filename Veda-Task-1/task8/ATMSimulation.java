package task8;

import java.util.Scanner;

// Account logic separated from user interaction (Encapsulation)
class ATMAccount {
    private final int pin = 1234;
    private double balance = 5000.00; // Initial default balance

    public boolean verifyPin(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATMSimulation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMAccount account = new ATMAccount();

        System.out.println("=========================================");
        System.out.println("         WELCOME TO VEDA BANK ATM        ");
        System.out.println("=========================================");

        // PIN Verification with attempt limit (Max 3 attempts)
        int attempts = 0;
        boolean isAuthenticated = false;

        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            if (scanner.hasNextInt()) {
                int enteredPin = scanner.nextInt();
                if (account.verifyPin(enteredPin)) {
                    isAuthenticated = true;
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN! Attempts remaining: " + (3 - attempts));
                }
            } else {
                System.out.println("Invalid input! Please enter a numeric PIN.");
                scanner.next(); // Clear invalid token
                attempts++;
            }
        }

        if (!isAuthenticated) {
            System.out.println("\n[ACCOUNT LOCKED] Maximum PIN attempts exceeded. Please try again later.");
            scanner.close();
            return;
        }

        // ATM Main Menu Loop
        boolean exit = false;
        while (!exit) {
            System.out.println("\n-------------- ATM MENU --------------");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.printf("Current Account Balance: $%.2f\n", account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: $");
                        if (scanner.hasNextDouble()) {
                            double depositAmount = scanner.nextDouble();
                            if (account.deposit(depositAmount)) {
                                System.out.printf("Successfully deposited $%.2f. New Balance: $%.2f\n", depositAmount, account.getBalance());
                            } else {
                                System.out.println("Transaction Failed: Deposit amount must be positive.");
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a valid numerical amount.");
                            scanner.next();
                        }
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        if (scanner.hasNextDouble()) {
                            double withdrawAmount = scanner.nextDouble();
                            if (withdrawAmount > account.getBalance()) {
                                System.out.println("Transaction Failed: Insufficient balance!");
                            } else if (account.withdraw(withdrawAmount)) {
                                System.out.printf("Successfully withdrew $%.2f. Remaining Balance: $%.2f\n", withdrawAmount, account.getBalance());
                            } else {
                                System.out.println("Transaction Failed: Amount must be positive.");
                            }
                        } else {
                            System.out.println("Invalid input! Please enter a valid numerical amount.");
                            scanner.next();
                        }
                        break;

                    case 4:
                        System.out.println("Thank you for using Veda Bank ATM. Goodbye!");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid choice! Please select an option between 1 and 4.");
                }
            } else {
                System.out.println("Invalid input! Please select a valid numeric menu option.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
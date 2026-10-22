package task22;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Custom Exception 1: Insufficient Balance (Checked Exception)
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

// Custom Exception 2: Invalid Account (Checked Exception)
class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}

// Custom Exception 3: Invalid Transaction Amount (Checked Exception)
class InvalidTransactionAmountException extends Exception {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}

// Account Domain Model
class BankAccount {
    private final String accountNumber;
    private final String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) throws InvalidTransactionAmountException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException("Deposit amount must be strictly greater than zero! Provided: ₹" + amount);
        }
        this.balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException, InvalidTransactionAmountException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException("Withdrawal amount must be strictly greater than zero! Provided: ₹" + amount);
        }
        if (amount > this.balance) {
            throw new InsufficientBalanceException(
                String.format("Transaction Declined! Attempted to withdraw ₹%.2f, but current balance is only ₹%.2f.", amount, this.balance)
            );
        }
        this.balance -= amount;
    }
}

public class BankingSystem {

    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount findAccount(String accNo) throws InvalidAccountException {
        BankAccount account = accounts.get(accNo);
        if (account == null) {
            throw new InvalidAccountException("Account Lookup Failed! Account number '" + accNo + "' does not exist in our database.");
        }
        return account;
    }

    public void processDeposit(String accNo, double amount) {
        try {
            BankAccount account = findAccount(accNo);
            account.deposit(amount);
            System.out.printf("[SUCCESS] Deposited ₹%.2f to Account %s (%s). Updated Balance: ₹%.2f\n",
                    amount, accNo, account.getHolderName(), account.getBalance());
        } catch (InvalidAccountException | InvalidTransactionAmountException e) {
            System.out.println("[TRANSACTION ERROR] " + e.getMessage());
        } finally {
            System.out.println("[AUDIT LOG] Deposit attempt completed for Account: " + accNo);
        }
    }

    public void processWithdrawal(String accNo, double amount) {
        try {
            BankAccount account = findAccount(accNo);
            account.withdraw(amount);
            System.out.printf("[SUCCESS] Withdrew ₹%.2f from Account %s (%s). Remaining Balance: ₹%.2f\n",
                    amount, accNo, account.getHolderName(), account.getBalance());
        } catch (InvalidAccountException | InsufficientBalanceException | InvalidTransactionAmountException e) {
            System.out.println("[TRANSACTION ERROR] " + e.getMessage());
        } finally {
            System.out.println("[AUDIT LOG] Withdrawal attempt completed for Account: " + accNo);
        }
    }

    public static void main(String[] args) {
        BankingSystem bank = new BankingSystem();

        // Seed initial valid accounts
        bank.addAccount(new BankAccount("ACC1001", "Amit Kumar Singh", 5000.00));
        bank.addAccount(new BankAccount("ACC1002", "Rohan Sharma", 1500.00));

        System.out.println("=================================================");
        System.out.println("   CUSTOM EXCEPTION HANDLING - BANKING SYSTEM   ");
        System.out.println("=================================================");

        // Test Case 1: Valid Deposit
        System.out.println("\n--- Test 1: Valid Deposit ---");
        bank.processDeposit("ACC1001", 1500.00);

        // Test Case 2: Invalid Transaction Amount (Negative Deposit)
        System.out.println("\n--- Test 2: Invalid Deposit Amount ---");
        bank.processDeposit("ACC1001", -500.00);

        // Test Case 3: Insufficient Balance Withdrawal
        System.out.println("\n--- Test 3: Insufficient Balance ---");
        bank.processWithdrawal("ACC1002", 3000.00);

        // Test Case 4: Non-existent / Invalid Account Lookup
        System.out.println("\n--- Test 4: Invalid Account Lookup ---");
        bank.processWithdrawal("ACC9999", 500.00);

        // Test Case 5: Interactive Menu Execution
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n-----------------------------------------");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Check Account Balance");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number: ");
                        String depAcc = scanner.nextLine();
                        System.out.print("Enter Deposit Amount (₹): ");
                        double depAmt = scanner.nextDouble();
                        bank.processDeposit(depAcc, depAmt);
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        String withAcc = scanner.nextLine();
                        System.out.print("Enter Withdrawal Amount (₹): ");
                        double withAmt = scanner.nextDouble();
                        bank.processWithdrawal(withAcc, withAmt);
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        String checkAcc = scanner.nextLine();
                        try {
                            BankAccount acc = bank.findAccount(checkAcc);
                            System.out.printf("[ACCOUNT INFO] Holder: %s | Balance: ₹%.2f\n", acc.getHolderName(), acc.getBalance());
                        } catch (InvalidAccountException e) {
                            System.out.println("[ERROR] " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\nExiting Banking System. All audit logs saved.");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid selection! Please enter 1-4.");
                }
            } else {
                System.out.println("Invalid input format!");
                scanner.next();
            }
        }

        scanner.close();
    }
}
package task15;

import java.util.Scanner;

// 1. Payment Interface defining common contract behavior
interface PaymentMethod {
    boolean validateDetails();
    void processPayment(double amount);
}

// 2. Implementation 1: Credit Card Payment
class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolder;

    public CreditCardPayment(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public boolean validateDetails() {
        // Simple mock validation: check if card number length is 16 digits
        if (cardNumber != null && cardNumber.length() == 16) {
            System.out.println("[Credit Card] Validation successful for " + cardHolder);
            return true;
        }
        System.out.println("[Credit Card] Validation failed: Invalid card number length.");
        return false;
    }

    @Override
    public void processPayment(double amount) {
        if (validateDetails()) {
            System.out.printf("[Credit Card] Successfully processed payment of $%.2f using Card ending in %s\n", 
                amount, cardNumber.substring(12));
        } else {
            System.out.println("[Credit Card] Transaction declined due to invalid details.");
        }
    }
}

// 3. Implementation 2: UPI Payment
class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean validateDetails() {
        // Simple mock validation: check if UPI ID contains '@'
        if (upiId != null && upiId.contains("@")) {
            System.out.println("[UPI] Validation successful for VPA: " + upiId);
            return true;
        }
        System.out.println("[UPI] Validation failed: Invalid UPI ID format.");
        return false;
    }

    @Override
    public void processPayment(double amount) {
        if (validateDetails()) {
            System.out.printf("[UPI] Successfully processed payment of $%.2f via UPI ID: %s\n", amount, upiId);
        } else {
            System.out.println("[UPI] Transaction declined due to invalid UPI handle.");
        }
    }
}

// 4. Implementation 3: Wallet Payment
class WalletPayment implements PaymentMethod {
    private String walletName;
    private double walletBalance;

    public WalletPayment(String walletName, double walletBalance) {
        this.walletName = walletName;
        this.walletBalance = walletBalance;
    }

    @Override
    public boolean validateDetails() {
        System.out.println("[Wallet] Checking wallet balance for " + walletName + "...");
        return walletBalance > 0;
    }

    @Override
    public void processPayment(double amount) {
        if (validateDetails() && walletBalance >= amount) {
            walletBalance -= amount;
            System.out.printf("[Wallet] Successfully paid $%.2f using %s. Remaining Balance: $%.2f\n", 
                amount, walletName, walletBalance);
        } else {
            System.out.println("[Wallet] Transaction failed: Insufficient wallet balance or invalid state.");
        }
    }
}

public class PaymentSystemDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("     PAYMENT SYSTEM USING INTERFACES     ");
        System.out.println("=========================================");

        // Using loosely coupled interface references
        PaymentMethod payment1 = new CreditCardPayment("4111222233334444", "Amit Kumar Singh");
        PaymentMethod payment2 = new UPIPayment("amit.singh@okaxis");
        PaymentMethod payment3 = new WalletPayment("VedaPay Wallet", 1500.00);

        System.out.println("\n--- Processing Transaction 1 ---");
        payment1.processPayment(250.50);

        System.out.println("\n--- Processing Transaction 2 ---");
        payment2.processPayment(99.99);

        System.out.println("\n--- Processing Transaction 3 ---");
        payment3.processPayment(450.00);

        // Demonstrating polymorphic collection handling with interface contract
        System.out.println("\n--- Polymorphic Batch Processing ---");
        PaymentMethod[] payments = {
            new CreditCardPayment("5555444433332222", "Rohan Sharma"),
            new UPIPayment("rohan@ybl")
        };

        for (PaymentMethod pm : payments) {
            pm.processPayment(150.00);
        }

        System.out.println("\n=========================================");
        System.out.println("Payment processing pipeline completed successfully!");
    }
}
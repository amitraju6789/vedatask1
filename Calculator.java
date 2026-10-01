import java.util.Scanner;

public class Calculator {

    // Addition
    public static double add(double a, double b) {
        return a + b;
    }

    // Subtraction
    public static double subtract(double a, double b) {
        return a - b;
    }

    // Multiplication
    public static double multiply(double a, double b) {
        return a * b;
    }

    // Division with zero check
    public static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Cannot divide by zero!");
            return Double.NaN;
        }
        return a / b;
    }

    // Modulus with zero check
    public static double modulus(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Modulus by zero is not allowed!");
            return Double.NaN;
        }
        return a % b;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("=== Console Calculator ===");

        while (keepRunning) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Add (+)");
            System.out.println("2. Subtract (-)");
            System.out.println("3. Multiply (*)");
            System.out.println("4. Divide (/)");
            System.out.println("5. Modulus (%)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number between 1 and 6.");
                sc.next(); // Clear invalid input
                continue;
            }

            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("Exiting... Thank you!");
                keepRunning = false;
                break;
            }

            if (choice < 1 || choice > 6) {
                System.out.println("Invalid option! Please choose a valid menu item.");
                continue;
            }

            // Input first number
            System.out.print("Enter first number: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid number! Try again.");
                sc.next();
                System.out.print("Enter first number: ");
            }
            double num1 = sc.nextDouble();

            // Input second number
            System.out.print("Enter second number: ");
            while (!sc.hasNextDouble()) {
                System.out.println("Invalid number! Try again.");
                sc.next();
                System.out.print("Enter second number: ");
            }
            double num2 = sc.nextDouble();

            double result = 0;
            boolean validOp = true;

            switch (choice) {
                case 1:
                    result = add(num1, num2);
                    break;
                case 2:
                    result = subtract(num1, num2);
                    break;
                case 3:
                    result = multiply(num1, num2);
                    break;
                case 4:
                    result = divide(num1, num2);
                    if (Double.isNaN(result)) validOp = false;
                    break;
                case 5:
                    result = modulus(num1, num2);
                    if (Double.isNaN(result)) validOp = false;
                    break;
            }

            if (validOp) {
                System.out.println("Result: " + result);
            }
        }

        sc.close();
    }
}
package task5;

import java.util.Scanner;

public class MultiplicationTable {

    // Method for single number table generation
    public static void generateSingleTable(int number) {
        System.out.println("\n--- Multiplication Table for " + number + " ---");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%2d x %2d = %3d\n", number, i, (number * i));
        }
    }

    // Method for range-based table generation (uses nested loops)
    public static void generateRangeTables(int start, int end) {
        System.out.println("\n--- Multiplication Tables from " + start + " to " + end + " ---");
        for (int num = start; num <= end; num++) {
            System.out.println("\nTable of " + num + ":");
            for (int i = 1; i <= 10; i++) {
                System.out.printf("%2d x %2d = %3d\n", num, i, (num * i));
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("     MULTIPLICATION TABLE GENERATOR      ");
        System.out.println("=========================================");
        System.out.println("1. Generate table for a single number");
        System.out.println("2. Generate tables for a range of numbers");
        System.out.print("Choose an option (1 or 2): ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a number: ");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        generateSingleTable(num);
                    } else {
                        System.out.println("Invalid input! Please enter an integer.");
                    }
                    break;

                case 2:
                    System.out.print("Enter start number: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter end number: ");
                    int end = scanner.nextInt();

                    if (start <= end) {
                        generateRangeTables(start, end);
                    } else {
                        System.out.println("Start number must be less than or equal to end number.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Please select 1 or 2.");
            }
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
        }

        scanner.close();
    }
}

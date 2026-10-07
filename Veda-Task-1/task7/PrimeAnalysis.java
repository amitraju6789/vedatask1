package task7;

import java.util.Scanner;

public class PrimeAnalysis {

    // Optimized Prime Checker using sqrt(n) loop
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        if (n == 2 || n == 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        // Test divisors up to square root using 6k ± 1 optimization
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    // Generates and prints prime numbers within a specified range
    public static void generatePrimesInRange(int start, int end) {
        System.out.println("\n--- Prime Numbers Between " + start + " and " + end + " ---");
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                count++;
            }
        }

        System.out.println("\nTotal prime numbers found: " + count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("        PRIME NUMBER ANALYSIS            ");
        System.out.println("=========================================");
        System.out.println("1. Check if a number is Prime");
        System.out.println("2. Generate Prime Numbers in a Range");
        System.out.print("Select an option (1 or 2): ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter a number to check: ");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        if (isPrime(num)) {
                            System.out.println("Result: " + num + " IS a prime number.");
                        } else {
                            System.out.println("Result: " + num + " IS NOT a prime number.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter an integer.");
                    }
                    break;

                case 2:
                    System.out.print("Enter start range: ");
                    int start = scanner.nextInt();
                    System.out.print("Enter end range: ");
                    int end = scanner.nextInt();

                    if (start <= end) {
                        generatePrimesInRange(start, end);
                    } else {
                        System.out.println("Start range must be less than or equal to end range.");
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
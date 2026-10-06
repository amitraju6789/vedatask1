package task6;

import java.util.Scanner;

public class PalindromeChecker {

    // Method to check string palindrome (Case-insensitive & ignores non-alphanumeric)
    public static boolean isStringPalindrome(String text) {
        if (text == null) return false;
        
        // Remove spaces and special characters, convert to lowercase
        String cleanText = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        
        // Two-pointer approach
        int left = 0;
        int right = cleanText.length() - 1;

        while (left < right) {
            if (cleanText.charAt(left) != cleanText.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Method to check number palindrome using mathematical operations
    public static boolean isNumberPalindrome(int number) {
        if (number < 0) return false; // Negative numbers are not palindromes

        int originalNumber = number;
        int reversedNumber = 0;

        while (number > 0) {
            int digit = number % 10;
            reversedNumber = reversedNumber * 10 + digit;
            number /= 10;
        }

        return originalNumber == reversedNumber;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=========================================");
        System.out.println("          PALINDROME CHECKER             ");
        System.out.println("=========================================");
        System.out.println("1. Check String Palindrome");
        System.out.println("2. Check Number Palindrome");
        System.out.print("Select option (1 or 2): ");

        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter a string: ");
                    String inputString = scanner.nextLine();
                    if (isStringPalindrome(inputString)) {
                        System.out.println("Result: \"" + inputString + "\" IS a valid palindrome!");
                    } else {
                        System.out.println("Result: \"" + inputString + "\" IS NOT a palindrome.");
                    }
                    break;

                case 2:
                    System.out.print("Enter a number: ");
                    if (scanner.hasNextInt()) {
                        int inputNumber = scanner.nextInt();
                        if (isNumberPalindrome(inputNumber)) {
                            System.out.println("Result: " + inputNumber + " IS a valid palindrome!");
                        } else {
                            System.out.println("Result: " + inputNumber + " IS NOT a palindrome.");
                        }
                    } else {
                        System.out.println("Invalid input! Please enter an integer.");
                    }
                    break;

                default:
                    System.out.println("Invalid choice! Please enter 1 or 2.");
            }
        } else {
            System.out.println("Invalid input! Please enter a valid choice.");
        }

        scanner.close();
    }
}
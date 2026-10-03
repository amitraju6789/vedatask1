import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        // Objects creation
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Target number range (e.g., 1 to 100)
        int min = 1;
        int max = 100;
        int targetNumber = random.nextInt(max - min + 1) + min;

        int userGuess = 0;
        int attempts = 0;

        System.out.println("=========================================");
        System.out.println("   Welcome to Number Guessing Game!    ");
        System.out.println("=========================================");
        System.out.println("I have selected a number between " + min + " and " + max + ".");
        System.out.println("Try to guess it!\n");

        // Loop runs until the correct number is guessed
        while (userGuess != targetNumber) {
            System.out.print("Enter your guess: ");
            
            // Input validation for integers
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                attempts++; // Increment attempt counter

                if (userGuess < targetNumber) {
                    System.out.println("Too low! Try a higher number.\n");
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high! Try a lower number.\n");
                } else {
                    System.out.println("\n🎉 Congratulations! You guessed the correct number!");
                    System.out.println("Total attempts taken: " + attempts);
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.\n");
                scanner.next(); // Clear invalid token
            }
        }

        scanner.close();
    }
}
import java.util.Scanner;

public class GradeCalculator {

    // Method to calculate total marks
    public static double calculateTotal(double[] marks) {
        double total = 0;
        for (double mark : marks) {
            total += mark;
        }
        return total;
    }

    // Method to calculate percentage
    public static double calculatePercentage(double total, int totalSubjects) {
        return total / totalSubjects;
    }

    // Method to determine grade using if-else logic
    public static char calculateGrade(double percentage) {
        if (percentage >= 90) {
            return 'A';
        } else if (percentage >= 80) {
            return 'B';
        } else if (percentage >= 70) {
            return 'C';
        } else if (percentage >= 60) {
            return 'D';
        } else if (percentage >= 40) {
            return 'E';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Student Grade Calculator ===");
        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();

        while (numSubjects <= 0) {
            System.out.print("Please enter a valid number of subjects (greater than 0): ");
            numSubjects = scanner.nextInt();
        }

        double[] marks = new double[numSubjects];

        // Input marks for each subject with validation (0 to 100)
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for Subject " + (i + 1) + " (out of 100): ");
            double mark = scanner.nextDouble();

            while (mark < 0 || mark > 100) {
                System.out.print("Invalid marks! Enter value between 0 and 100: ");
                mark = scanner.nextDouble();
            }
            marks[i] = mark;
        }

        // Perform calculations using methods
        double totalMarks = calculateTotal(marks);
        double percentage = calculatePercentage(totalMarks, numSubjects);
        char grade = calculateGrade(percentage);

        // Display results
        System.out.println("\n--- Performance Summary ---");
        System.out.println("Total Marks Obtained: " + totalMarks + " / " + (numSubjects * 100));
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
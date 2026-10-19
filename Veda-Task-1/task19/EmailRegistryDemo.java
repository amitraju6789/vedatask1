package task19;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EmailRegistryDemo {

    private final Set<String> emailSet = new HashSet<>();

    // Normalize email (remove leading/trailing spaces and convert to lowercase)
    private String normalizeEmail(String email) {
        return email != null ? email.trim().toLowerCase() : "";
    }

    // Basic email format check
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".") && email.indexOf('@') < email.lastIndexOf('.');
    }

    // 1. Register Email with Duplicate Detection
    public boolean registerEmail(String rawEmail) {
        String email = normalizeEmail(rawEmail);
        if (!isValidEmail(email)) {
            System.out.println("[ERROR] Invalid email format! Please enter a valid address (e.g., user@domain.com).");
            return false;
        }

        // HashSet add() returns false if the element is already present
        if (emailSet.add(email)) {
            System.out.println("[SUCCESS] Email registered: " + email);
            return true;
        } else {
            System.out.println("[DUPLICATE DETECTED] Registration failed! '" + email + "' is already registered.");
            return false;
        }
    }

    // 2. Remove Email from Registry
    public boolean removeEmail(String rawEmail) {
        String email = normalizeEmail(rawEmail);
        if (emailSet.remove(email)) {
            System.out.println("[SUCCESS] Email removed from registry: " + email);
            return true;
        } else {
            System.out.println("[ERROR] Email not found in registry: " + email);
            return false;
        }
    }

    // 3. Search Email in Registry
    public boolean searchEmail(String rawEmail) {
        String email = normalizeEmail(rawEmail);
        return emailSet.contains(email);
    }

    // 4. List All Registered Emails
    public void listAllEmails() {
        if (emailSet.isEmpty()) {
            System.out.println("\n[INFO] Email registry is empty.");
            return;
        }
        System.out.println("\n-----------------------------------------");
        System.out.println("       REGISTERED UNIQUE EMAILS          ");
        System.out.println("-----------------------------------------");
        int count = 1;
        for (String email : emailSet) {
            System.out.printf("%2d. %s\n", count++, email);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total Unique Registrations: " + emailSet.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmailRegistryDemo registry = new EmailRegistryDemo();

        // Seed initial mock data
        registry.registerEmail("amit.singh@example.com");
        registry.registerEmail("developer@vedatechnology.in");

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      UNIQUE EMAIL REGISTRY (HashSet)    ");
            System.out.println("=========================================");
            System.out.println("1. Register New Email");
            System.out.println("2. Remove Registered Email");
            System.out.println("3. Search Email ($O(1)$ Lookup)");
            System.out.println("4. List All Registered Emails");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Email Address: ");
                        String addEmail = scanner.nextLine();
                        registry.registerEmail(addEmail);
                        break;

                    case 2:
                        System.out.print("Enter Email Address to Remove: ");
                        String remEmail = scanner.nextLine();
                        registry.removeEmail(remEmail);
                        break;

                    case 3:
                        System.out.print("Enter Email Address to Search: ");
                        String searchEm = scanner.nextLine();
                        if (registry.searchEmail(searchEm)) {
                            System.out.println("[FOUND] '" + searchEm.trim().toLowerCase() + "' is registered.");
                        } else {
                            System.out.println("[NOT FOUND] '" + searchEm.trim().toLowerCase() + "' is NOT registered.");
                        }
                        break;

                    case 4:
                        registry.listAllEmails();
                        break;

                    case 5:
                        System.out.println("\nExiting Email Registry System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please select between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
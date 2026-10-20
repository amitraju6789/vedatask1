package task20;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

// Encapsulated Support Ticket Class
class SupportTicket {
    private static int idCounter = 1000;
    private final String ticketId;
    private final String customerName;
    private final String issueDescription;

    public SupportTicket(String customerName, String issueDescription) {
        this.ticketId = "TICK-" + (++idCounter);
        this.customerName = customerName;
        this.issueDescription = issueDescription;
    }

    public String getTicketId() { return ticketId; }
    public String getCustomerName() { return customerName; }
    public String getIssueDescription() { return issueDescription; }

    public void displayTicket() {
        System.out.printf("| %-10s | %-18s | %-32s |\n", ticketId, customerName, issueDescription);
    }
}

public class CustomerSupportQueue {

    // Queue interface instantiated using ArrayDeque as recommended
    private final Queue<SupportTicket> ticketQueue = new ArrayDeque<>();

    // 1. Add new ticket to Queue (Enqueue)
    public void createTicket(String customerName, String issueDescription) {
        SupportTicket newTicket = new SupportTicket(customerName, issueDescription);
        ticketQueue.offer(newTicket); // Non-blocking add
        System.out.println("[SUCCESS] Ticket created: " + newTicket.getTicketId() + " for " + customerName);
        System.out.println("Position in Queue: " + ticketQueue.size());
    }

    // 2. Process next ticket in FIFO order (Dequeue)
    public void processNextTicket() {
        if (ticketQueue.isEmpty()) {
            System.out.println("\n[INFO] Support queue is empty! No pending tickets to process.");
            return;
        }

        SupportTicket ticket = ticketQueue.poll(); // Retrieves and removes head of queue
        System.out.println("\n=========================================");
        System.out.println("       PROCESSING SUPPORT TICKET         ");
        System.out.println("=========================================");
        System.out.println("Ticket ID    : " + ticket.getTicketId());
        System.out.println("Customer Name: " + ticket.getCustomerName());
        System.out.println("Issue        : " + ticket.getIssueDescription());
        System.out.println("Status       : RESOLVED & CLOSED");
        System.out.println("-----------------------------------------");
        System.out.println("Remaining tickets in queue: " + ticketQueue.size());
    }

    // 3. View next ticket without removing it (Peek)
    public void peekNextTicket() {
        SupportTicket ticket = ticketQueue.peek(); // Inspects head without removal
        if (ticket != null) {
            System.out.println("\n[NEXT IN LINE]");
            System.out.println("Ticket ID    : " + ticket.getTicketId());
            System.out.println("Customer Name: " + ticket.getCustomerName());
            System.out.println("Issue        : " + ticket.getIssueDescription());
        } else {
            System.out.println("\n[INFO] Support queue is empty.");
        }
    }

    // 4. Display all active tickets in queue order
    public void displayQueueStatus() {
        if (ticketQueue.isEmpty()) {
            System.out.println("\n[INFO] Support queue is currently empty.");
            return;
        }

        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("| %-10s | %-18s | %-32s |\n", "Ticket ID", "Customer Name", "Issue Description");
        System.out.println("-------------------------------------------------------------------");
        for (SupportTicket t : ticketQueue) {
            t.displayTicket();
        }
        System.out.println("-------------------------------------------------------------------");
        System.out.println("Total Pending Tickets: " + ticketQueue.size());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerSupportQueue system = new CustomerSupportQueue();

        // Seed initial tickets
        system.createTicket("Amit Kumar Singh", "Login page returning 500 error");
        system.createTicket("Rohan Sharma", "Payment processed but subscription inactive");

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("     CUSTOMER SUPPORT QUEUE (ArrayDeque) ");
            System.out.println("=========================================");
            System.out.println("1. Create New Support Ticket");
            System.out.println("2. Process Next Ticket (FIFO)");
            System.out.println("3. Peek Next Ticket");
            System.out.println("4. Display All Pending Tickets");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Customer Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Issue Description: ");
                        String issue = scanner.nextLine();
                        system.createTicket(name, issue);
                        break;

                    case 2:
                        system.processNextTicket();
                        break;

                    case 3:
                        system.peekNextTicket();
                        break;

                    case 4:
                        system.displayQueueStatus();
                        break;

                    case 5:
                        System.out.println("\nExiting Customer Support System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice! Please select between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
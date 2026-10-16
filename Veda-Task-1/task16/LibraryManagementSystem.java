package task16;

import java.util.*;

// 1. Book Model Class
class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isIssued;
    private String issuedToMemberId;
    private int daysIssued; // Track duration for overdue calculations

    public Book(String bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedToMemberId = null;
        this.daysIssued = 0;
    }

    public String getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }
    public String getIssuedToMemberId() { return issuedToMemberId; }
    public int getDaysIssued() { return daysIssued; }

    public void issueBook(String memberId, int days) {
        this.isIssued = true;
        this.issuedToMemberId = memberId;
        this.daysIssued = days;
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedToMemberId = null;
        this.daysIssued = 0;
    }

    public void displayBook() {
        String status = isIssued ? "Issued to " + issuedToMemberId : "Available";
        System.out.printf("| %-8s | %-25s | %-18s | %-15s |\n", bookId, title, author, status);
    }
}

// 2. Member Model Class
class Member {
    private String memberId;
    private String name;
    private List<String> borrowedBookIds;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBookIds = new ArrayList<>();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public List<String> getBorrowedBookIds() { return borrowedBookIds; }

    public void borrowBook(String bookId) {
        borrowedBookIds.add(bookId);
    }

    public void returnBook(String bookId) {
        borrowedBookIds.remove(bookId);
    }

    public void displayMember() {
        System.out.printf("| %-10s | %-20s | Borrowed Books: %-10d |\n", memberId, name, borrowedBookIds.size());
    }
}

// 3. Main System Manager
public class LibraryManagementSystem {
    private static Map<String, Book> bookMap = new HashMap<>();
    private static Map<String, Member> memberMap = new HashMap<>();
    private static final int MAX_ALLOWED_DAYS = 14; // Overdue limit
    private static final double DAILY_FINE_RATE = 2.0; // $2 per day overdue

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        seedInitialData();

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      LIBRARY MANAGEMENT SYSTEM          ");
            System.out.println("=========================================");
            System.out.println("1. Add New Book");
            System.out.println("2. Add New Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book (With Overdue Check)");
            System.out.println("5. List All Books");
            System.out.println("6. List All Members");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book ID: ");
                        String bId = scanner.nextLine();
                        if (bookMap.containsKey(bId)) {
                            System.out.println("[ERROR] Book ID already exists!");
                            break;
                        }
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author: ");
                        String author = scanner.nextLine();
                        bookMap.put(bId, new Book(bId, title, author));
                        System.out.println("[SUCCESS] Book added successfully!");
                        break;

                    case 2:
                        System.out.print("Enter Member ID: ");
                        String mId = scanner.nextLine();
                        if (memberMap.containsKey(mId)) {
                            System.out.println("[ERROR] Member ID already exists!");
                            break;
                        }
                        System.out.print("Enter Member Name: ");
                        String name = scanner.nextLine();
                        memberMap.put(mId, new Member(mId, name));
                        System.out.println("[SUCCESS] Member registered successfully!");
                        break;

                    case 3:
                        System.out.print("Enter Member ID: ");
                        String issueMId = scanner.nextLine();
                        if (!memberMap.containsKey(issueMId)) {
                            System.out.println("[ERROR] Member not found!");
                            break;
                        }
                        System.out.print("Enter Book ID to Issue: ");
                        String issueBId = scanner.nextLine();
                        Book issueBook = bookMap.get(issueBId);
                        if (issueBook == null) {
                            System.out.println("[ERROR] Book not found!");
                        } else if (issueBook.isIssued()) {
                            System.out.println("[ERROR] Book is already issued to member: " + issueBook.getIssuedToMemberId());
                        } else {
                            System.out.print("Enter expected borrowing duration (in days): ");
                            int days = scanner.nextInt();
                            scanner.nextLine();
                            issueBook.issueBook(issueMId, days);
                            memberMap.get(issueMId).borrowBook(issueBId);
                            System.out.println("[SUCCESS] Book issued successfully to " + memberMap.get(issueMId).getName());
                        }
                        break;

                    case 4:
                        System.out.print("Enter Book ID to Return: ");
                        String returnBId = scanner.nextLine();
                        Book returnBook = bookMap.get(returnBId);

                        if (returnBook == null || !returnBook.isIssued()) {
                            System.out.println("[ERROR] Invalid book ID or book is not currently issued.");
                        } else {
                            String borrowerId = returnBook.getIssuedToMemberId();
                            int daysKept = returnBook.getDaysIssued();

                            // Overdue Fine Calculation
                            double fine = 0.0;
                            if (daysKept > MAX_ALLOWED_DAYS) {
                                int overdueDays = daysKept - MAX_ALLOWED_DAYS;
                                fine = overdueDays * DAILY_FINE_RATE;
                                System.out.printf("\n[OVERDUE WARNING] Book returned %d days late!\n", overdueDays);
                                System.out.printf("Fine Amount Due: $%.2f ($%.2f/day rate)\n", fine, DAILY_FINE_RATE);
                            }

                            returnBook.returnBook();
                            if (memberMap.containsKey(borrowerId)) {
                                memberMap.get(borrowerId).returnBook(returnBId);
                            }
                            System.out.println("[SUCCESS] Book returned successfully.");
                        }
                        break;

                    case 5:
                        System.out.println("\n-------------------------------------------------------------------------");
                        System.out.printf("| %-8s | %-25s | %-18s | %-15s |\n", "Book ID", "Title", "Author", "Status");
                        System.out.println("-------------------------------------------------------------------------");
                        for (Book b : bookMap.values()) {
                            b.displayBook();
                        }
                        System.out.println("-------------------------------------------------------------------------");
                        break;

                    case 6:
                        System.out.println("\n---------------------------------------------------------");
                        System.out.printf("| %-10s | %-20s | %-18s |\n", "Member ID", "Name", "Status");
                        System.out.println("---------------------------------------------------------");
                        for (Member m : memberMap.values()) {
                            m.displayMember();
                        }
                        System.out.println("---------------------------------------------------------");
                        break;

                    case 7:
                        System.out.println("\nExiting Library Management System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }

    private static void seedInitialData() {
        bookMap.put("B101", new Book("B101", "Effective Java", "Joshua Bloch"));
        bookMap.put("B102", new Book("B102", "Clean Code", "Robert C. Martin"));
        memberMap.put("M201", new Member("M201", "Amit Kumar Singh"));
    }
}
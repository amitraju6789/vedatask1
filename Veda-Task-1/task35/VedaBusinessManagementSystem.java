package task35;

import java.util.*;

// Custom Exception for Invalid Input or Missing Records
class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}

// Abstract Base Class demonstrating Inheritance
abstract class Offering {
    private String id;
    private String title;
    private double fee;

    public Offering(String id, String title, double fee) {
        this.id = id;
        this.title = title;
        this.fee = fee;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public double getFee() { return fee; }

    public abstract void displayDetails();
}

// Service Category extending Offering
class ServiceCategory extends Offering {
    private String categoryType;

    public ServiceCategory(String id, String title, double fee, String categoryType) {
        super(id, title, fee);
        this.categoryType = categoryType;
    }

    public String getCategoryType() { return categoryType; }

    @Override
    public void displayDetails() {
        System.out.printf("[SERVICE] ID: %-6s | Title: %-22s | Fee: $%-8.2f | Type: %-15s\n",
                getId(), getTitle(), getFee(), categoryType);
    }
}

// Training Program extending Offering
class TrainingProgram extends Offering {
    private int durationWeeks;

    public TrainingProgram(String id, String title, double fee, int durationWeeks) {
        super(id, title, fee);
        this.durationWeeks = durationWeeks;
    }

    public int getDurationWeeks() { return durationWeeks; }

    @Override
    public void displayDetails() {
        System.out.printf("[TRAINING] ID: %-6s | Title: %-22s | Fee: $%-8.2f | Duration: %d Weeks\n",
                getId(), getTitle(), getFee(), durationWeeks);
    }
}

// Customer Entity
class Customer {
    private String customerId;
    private String name;
    private String email;

    public Customer(String customerId, String name, String email) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
    }

    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void displayCustomer() {
        System.out.printf("Customer ID: %-6s | Name: %-18s | Email: %s\n", customerId, name, email);
    }
}

// Service Request Entity
class ServiceRequest {
    private String requestId;
    private Customer customer;
    private Offering offering;
    private String status; // Pending, In-Progress, Completed

    public ServiceRequest(String requestId, Customer customer, Offering offering, String status) {
        this.requestId = requestId;
        this.customer = customer;
        this.offering = offering;
        this.status = status;
    }

    public String getRequestId() { return requestId; }
    public Customer getCustomer() { return customer; }
    public Offering getOffering() { return offering; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public void displayRequest() {
        System.out.printf("Req ID: %-6s | Customer: %-15s | Offering: %-20s | Status: %s\n",
                requestId, customer.getName(), offering.getTitle(), status);
    }
}

// Main Business Management System
public class VedaBusinessManagementSystem {
    private static final List<Offering> catalog = new ArrayList<>();
    private static final Map<String, Customer> customers = new HashMap<>();
    private static final List<ServiceRequest> requests = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedSyntheticData();

        boolean running = true;
        while (running) {
            System.out.println("\n=========================================================");
            System.out.println("    VEDA TECHNOLOGY BUSINESS & SERVICE MANAGEMENT SYSTEM ");
            System.out.println("=========================================================");
            System.out.println("1. View Service & Training Catalog");
            System.out.println("2. View Registered Customers");
            System.out.println("3. Create New Service Request / Inquiry");
            System.out.println("4. Search Offerings by Category or Keyword");
            System.out.println("5. Filter Service Requests by Status");
            System.out.println("6. Display Executive Business Summary Report");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choice) {
                        case 1 -> displayCatalog();
                        case 2 -> displayCustomers();
                        case 3 -> createServiceRequest();
                        case 4 -> searchCatalog();
                        case 5 -> filterRequestsByStatus();
                        case 6 -> displayExecutiveReport();
                        case 7 -> {
                            System.out.println("\nExiting Veda Business System. Goodbye!");
                            running = false;
                        }
                        default -> System.out.println("[ERROR] Invalid choice. Please select between 1 and 7.");
                    }
                } catch (BusinessException e) {
                    System.out.println("[BUSINESS ERROR] " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("[SYSTEM ERROR] Unexpected exception: " + e.getMessage());
                }
            } else {
                System.out.println("[ERROR] Please enter a valid number.");
                scanner.next();
            }
        }
        scanner.close();
    }

    private static void seedSyntheticData() {
        // Offerings
        catalog.add(new ServiceCategory("SRV01", "Cloud Migration", 4500.00, "Infrastructure"));
        catalog.add(new ServiceCategory("SRV02", "Full-Stack Web Dev", 3000.00, "Software"));
        catalog.add(new TrainingProgram("TRN01", "Java Enterprise Track", 1200.00, 8));
        catalog.add(new TrainingProgram("TRN02", "DevOps & Docker Bootcamp", 950.00, 6));

        // Customers
        Customer c1 = new Customer("CUST101", "Amit Kumar Singh", "amit@vedatech.synth");
        Customer c2 = new Customer("CUST102", "Rohan Sharma", "rohan@enterprise.synth");
        customers.put(c1.getCustomerId(), c1);
        customers.put(c2.getCustomerId(), c2);

        // Service Requests
        requests.add(new ServiceRequest("REQ001", c1, catalog.get(0), "In-Progress"));
        requests.add(new ServiceRequest("REQ002", c2, catalog.get(2), "Completed"));
    }

    private static void displayCatalog() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("             SERVICE & TRAINING CATALOG                  ");
        System.out.println("---------------------------------------------------------");
        for (Offering o : catalog) {
            o.displayDetails();
        }
        System.out.println("---------------------------------------------------------");
    }

    private static void displayCustomers() {
        System.out.println("\n---------------------------------------------------------");
        System.out.println("               REGISTERED CUSTOMERS                      ");
        System.out.println("---------------------------------------------------------");
        for (Customer c : customers.values()) {
            c.displayCustomer();
        }
        System.out.println("---------------------------------------------------------");
    }

    private static void createServiceRequest() throws BusinessException {
        System.out.print("Enter Customer ID (or type NEW to register): ");
        String custId = scanner.nextLine().trim();

        Customer customer;
        if (custId.equalsIgnoreCase("NEW")) {
            System.out.print("Enter New Customer ID: ");
            String newId = scanner.nextLine().trim();
            System.out.print("Enter Customer Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Customer Email: ");
            String email = scanner.nextLine().trim();

            customer = new Customer(newId, name, email);
            customers.put(newId, customer);
            System.out.println("[SUCCESS] New customer registered.");
        } else {
            customer = customers.get(custId);
            if (customer == null) {
                throw new BusinessException("Customer ID '" + custId + "' not found in database.");
            }
        }

        displayCatalog();
        System.out.print("Enter Offering ID to Request (e.g. SRV01, TRN01): ");
        String offId = scanner.nextLine().trim();

        Offering selectedOffering = null;
        for (Offering o : catalog) {
            if (o.getId().equalsIgnoreCase(offId)) {
                selectedOffering = o;
                break;
            }
        }

        if (selectedOffering == null) {
            throw new BusinessException("Offering ID '" + offId + "' does not exist.");
        }

        String reqId = "REQ00" + (requests.size() + 1);
        ServiceRequest newReq = new ServiceRequest(reqId, customer, selectedOffering, "Pending");
        requests.add(newReq);
        System.out.println("[SUCCESS] Service request " + reqId + " created successfully with status 'Pending'.");
    }

    private static void searchCatalog() throws BusinessException {
        System.out.print("Enter search keyword or category (e.g. Infrastructure, Java): ");
        String query = scanner.nextLine().trim().toLowerCase();

        System.out.println("\n--- SEARCH RESULTS ---");
        boolean found = false;
        for (Offering o : catalog) {
            boolean matches = o.getTitle().toLowerCase().contains(query) || o.getId().toLowerCase().contains(query);
            if (o instanceof ServiceCategory sc) {
                matches = matches || sc.getCategoryType().toLowerCase().contains(query);
            }

            if (matches) {
                o.displayDetails();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No catalog offerings matched query: " + query);
        }
    }

    private static void filterRequestsByStatus() throws BusinessException {
        System.out.print("Enter status to filter by (Pending, In-Progress, Completed): ");
        String statusFilter = scanner.nextLine().trim();

        System.out.println("\n--- REQUESTS FILTERED BY STATUS: " + statusFilter + " ---");
        boolean found = false;
        for (ServiceRequest r : requests) {
            if (r.getStatus().equalsIgnoreCase(statusFilter)) {
                r.displayRequest();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No service requests found with status: " + statusFilter);
        }
    }

    private static void displayExecutiveReport() {
        long pendingCount = requests.stream().filter(r -> r.getStatus().equalsIgnoreCase("Pending")).count();
        long inProgCount = requests.stream().filter(r -> r.getStatus().equalsIgnoreCase("In-Progress")).count();
        long completedCount = requests.stream().filter(r -> r.getStatus().equalsIgnoreCase("Completed")).count();

        System.out.println("\n=========================================================");
        System.out.println("           EXECUTIVE BUSINESS SUMMARY REPORT             ");
        System.out.println("=========================================================");
        System.out.println("Total Catalog Offerings : " + catalog.size());
        System.out.println("Registered Customers    : " + customers.size());
        System.out.println("Total Service Requests  : " + requests.size());
        System.out.println("  - Pending Requests    : " + pendingCount);
        System.out.println("  - In-Progress Requests: " + inProgCount);
        System.out.println("  - Completed Requests  : " + completedCount);
        System.out.println("=========================================================");
    }
}
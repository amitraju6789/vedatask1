package task11;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Encapsulated Employee Model Class
class Employee {
    private final int id; // Unique Employee ID
    private String name;
    private String department;
    private double salary;

    // Constructor
    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Display formatted employee record
    public void displayEmployee() {
        System.out.printf("| %-8d | %-20s | %-18s | $%-10.2f |\n", id, name, department, salary);
    }
}

// System Manager Class (Business Logic)
class EmployeeManager {
    private final List<Employee> employeeList = new ArrayList<>();

    // Check if ID already exists
    public boolean exists(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) return true;
        }
        return false;
    }

    // 1. Create / Add Employee
    public boolean addEmployee(int id, String name, String department, double salary) {
        if (exists(id)) {
            return false; // Unique ID constraint
        }
        employeeList.add(new Employee(id, name, department, salary));
        return true;
    }

    // 2. Search Employee by ID
    public Employee searchEmployee(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                return emp;
            }
        }
        return null;
    }

    // 3. Update Employee Details
    public boolean updateEmployee(int id, String newDepartment, double newSalary) {
        Employee emp = searchEmployee(id);
        if (emp != null) {
            emp.setDepartment(newDepartment);
            emp.setSalary(newSalary);
            return true;
        }
        return false;
    }

    // 4. List All Employees
    public void listAllEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("\n[INFO] No employee records found.");
            return;
        }
        System.out.println("\n------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-18s | %-11s |\n", "ID", "Name", "Department", "Salary");
        System.out.println("------------------------------------------------------------------");
        for (Employee emp : employeeList) {
            emp.displayEmployee();
        }
        System.out.println("------------------------------------------------------------------");
    }
}

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();

        // Adding initial mock data
        manager.addEmployee(101, "Amit Kumar Singh", "Software Engineering", 75000);
        manager.addEmployee(102, "Rohan Sharma", "Product Development", 68000);

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      EMPLOYEE MANAGEMENT SYSTEM         ");
            System.out.println("=========================================");
            System.out.println("1. Add New Employee");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. Update Employee Details");
            System.out.println("4. List All Employees");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Unique Employee ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        if (manager.exists(id)) {
                            System.out.println("[ERROR] Employee with ID " + id + " already exists!");
                            break;
                        }

                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();

                        System.out.print("Enter Salary: ");
                        double salary = scanner.nextDouble();

                        if (manager.addEmployee(id, name, department, salary)) {
                            System.out.println("[SUCCESS] Employee added successfully!");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Employee ID to Search: ");
                        int searchId = scanner.nextInt();
                        Employee found = manager.searchEmployee(searchId);

                        if (found != null) {
                            System.out.println("\n[RECORD FOUND]");
                            System.out.println("------------------------------------------------------------------");
                            System.out.printf("| %-8s | %-20s | %-18s | %-11s |\n", "ID", "Name", "Department", "Salary");
                            System.out.println("------------------------------------------------------------------");
                            found.displayEmployee();
                            System.out.println("------------------------------------------------------------------");
                        } else {
                            System.out.println("[NOT FOUND] No employee found with ID: " + searchId);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to Update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        if (!manager.exists(updateId)) {
                            System.out.println("[ERROR] Employee ID not found.");
                            break;
                        }

                        System.out.print("Enter New Department: ");
                        String newDept = scanner.nextLine();

                        System.out.print("Enter New Salary: ");
                        double newSal = scanner.nextDouble();

                        if (manager.updateEmployee(updateId, newDept, newSal)) {
                            System.out.println("[SUCCESS] Employee details updated successfully!");
                        }
                        break;

                    case 4:
                        manager.listAllEmployees();
                        break;

                    case 5:
                        System.out.println("\nExiting Employee Management System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please enter a number between 1 and 5.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
package task18;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Encapsulated Employee Model Class
class Employee {
    private String empId;
    private String name;
    private String department;
    private double salary;

    public Employee(String empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void setDepartment(String department) { this.department = department; }
    public void setSalary(double salary) { this.salary = salary; }

    public void displayEmployee() {
        System.out.printf("| %-10s | %-20s | %-18s | $%-10.2f |\n", empId, name, department, salary);
    }
}

public class HashMapEmployeeLookup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Employee HashMap: Key = Employee ID (String), Value = Employee Object
        Map<String, Employee> employeeMap = new HashMap<>();

        // Seed initial records
        employeeMap.put("E101", new Employee("E101", "Amit Kumar Singh", "Software Engineering", 85000.00));
        employeeMap.put("E102", new Employee("E102", "Rohan Sharma", "Product Development", 72000.00));

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      HASHMAP EMPLOYEE LOOKUP SYSTEM     ");
            System.out.println("=========================================");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee by ID ($O(1)$)");
            System.out.println("3. Update Employee Details");
            System.out.println("4. Delete Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Select an option (1-6): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Unique Employee ID: ");
                        String id = scanner.nextLine().trim();

                        if (employeeMap.containsKey(id)) {
                            System.out.println("[ERROR] Employee ID already exists! Keys must be unique.");
                            break;
                        }

                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        System.out.print("Enter Salary: ");
                        if (scanner.hasNextDouble()) {
                            double sal = scanner.nextDouble();
                            scanner.nextLine();
                            employeeMap.put(id, new Employee(id, name, dept, sal));
                            System.out.println("[SUCCESS] Employee added successfully to HashMap.");
                        } else {
                            System.out.println("[ERROR] Invalid salary amount.");
                            scanner.next();
                        }
                        break;

                    case 2:
                        System.out.print("Enter Employee ID to Search: ");
                        String searchId = scanner.nextLine().trim();

                        Employee emp = employeeMap.get(searchId); // O(1) lookup
                        if (emp != null) {
                            System.out.println("\n[RECORD FOUND via O(1) Key Lookup]");
                            System.out.println("------------------------------------------------------------------");
                            System.out.printf("| %-10s | %-20s | %-18s | %-11s |\n", "Emp ID", "Name", "Department", "Salary");
                            System.out.println("------------------------------------------------------------------");
                            emp.displayEmployee();
                            System.out.println("------------------------------------------------------------------");
                        } else {
                            System.out.println("[NOT FOUND] No employee record associated with ID: " + searchId);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to Update: ");
                        String updateId = scanner.nextLine().trim();

                        if (!employeeMap.containsKey(updateId)) {
                            System.out.println("[ERROR] Employee ID not found in database.");
                            break;
                        }

                        System.out.print("Enter New Department: ");
                        String newDept = scanner.nextLine();
                        System.out.print("Enter New Salary: ");
                        if (scanner.hasNextDouble()) {
                            double newSal = scanner.nextDouble();
                            scanner.nextLine();

                            Employee target = employeeMap.get(updateId);
                            target.setDepartment(newDept);
                            target.setSalary(newSal);
                            System.out.println("[SUCCESS] Employee details updated successfully.");
                        } else {
                            System.out.println("[ERROR] Invalid salary amount.");
                            scanner.next();
                        }
                        break;

                    case 4:
                        System.out.print("Enter Employee ID to Delete: ");
                        String deleteId = scanner.nextLine().trim();

                        if (employeeMap.remove(deleteId) != null) {
                            System.out.println("[SUCCESS] Employee record removed from HashMap.");
                        } else {
                            System.out.println("[ERROR] Employee ID not found.");
                        }
                        break;

                    case 5:
                        if (employeeMap.isEmpty()) {
                            System.out.println("\n[INFO] Employee database is currently empty.");
                            break;
                        }
                        System.out.println("\n------------------------------------------------------------------");
                        System.out.printf("| %-10s | %-20s | %-18s | %-11s |\n", "Emp ID", "Name", "Department", "Salary");
                        System.out.println("------------------------------------------------------------------");
                        for (Map.Entry<String, Employee> entry : employeeMap.entrySet()) {
                            entry.getValue().displayEmployee();
                        }
                        System.out.println("------------------------------------------------------------------");
                        break;

                    case 6:
                        System.out.println("\nExiting HashMap Employee Lookup System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please select between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
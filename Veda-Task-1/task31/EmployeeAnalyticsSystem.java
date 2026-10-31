package task31;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private final String id;
    private final String name;
    private final String department;
    private final double salary;
    private final int age;

    public Employee(String id, String name, String department, double salary, int age) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }

    public void display() {
        System.out.printf("| %-6s | %-18s | %-18s | $%-10.2f | %-3d |\n", id, name, department, salary, age);
    }
}

public class EmployeeAnalyticsSystem {

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("        EMPLOYEE ANALYTICS SYSTEM USING STREAMS          ");
        System.out.println("=========================================================");

        List<Employee> employees = Arrays.asList(
            new Employee("E101", "Amit Kumar Singh", "Engineering", 95000.00, 24),
            new Employee("E102", "Rohan Sharma", "Product", 72000.00, 28),
            new Employee("E103", "Priya Patel", "Engineering", 115000.00, 29),
            new Employee("E104", "Vikram Verma", "DevOps", 88000.00, 32),
            new Employee("E105", "Neha Gupta", "Product", 65000.00, 26),
            new Employee("E106", "Ananya Roy", "Engineering", 125000.00, 31),
            new Employee("E107", "Suresh Kumar", "DevOps", 78000.00, 35),
            new Employee("E108", "Karan Malhotra", "Product", 91000.00, 30)
        );

        // ==========================================
        // 1. SALARY ANALYSIS & EXTREMES (max/min)
        // ==========================================
        System.out.println("\n[1] SALARY ANALYSIS & EXTREMES");
        System.out.println("---------------------------------------------------------");
        
        Employee highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        Employee lowestPaid = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElse(null);

        if (highestPaid != null) {
            System.out.printf("Highest Paid Employee : %s ($%.2f - %s)\n", highestPaid.getName(), highestPaid.getSalary(), highestPaid.getDepartment());
        }
        if (lowestPaid != null) {
            System.out.printf("Lowest Paid Employee  : %s ($%.2f - %s)\n", lowestPaid.getName(), lowestPaid.getSalary(), lowestPaid.getDepartment());
        }

        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.printf("Average Payroll Salary: $%.2f\n", salaryStats.getAverage());
        System.out.printf("Total Payroll Expense : $%.2f\n", salaryStats.getSum());
        System.out.println("---------------------------------------------------------");


        // ==========================================
        // 2. DEPARTMENT-WISE STATISTICS (groupingBy & averagingDouble)
        // ==========================================
        System.out.println("\n[2] DEPARTMENT-WISE STATISTICS & HEADCOUNTS");
        System.out.println("---------------------------------------------------------");
        
        Map<String, Long> deptHeadcounts = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));

        Map<String, Double> deptAvgSalaries = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));

        deptHeadcounts.forEach((dept, count) -> {
            double avgSal = deptAvgSalaries.get(dept);
            System.out.printf("Department: %-15s | Headcount: %d | Avg Salary: $%.2f\n", dept, count, avgSal);
        });
        System.out.println("---------------------------------------------------------");


        // ==========================================
        // 3. EMPLOYEE RANKING (Sorted by Salary Descending)
        // ==========================================
        System.out.println("\n[3] EMPLOYEE RANKING (Sorted by Salary Descending)");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-18s | %-11s | %-3s |\n", "ID", "Name", "Department", "Salary", "Age");
        System.out.println("-------------------------------------------------------------------------");
        
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(Employee::display);
        
        System.out.println("-------------------------------------------------------------------------");


        // ==========================================
        // 4. EXECUTIVE SUMMARY REPORT
        // ==========================================
        System.out.println("\n[4] EXECUTIVE ANALYTICS SUMMARY REPORT");
        System.out.println("=========================================================");
        System.out.println(" Total Active Workforce : " + employees.size() + " Employees");
        System.out.println(" Tracked Departments    : " + deptHeadcounts.keySet());
        System.out.println(" Top Earner             : " + (highestPaid != null ? highestPaid.getName() + " ($" + highestPaid.getSalary() + ")" : "N/A"));
        System.out.println("=========================================================");
    }
}
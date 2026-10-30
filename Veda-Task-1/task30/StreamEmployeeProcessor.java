package task30;

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
        System.out.printf("| %-6s | %-18s | %-20s | $%-10.2f | %-3d |\n", id, name, department, salary, age);
    }
}

public class StreamEmployeeProcessor {

    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("    FUNCTIONAL DATA PROCESSING USING JAVA STREAMS       ");
        System.out.println("=========================================================");

        List<Employee> employees = Arrays.asList(
            new Employee("E101", "Amit Kumar Singh", "Engineering", 95000.00, 24),
            new Employee("E102", "Rohan Sharma", "Product", 72000.00, 28),
            new Employee("E103", "Priya Patel", "Engineering", 11000.00, 29),
            new Employee("E104", "Vikram Verma", "DevOps", 88000.00, 32),
            new Employee("E105", "Neha Gupta", "Product", 65000.00, 26),
            new Employee("E106", "Ananya Roy", "Engineering", 125000.00, 31),
            new Employee("E107", "Suresh Kumar", "DevOps", 78000.00, 35)
        );

        // 1. FILTERING: High Earners (Salary > $80,000)
        System.out.println("\n[1] FILTER: Employees Earning > $80,000");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-20s | %-11s | %-3s |\n", "ID", "Name", "Department", "Salary", "Age");
        System.out.println("-------------------------------------------------------------------------");
        employees.stream()
                .filter(e -> e.getSalary() > 80000.00)
                .forEach(Employee::display);
        System.out.println("-------------------------------------------------------------------------");

        // 2. SORTING: Employees Sorted by Salary Descending
        System.out.println("\n[2] SORTED: Employees Ordered by Salary (Descending)");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-6s | %-18s | %-20s | %-11s | %-3s |\n", "ID", "Name", "Department", "Salary", "Age");
        System.out.println("-------------------------------------------------------------------------");
        employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .forEach(Employee::display);
        System.out.println("-------------------------------------------------------------------------");

        // 3. TRANSFORMING (MAP): Extract Uppercase Employee Names
        List<String> upperCaseNames = employees.stream()
                .map(e -> e.getName().toUpperCase())
                .collect(Collectors.toList());
        System.out.println("\n[3] MAP: Transformed UpperCase Names List -> " + upperCaseNames);

        // 4. GROUPING: Group Employees by Department
        System.out.println("\n[4] GROUPING BY: Employees Partitioned by Department");
        Map<String, List<Employee>> departmentGroupMap = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));

        departmentGroupMap.forEach((dept, empList) -> {
            System.out.println("\n>>> Department: " + dept + " (" + empList.size() + " Members)");
            empList.forEach(e -> System.out.printf("   - %s (%s) | $%.2f\n", e.getName(), e.getId(), e.getSalary()));
        });

        // 5. AGGREGATION: Summary Statistics (Total, Avg, Min, Max Salary)
        System.out.println("\n[5] AGGREGATION: Overall Payroll Statistics");
        DoubleSummaryStatistics salaryStats = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));

        System.out.println("---------------------------------------------------------");
        System.out.println(" Total Employee Count : " + salaryStats.getCount());
        System.out.printf(" Total Payroll Expense : $%.2f\n", salaryStats.getSum());
        System.out.printf(" Average Salary        : $%.2f\n", salaryStats.getAverage());
        System.out.printf(" Minimum Salary        : $%.2f\n", salaryStats.getMin());
        System.out.printf(" Maximum Salary        : $%.2f\n", salaryStats.getMax());
        System.out.println("---------------------------------------------------------");
    }
}
package task12;

// Base Employee Class
class Employee {
    protected String name;
    protected int id;
    protected double baseSalary;

    // Constructor for Base Class
    public Employee(String name, int id, double baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    // Common method for all employees
    public void displayDetails() {
        System.out.println("ID         : " + id);
        System.out.println("Name       : " + name);
        System.out.printf("Base Salary: $%.2f\n", baseSalary);
    }

    public void work() {
        System.out.println(name + " is performing general corporate duties.");
    }
}

// Child Class 1: Developer
class Developer extends Employee {
    private String programmingLanguage;

    public Developer(String name, int id, double baseSalary, String programmingLanguage) {
        super(name, id, baseSalary); // Inheriting parent constructor
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    public void work() {
        System.out.println(name + " is writing clean code and building backend features in " + programmingLanguage + ".");
    }

    public void debugCode() {
        System.out.println(name + " is debugging production code and resolving merge conflicts.");
    }
}

// Child Class 2: Manager
class Manager extends Employee {
    private int teamSize;

    public Manager(String name, int id, double baseSalary, int teamSize) {
        super(name, id, baseSalary);
        this.teamSize = teamSize;
    }

    @Override
    public void work() {
        System.out.println(name + " is leading sprint planning and managing a team of " + teamSize + " engineers.");
    }

    public void conductMeeting() {
        System.out.println(name + " is hosting the weekly project status synchronization meeting.");
    }
}

// Child Class 3: Intern
class Intern extends Employee {
    private int durationMonths;

    public Intern(String name, int id, double baseSalary, int durationMonths) {
        super(name, id, baseSalary);
        this.durationMonths = durationMonths;
    }

    @Override
    public void work() {
        System.out.println(name + " is learning core programming concepts during their " + durationMonths + "-month internship track.");
    }

    public void submitAssignment() {
        System.out.println(name + " is submitting their weekly coding assignments for review.");
    }
}

public class EmployeeHierarchyDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    EMPLOYEE INHERITANCE HIERARCHY       ");
        System.out.println("=========================================");

        // 1. Developer Object
        System.out.println("\n--- [1] Developer Profile ---");
        Developer dev = new Developer("Amit Kumar Singh", 101, 75000.0, "Java");
        dev.displayDetails();
        dev.work();
        dev.debugCode();

        // 2. Manager Object
        System.out.println("\n--- [2] Manager Profile ---");
        Manager mgr = new Manager("Rohan Sharma", 102, 110000.0, 8);
        mgr.displayDetails();
        mgr.work();
        mgr.conductMeeting();

        // 3. Intern Object
        System.out.println("\n--- [3] Intern Profile ---");
        Intern intern = new Intern("Aman Verma", 103, 25000.0, 6);
        intern.displayDetails();
        intern.work();
        intern.submitAssignment();

        System.out.println("\n=========================================");
        System.out.println("Hierarchy execution completed successfully!");
    }
}
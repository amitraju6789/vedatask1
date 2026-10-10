package task10;

// Student model class demonstrating Constructor Overloading
class Student {
    private String name;
    private int id;
    private String course;
    private double gpa;

    // 1. Default / No-Argument Constructor
    public Student() {
        this("Unknown", 0, "Unassigned", 0.0); // Constructor Chaining using this()
    }

    // 2. Constructor with Name and ID (Partial Parameters)
    public Student(String name, int id) {
        this(name, id, "General Computer Science", 0.0); // Chaining to full constructor
    }

    // 3. Fully Parameterized Constructor
    public Student(String name, int id, String course, double gpa) {
        this.name = name;
        this.id = id;
        this.course = course;
        this.gpa = gpa;
    }

    // Method to display student details
    public void displayStudentInfo() {
        System.out.println("-----------------------------------------");
        System.out.println("Student ID   : " + id);
        System.out.println("Student Name : " + name);
        System.out.println("Course       : " + course);
        System.out.println("GPA          : " + gpa);
    }
}

public class ConstructorOverloadingDemo {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    CONSTRUCTOR OVERLOADING DEMO         ");
        System.out.println("=========================================");

        // Object 1: Created using Default Constructor
        System.out.println("\n[1] Object initialized with Default Constructor:");
        Student student1 = new Student();
        student1.displayStudentInfo();

        // Object 2: Created using Partial Parameterized Constructor
        System.out.println("\n[2] Object initialized with Partial Constructor:");
        Student student2 = new Student("Amit Kumar Singh", 101);
        student2.displayStudentInfo();

        // Object 3: Created using Fully Parameterized Constructor
        System.out.println("\n[3] Object initialized with Full Constructor:");
        Student student3 = new Student("Amit Kumar Singh", 102, "Java Backend Track", 3.95);
        student3.displayStudentInfo();

        System.out.println("\n=========================================");
        System.out.println("All objects instantiated successfully!");
    }
}
package task33;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Subject Class to encapsulate course marks
class Subject {
    private String subjectName;
    private double marks;

    public Subject(String subjectName, double marks) {
        this.subjectName = subjectName;
        this.marks = validateMarks(marks);
    }

    private double validateMarks(double marks) {
        if (marks < 0 || marks > 100) {
            System.out.println("[WARNING] Invalid marks entered (" + marks + "). Clamped to range [0, 100].");
            return Math.max(0, Math.min(100, marks));
        }
        return marks;
    }

    public String getSubjectName() { return subjectName; }
    public double getMarks() { return marks; }
    public void setMarks(double marks) { this.marks = validateMarks(marks); }
}

// Grade Calculator Utility
class GradeCalculator {
    public static String calculateGrade(double percentage) {
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    public static String getPerformanceRemarks(String grade) {
        return switch (grade) {
            case "A+" -> "Outstanding Achievement";
            case "A"  -> "Excellent Performance";
            case "B"  -> "Good Standing";
            case "C"  -> "Satisfactory";
            case "D"  -> "Needs Improvement";
            default   -> "Academic Warning (Fail)";
        };
    }
}

// Encapsulated Student Model
class Student {
    private String studentId;
    private String name;
    private List<Subject> subjects;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.subjects = new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Subject> getSubjects() { return subjects; }

    public void addSubject(String subjectName, double marks) {
        subjects.add(new Subject(subjectName, marks));
    }

    public double calculateTotalMarks() {
        return subjects.stream().mapToDouble(Subject::getMarks).sum();
    }

    public double calculatePercentage() {
        if (subjects.isEmpty()) return 0.0;
        return calculateTotalMarks() / subjects.size();
    }

    public String getGrade() {
        return GradeCalculator.calculateGrade(calculatePercentage());
    }

    public void displaySummary() {
        double percentage = calculatePercentage();
        String grade = getGrade();
        System.out.printf("| %-8s | %-20s | %-8.2f | %-10.2f%% | %-5s | %-22s |\n",
                studentId, name, calculateTotalMarks(), percentage, grade, GradeCalculator.getPerformanceRemarks(grade));
    }

    public void displayDetailedReport() {
        System.out.println("\n========================================================");
        System.out.println("            ACADEMIC PERFORMANCE REPORT CARD            ");
        System.out.println("========================================================");
        System.out.println("Student ID   : " + studentId);
        System.out.println("Student Name : " + name);
        System.out.println("--------------------------------------------------------");
        System.out.printf("| %-25s | %-12s |\n", "Subject Name", "Marks (100)");
        System.out.println("--------------------------------------------------------");
        for (Subject sub : subjects) {
            System.out.printf("| %-25s | %-12.2f |\n", sub.getSubjectName(), sub.getMarks());
        }
        System.out.println("--------------------------------------------------------");
        System.out.printf("Total Marks   : %.2f / %.2f\n", calculateTotalMarks(), subjects.size() * 100.0);
        System.out.printf("Percentage    : %.2f%%\n", calculatePercentage());
        System.out.printf("Overall Grade : %s (%s)\n", getGrade(), GradeCalculator.getPerformanceRemarks(getGrade()));
        System.out.println("========================================================\n");
    }
}

// Main Menu-Driven CRUD & Analytics Application
public class StudentGradeManagementSystem {
    private static final List<Student> studentDirectory = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        seedInitialData();

        boolean running = true;
        while (running) {
            System.out.println("\n=================================================");
            System.out.println("  STUDENT GRADE MANAGEMENT SYSTEM (FINAL POLISH) ");
            System.out.println("=================================================");
            System.out.println("1. Add New Student Record");
            System.out.println("2. Display All Student Summaries");
            System.out.println("3. Search & View Detailed Report Card");
            System.out.println("4. Update Student Details / Marks");
            System.out.println("5. Delete Student Record");
            System.out.println("6. Display Class Performance Analytics");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> displayAllSummaries();
                    case 3 -> searchStudent();
                    case 4 -> updateStudent();
                    case 5 -> deleteStudent();
                    case 6 -> displayClassAnalytics();
                    case 7 -> {
                        System.out.println("\nExiting Grade Management System. All records preserved.");
                        running = false;
                    }
                    default -> System.out.println("[ERROR] Invalid choice. Select between 1 and 7.");
                }
            } else {
                System.out.println("[ERROR] Please enter a valid number.");
                scanner.next();
            }
        }
        scanner.close();
    }

    private static void seedInitialData() {
        Student s1 = new Student("STU101", "Amit Kumar Singh");
        s1.addSubject("Mathematics", 92.5);
        s1.addSubject("Computer Science", 95.0);
        s1.addSubject("Physics", 88.0);
        studentDirectory.add(s1);

        Student s2 = new Student("STU102", "Rohan Sharma");
        s2.addSubject("Mathematics", 78.0);
        s2.addSubject("Computer Science", 82.5);
        s2.addSubject("Physics", 74.0);
        studentDirectory.add(s2);

        Student s3 = new Student("STU103", "Priya Patel");
        s3.addSubject("Mathematics", 91.0);
        s3.addSubject("Computer Science", 89.5);
        s3.addSubject("Physics", 93.0);
        studentDirectory.add(s3);
    }

    private static void addStudent() {
        System.out.print("Enter Unique Student ID: ");
        String id = scanner.nextLine().trim();

        if (findStudentById(id) != null) {
            System.out.println("[ERROR] Student ID already exists!");
            return;
        }

        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();

        Student student = new Student(id, name);
        System.out.print("Enter Number of Subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= numSubjects; i++) {
            System.out.print("Subject " + i + " Name: ");
            String subName = scanner.nextLine().trim();
            System.out.print("Marks obtained (0-100): ");
            double marks = scanner.nextDouble();
            scanner.nextLine();
            student.addSubject(subName, marks);
        }

        studentDirectory.add(student);
        System.out.println("[SUCCESS] Student record created successfully!");
    }

    private static void displayAllSummaries() {
        if (studentDirectory.isEmpty()) {
            System.out.println("\n[INFO] Directory is empty.");
            return;
        }
        System.out.println("\n-----------------------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-8s | %-11s | %-5s | %-22s |\n",
                "ID", "Name", "Total", "Percentage", "Grade", "Remarks");
        System.out.println("-----------------------------------------------------------------------------------------");
        
        // Sort students by percentage descending for ranking overview
        studentDirectory.stream()
                .sorted(Comparator.comparingDouble(Student::calculatePercentage).reversed())
                .forEach(Student::displaySummary);

        System.out.println("-----------------------------------------------------------------------------------------");
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to Search: ");
        String id = scanner.nextLine().trim();
        Student s = findStudentById(id);
        if (s != null) {
            s.displayDetailedReport();
        } else {
            System.out.println("[NOT FOUND] No record matching ID: " + id);
        }
    }

    private static void updateStudent() {
        System.out.print("Enter Student ID to Update: ");
        String id = scanner.nextLine().trim();
        Student s = findStudentById(id);
        if (s == null) {
            System.out.println("[NOT FOUND] Student record not found.");
            return;
        }

        System.out.print("Enter New Name (or press Enter to keep '" + s.getName() + "'): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            s.setName(newName);
        }

        System.out.println("Updating Marks for Subjects:");
        for (Subject sub : s.getSubjects()) {
            System.out.print("Enter new marks for " + sub.getSubjectName() + " (Current: " + sub.getMarks() + "): ");
            double newMarks = scanner.nextDouble();
            scanner.nextLine();
            sub.setMarks(newMarks);
        }
        System.out.println("[SUCCESS] Student record updated successfully!");
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        String id = scanner.nextLine().trim();
        Student s = findStudentById(id);
        if (s != null) {
            studentDirectory.remove(s);
            System.out.println("[SUCCESS] Student record removed.");
        } else {
            System.out.println("[NOT FOUND] No student matching ID: " + id);
        }
    }

    private static void displayClassAnalytics() {
        if (studentDirectory.isEmpty()) {
            System.out.println("\n[INFO] No records available for analytics.");
            return;
        }

        double classAvg = studentDirectory.stream().mapToDouble(Student::calculatePercentage).average().orElse(0.0);
        Student topStudent = studentDirectory.stream().max(Comparator.comparingDouble(Student::calculatePercentage)).orElse(null);
        Student lowStudent = studentDirectory.stream().min(Comparator.comparingDouble(Student::calculatePercentage)).orElse(null);

        System.out.println("\n========================================================");
        System.out.println("             CLASS PERFORMANCE ANALYTICS                ");
        System.out.println("========================================================");
        System.out.println("Total Enrolled Students : " + studentDirectory.size());
        System.out.printf("Class Average Percentage: %.2f%%\n", classAvg);
        if (topStudent != null) {
            System.out.printf("Top Performer           : %s (%.2f%% - Grade %s)\n", topStudent.getName(), topStudent.calculatePercentage(), topStudent.getGrade());
        }
        if (lowStudent != null) {
            System.out.printf("Needs Attention         : %s (%.2f%% - Grade %s)\n", lowStudent.getName(), lowStudent.calculatePercentage(), lowStudent.getGrade());
        }
        System.out.println("========================================================");
    }

    private static Student findStudentById(String id) {
        for (Student s : studentDirectory) {
            if (s.getStudentId().equalsIgnoreCase(id)) return s;
        }
        return null;
    }
}
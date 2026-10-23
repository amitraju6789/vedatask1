package task23;

import java.io.*;
import java.util.*;

// Student Model Class
class Student {
    private final String rollNumber;
    private String name;
    private String course;
    private double gpa;

    public Student(String rollNumber, String name, String course, double gpa) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.course = course;
        this.gpa = gpa;
    }

    public String getRollNumber() { return rollNumber; }
    public String getName() { return name; }
    public String getCourse() { return course; }
    public double getGpa() { return gpa; }

    public void setName(String name) { this.name = name; }
    public void setCourse(String course) { this.course = course; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // Format for text file storage (CSV style: RollNo,Name,Course,GPA)
    public String toFileString() {
        return rollNumber + "," + name + "," + course + "," + gpa;
    }

    // Parse CSV line from file back into Student object
    public static Student fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            return new Student(parts[0].trim(), parts[1].trim(), parts[2].trim(), Double.parseDouble(parts[3].trim()));
        }
        return null;
    }

    public void displayStudent() {
        System.out.printf("| %-12s | %-20s | %-18s | %-6.2f |\n", rollNumber, name, course, gpa);
    }
}

public class StudentRecordSystem {

    private static final String FILE_NAME = "students.txt";

    // Helper: Read all students from text file
    private List<Student> loadStudentsFromFile() {
        List<Student> studentList = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return studentList; // Return empty list if file doesn't exist yet
        }

        // Try-with-resources automatically closes reader
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student student = Student.fromFileString(line);
                    if (student != null) {
                        studentList.add(student);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[FILE ERROR] Error reading student data: " + e.getMessage());
        }

        return studentList;
    }

    // Helper: Overwrite entire file with updated list of students
    private void saveAllStudentsToFile(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (Student s : students) {
                writer.write(s.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("[FILE ERROR] Error saving student data: " + e.getMessage());
        }
    }

    // 1. Add Student Record (Append to file)
    public void addStudent(Student student) {
        List<Student> existingStudents = loadStudentsFromFile();
        for (Student s : existingStudents) {
            if (s.getRollNumber().equalsIgnoreCase(student.getRollNumber())) {
                System.out.println("[ERROR] Student with Roll Number '" + student.getRollNumber() + "' already exists!");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(student.toFileString());
            writer.newLine();
            System.out.println("[SUCCESS] Student record saved persistently to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("[FILE ERROR] Failed to add student record: " + e.getMessage());
        }
    }

    // 2. Retrieve All Records
    public void displayAllStudents() {
        List<Student> students = loadStudentsFromFile();
        if (students.isEmpty()) {
            System.out.println("\n[INFO] No student records found in " + FILE_NAME);
            return;
        }

        System.out.println("\n------------------------------------------------------------------");
        System.out.printf("| %-12s | %-20s | %-18s | %-6s |\n", "Roll No", "Name", "Course", "GPA");
        System.out.println("------------------------------------------------------------------");
        for (Student s : students) {
            s.displayStudent();
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Total Records Stored: " + students.size());
    }

    // 3. Search Student by Roll Number
    public void searchStudent(String rollNumber) {
        List<Student> students = loadStudentsFromFile();
        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                System.out.println("\n[RECORD FOUND]");
                System.out.println("------------------------------------------------------------------");
                System.out.printf("| %-12s | %-20s | %-18s | %-6s |\n", "Roll No", "Name", "Course", "GPA");
                System.out.println("------------------------------------------------------------------");
                s.displayStudent();
                System.out.println("------------------------------------------------------------------");
                return;
            }
        }
        System.out.println("[NOT FOUND] No record found for Roll Number: " + rollNumber);
    }

    // 4. Update Student Record
    public void updateStudent(String rollNumber, String newName, String newCourse, double newGpa) {
        List<Student> students = loadStudentsFromFile();
        boolean found = false;

        for (Student s : students) {
            if (s.getRollNumber().equalsIgnoreCase(rollNumber)) {
                s.setName(newName);
                s.setCourse(newCourse);
                s.setGpa(newGpa);
                found = true;
                break;
            }
        }

        if (found) {
            saveAllStudentsToFile(students);
            System.out.println("[SUCCESS] Student record updated successfully in file!");
        } else {
            System.out.println("[ERROR] Student Roll Number '" + rollNumber + "' not found.");
        }
    }

    public static void main(String[] args) {
        StudentRecordSystem system = new StudentRecordSystem();
        Scanner scanner = new Scanner(System.in);

        // Seed default initial records if file doesn't exist
        File file = new File(FILE_NAME);
        if (!file.exists() || file.length() == 0) {
            system.addStudent(new Student("CS101", "Amit Kumar Singh", "B.Tech CS", 8.95));
            system.addStudent(new Student("CS102", "Rohan Sharma", "B.Tech CS", 8.40));
        }

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("   FILE-BASED STUDENT RECORD SYSTEM      ");
            System.out.println("=========================================");
            System.out.println("1. Add Student Record");
            System.out.println("2. Display All Student Records");
            System.out.println("3. Search Student by Roll Number");
            System.out.println("4. Update Student Record");
            System.out.println("5. Exit");
            System.out.print("Select an option (1-5): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Roll Number: ");
                        String roll = scanner.nextLine().trim();
                        System.out.print("Enter Student Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Course: ");
                        String course = scanner.nextLine().trim();
                        System.out.print("Enter GPA: ");
                        double gpa = scanner.nextDouble();
                        scanner.nextLine();

                        system.addStudent(new Student(roll, name, course, gpa));
                        break;

                    case 2:
                        system.displayAllStudents();
                        break;

                    case 3:
                        System.out.print("Enter Roll Number to Search: ");
                        String searchRoll = scanner.nextLine().trim();
                        system.searchStudent(searchRoll);
                        break;

                    case 4:
                        System.out.print("Enter Roll Number to Update: ");
                        String updateRoll = scanner.nextLine().trim();
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine().trim();
                        System.out.print("Enter New Course: ");
                        String newCourse = scanner.nextLine().trim();
                        System.out.print("Enter New GPA: ");
                        double newGpa = scanner.nextDouble();
                        scanner.nextLine();

                        system.updateStudent(updateRoll, newName, newCourse, newGpa);
                        break;

                    case 5:
                        System.out.println("\nExiting Student Record System. All changes saved to " + FILE_NAME);
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid selection! Please enter 1-5.");
                }
            } else {
                System.out.println("Invalid input format!");
                scanner.next();
            }
        }

        scanner.close();
    }
}
package task24;

import java.io.*;
import java.util.*;

// Employee Model
class EmployeeRecord {
    private final String empId;
    private final String name;
    private final String department;
    private final double salary;

    public EmployeeRecord(String empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getEmpId() { return empId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }

    public void display() {
        System.out.printf("| %-8s | %-20s | %-18s | $%-10.2f |\n", empId, name, department, salary);
    }
}

public class CSVEmployeeProcessor {

    private static final String INPUT_CSV = "employees.csv";
    private static final String OUTPUT_CSV = "processed_employees_summary.csv";

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("    CSV EMPLOYEE DATA PROCESSOR SYSTEM   ");
        System.out.println("=========================================");

        // Step 1: Ensure input sample CSV file exists
        createSampleCSVIfNotExists();

        List<EmployeeRecord> validEmployees = new ArrayList<>();
        int totalRowsParsed = 0;
        int malformedRowCount = 0;

        // Step 2: Read and Validate Input CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_CSV))) {
            String line;
            boolean isHeader = true;

            System.out.println("\n[1] Parsing & Validating CSV Rows...");

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                if (isHeader) {
                    isHeader = false; // Skip CSV header row
                    continue;
                }

                totalRowsParsed++;
                String[] columns = line.split(",", -1); // Preserve trailing empty fields

                // Validation 1: Check column count (expects 4 fields: EmpID, Name, Department, Salary)
                if (columns.length < 4) {
                    System.out.println("[MALFORMED ROW] Insufficient columns -> " + line);
                    malformedRowCount++;
                    continue;
                }

                String id = columns[0].trim();
                String name = columns[1].trim();
                String dept = columns[2].trim();
                String salaryStr = columns[3].trim();

                // Validation 2: Check missing/empty essential string fields
                if (id.isEmpty() || name.isEmpty() || dept.isEmpty()) {
                    System.out.println("[MISSING FIELDS] Row contains blank mandatory data -> " + line);
                    malformedRowCount++;
                    continue;
                }

                // Validation 3: Parse and validate numeric salary field
                try {
                    double salary = Double.parseDouble(salaryStr);
                    if (salary < 0) {
                        System.out.println("[INVALID VALUE] Salary cannot be negative -> " + line);
                        malformedRowCount++;
                        continue;
                    }

                    // Record successfully validated
                    validEmployees.add(new EmployeeRecord(id, name, dept, salary));

                } catch (NumberFormatException e) {
                    System.out.println("[MALFORMED NUMBER] Invalid salary numeric format -> " + line);
                    malformedRowCount++;
                }
            }

        } catch (IOException e) {
            System.out.println("[FILE ERROR] Unable to read input CSV: " + e.getMessage());
            return;
        }

        // Step 3: Compute Summary Statistics
        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println("VALIDATED EMPLOYEE RECORDS");
        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("| %-8s | %-20s | %-18s | %-11s |\n", "Emp ID", "Name", "Department", "Salary");
        System.out.println("-------------------------------------------------------------------------");

        double totalSalarySum = 0;
        EmployeeRecord highestPaid = null;

        for (EmployeeRecord emp : validEmployees) {
            emp.display();
            totalSalarySum += emp.getSalary();

            if (highestPaid == null || emp.getSalary() > highestPaid.getSalary()) {
                highestPaid = emp;
            }
        }
        System.out.println("-------------------------------------------------------------------------");

        double avgSalary = validEmployees.isEmpty() ? 0 : (totalSalarySum / validEmployees.size());

        System.out.println("\n[2] PROCESSOR STATISTICS SUMMARY:");
        System.out.println("Total Rows Processed : " + totalRowsParsed);
        System.out.println("Valid Records        : " + validEmployees.size());
        System.out.println("Malformed/Skipped    : " + malformedRowCount);
        System.out.printf("Average Salary       : $%.2f\n", avgSalary);
        if (highestPaid != null) {
            System.out.printf("Highest Paid Employee: %s ($%.2f)\n", highestPaid.getName(), highestPaid.getSalary());
        }

        // Step 4: Write Clean Summary Report CSV
        writeProcessedSummaryCSV(validEmployees, totalRowsParsed, malformedRowCount, avgSalary, highestPaid);
    }

    private static void createSampleCSVIfNotExists() {
        File file = new File(INPUT_CSV);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("EmpID,Name,Department,Salary\n");
                writer.write("E101,Amit Kumar Singh,Software Engineering,85000.00\n");
                writer.write("E102,Rohan Sharma,Product Engineering,72000.00\n");
                writer.write("E103,,Quality Assurance,55000.00\n"); // Missing field
                writer.write("E104,Priya Patel,Data Analytics,INVALID_SALARY\n"); // Malformed salary
                writer.write("E105,Vikram Verma,DevOps,92000.00\n");
                writer.write("E106,Neha Gupta,UI/UX Design,-1000.00\n"); // Negative salary
                System.out.println("[SAMPLE CREATED] Generated initial sample input file: " + INPUT_CSV);
            } catch (IOException e) {
                System.out.println("[FILE ERROR] Failed to create sample CSV: " + e.getMessage());
            }
        }
    }

    private static void writeProcessedSummaryCSV(List<EmployeeRecord> validEmployees, int total, int skipped, double avgSalary, EmployeeRecord highest) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_CSV))) {
            writer.write("SUMMARY REPORT,PROCESSED DATA METRICS\n");
            writer.write("Total Rows Processed," + total + "\n");
            writer.write("Valid Records Retained," + validEmployees.size() + "\n");
            writer.write("Malformed Rows Skipped," + skipped + "\n");
            writer.write("Calculated Average Salary," + String.format("%.2f", avgSalary) + "\n");
            writer.write("Highest Paid Employee," + (highest != null ? highest.getName() : "N/A") + "\n\n");

            writer.write("EmpID,Name,Department,Salary,Status\n");
            for (EmployeeRecord emp : validEmployees) {
                writer.write(String.format("%s,%s,%s,%.2f,VALIDATED\n", emp.getEmpId(), emp.getName(), emp.getDepartment(), emp.getSalary()));
            }

            System.out.println("\n[SUCCESS] Generated processed CSV output report: " + OUTPUT_CSV);

        } catch (IOException e) {
            System.out.println("[FILE ERROR] Failed to write processed CSV output: " + e.getMessage());
        }
    }
}
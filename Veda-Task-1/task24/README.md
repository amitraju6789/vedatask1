# Task 24: CSV Employee Data Processor

A Java file processor application that parses structured CSV employee datasets, performs row-by-row validation, computes statistical summaries, and exports validated records to an output report CSV.

## Features
- **Resilient Row Validation:** Catches blank fields, malformed numbers, and negative salaries without crashing program flow.
- **Data Statistics:** Computes total rows processed, valid vs skipped rows, average salary, and top earner.
- **Output Report Generation:** Writes structured execution statistics and clean employee records into `processed_employees_summary.csv`.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task24/CSVEmployeeProcessor.java
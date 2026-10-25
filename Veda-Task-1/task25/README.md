# Task 25: Create a Log File Analyzer

A Java application that reads application log files line-by-line, parses log severity levels, aggregates frequency counts using `HashMap`, and generates an executive error summary report.

## Features
- **Line-by-Line Log Parsing:** Processes large files efficiently using `BufferedReader`.
- **Frequency Aggregation (`HashMap`):** Tracks occurrence counts for `INFO`, `WARNING`, `ERROR`, and `DEBUG` log severities.
- **Error Summary Extraction:** Isolates critical error alerts into a dedicated diagnostic summary.
- **Report Generation:** Exports execution metrics and findings into `log_analysis_report.txt`.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task25/LogFileAnalyzer.java
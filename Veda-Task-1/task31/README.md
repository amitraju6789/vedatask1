# Task 31: Build an Employee Analytics System Using Streams

A Java enterprise analytics application utilizing modern Java Streams and Collectors to compute department statistics, salary extremes, workforce headcounts, and executive summaries.

## Features
- **Salary Analysis & Extremes:** Uses `max()` and `min()` with custom comparators to isolate highest and lowest earners.
- **Department Statistics:** Leverages `Collectors.groupingBy()` combined with `averagingDouble()` and `counting()` to generate departmental metrics.
- **Employee Ranking:** Sorts and structures workforce records declaratively.
- **Executive Reporting:** Summarizes payroll aggregates and workforce distributions.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task31/EmployeeAnalyticsSystem.java
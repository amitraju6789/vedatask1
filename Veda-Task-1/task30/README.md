# Task 30: Process Employee Data Using Java Streams

A Java application demonstrating functional pipeline data processing using Java Streams API, Lambda Expressions, and Collectors.

## Features
- **Declarative Filtering:** Filters records matching dynamic predicates without explicit loops.
- **Sorting Pipelines:** Orders object collections using custom Comparators.
- **Grouping & Partitioning:** Groups datasets into department buckets using `Collectors.groupingBy`.
- **Numerical Aggregations:** Computes total, average, minimum, and maximum metrics via `DoubleSummaryStatistics`.

## How to Run
1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile: `javac task30/StreamEmployeeProcessor.java`
3. Execute: `java task30.StreamEmployeeProcessor`
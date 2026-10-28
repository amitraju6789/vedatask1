# Task 28: Build a Multithreaded File Search Tool

A Java concurrency application that searches multiple text files concurrently for a specific keyword using fixed-size thread pools (`ExecutorService`).

## Features
- **Keyword Search Utility:** Scans documents for matching query strings line by line.
- **`ExecutorService` Thread Pools:** Avoids unbounded thread creation by pooling and reusing worker threads.
- **Thread-Safe Result Collection:** Utilizes `ConcurrentLinkedQueue` to aggregate search results across parallel tasks without race conditions.
- **Execution Time Benchmark:** Compares single-threaded vs multithreaded file search durations.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task28/MultithreadedFileSearchTool.java
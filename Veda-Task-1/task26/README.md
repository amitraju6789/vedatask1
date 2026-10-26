# Task 26: Build a Multithreaded Number Processor

A Java concurrency application comparing single-threaded versus multithreaded processing performance across a large numerical dataset.

## Features
- **Independent Work Range Splitting:** Divides massive number ranges equally across multiple concurrent worker threads.
- **Thread-Safety Considerations:** Utilizes thread-local accumulation and `AtomicLong` variables to eliminate race conditions without coarse-grained synchronization locks.
- **Execution Time Benchmarking:** Measures and compares single-threaded vs multithreaded execution durations and computes speedup factors.

## How to Run

1. Open terminal in the root directory (`Veda-Task-1`).
2. Compile the Java source file:
   ```bash
   javac task26/MultithreadedNumberProcessor.java
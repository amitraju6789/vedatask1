package task26;

import java.util.concurrent.atomic.AtomicLong;

// Worker class implementing Runnable to process a specific numeric range concurrently
class NumberRangeWorker implements Runnable {
    private final long startRange;
    private final long endRange;
    private final AtomicLong sharedPrimeCount;
    private final AtomicLong sharedSum;

    public NumberRangeWorker(long startRange, long endRange, AtomicLong sharedPrimeCount, AtomicLong sharedSum) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.sharedPrimeCount = sharedPrimeCount;
        this.sharedSum = sharedSum;
    }

    private boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    @Override
    public void run() {
        long localSum = 0;
        long localPrimes = 0;

        for (long i = startRange; i <= endRange; i++) {
            localSum += i;
            if (isPrime(i)) {
                localPrimes++;
            }
        }

        // Thread-safe aggregation using Atomic variables
        sharedSum.addAndGet(localSum);
        sharedPrimeCount.addAndGet(localPrimes);
    }
}

public class MultithreadedNumberProcessor {

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("      MULTITHREADED NUMBER PROCESSOR BENCHMARK   ");
        System.out.println("=================================================");

        long rangeStart = 1;
        long rangeEnd = 150_000_000L; // 150 Million numbers for noticeable parallel speedup
        int numberOfThreads = 4;      // Splitting workload across 4 worker threads

        System.out.printf("Processing Range : %,d to %,d\n", rangeStart, rangeEnd);
        System.out.println("Active Threads   : " + numberOfThreads);

        // ==========================================
        // 1. SINGLE-THREADED EXECUTION
        // ==========================================
        System.out.println("\n[1] Running Single-Threaded Calculation...");
        long singleStartTime = System.currentTimeMillis();

        long singleSum = 0;
        long singlePrimeCount = 0;

        for (long i = rangeStart; i <= rangeEnd; i++) {
            singleSum += i;
            if (isPrimeStatic(i)) {
                singlePrimeCount++;
            }
        }

        long singleEndTime = System.currentTimeMillis();
        long singleDuration = singleEndTime - singleStartTime;

        System.out.printf("Single-Threaded Result -> Sum: %d | Primes Found: %,d\n", singleSum, singlePrimeCount);
        System.out.printf("Single-Threaded Time   -> %,d ms\n", singleDuration);


        // ==========================================
        // 2. MULTITHREADED EXECUTION
        // ==========================================
        System.out.println("\n[2] Running Multithreaded Calculation (" + numberOfThreads + " threads)...");
        long multiStartTime = System.currentTimeMillis();

        AtomicLong multiSum = new AtomicLong(0);
        AtomicLong multiPrimeCount = new AtomicLong(0);

        long chunkSize = (rangeEnd - rangeStart + 1) / numberOfThreads;
        Thread[] threads = new Thread[numberOfThreads];

        for (int t = 0; t < numberOfThreads; t++) {
            long subStart = rangeStart + (t * chunkSize);
            long subEnd = (t == numberOfThreads - 1) ? rangeEnd : (subStart + chunkSize - 1);

            threads[t] = new Thread(new NumberRangeWorker(subStart, subEnd, multiPrimeCount, multiSum));
            threads[t].start();
        }

        // Wait for all worker threads to complete execution
        try {
            for (int t = 0; t < numberOfThreads; t++) {
                threads[t].join();
            }
        } catch (InterruptedException e) {
            System.out.println("[ERROR] Thread execution interrupted: " + e.getMessage());
        }

        long multiEndTime = System.currentTimeMillis();
        long multiDuration = multiEndTime - multiStartTime;

        System.out.printf("Multithreaded Result   -> Sum: %d | Primes Found: %,d\n", multiSum.get(), multiPrimeCount.get());
        System.out.printf("Multithreaded Time     -> %,d ms\n", multiDuration);


        // ==========================================
        // 3. EXECUTION COMPARISON & SPEEDUP
        // ==========================================
        System.out.println("\n---------------------------------------------------------");
        System.out.println("                 BENCHMARK PERFORMANCE SUMMARY           ");
        System.out.println("---------------------------------------------------------");
        System.out.printf("Single-Threaded Duration : %,d ms\n", singleDuration);
        System.out.printf("Multithreaded Duration   : %,d ms\n", multiDuration);
        if (multiDuration > 0) {
            double speedup = (double) singleDuration / multiDuration;
            System.out.printf("Performance Speedup      : %.2fx faster\n", speedup);
        }
        System.out.println("---------------------------------------------------------");
    }

    private static boolean isPrimeStatic(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}
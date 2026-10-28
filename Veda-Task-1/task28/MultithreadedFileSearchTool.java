package task28;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

// Search Result DTO to store match details safely
class SearchMatch {
    private final String fileName;
    private final int lineNumber;
    private final String lineContent;

    public SearchMatch(String fileName, int lineNumber, String lineContent) {
        this.fileName = fileName;
        this.lineNumber = lineNumber;
        this.lineContent = lineContent;
    }

    public String getFileName() { return fileName; }
    public int getLineNumber() { return lineNumber; }
    public String getLineContent() { return lineContent; }

    public void display() {
        System.out.printf("[MATCH] File: %-15s | Line %-4d | %s\n", fileName, lineNumber, lineContent.trim());
    }
}

// Worker Task to search a single file for the keyword
class FileSearchTask implements Callable<Integer> {
    private final File file;
    private final String keyword;
    private final ConcurrentLinkedQueue<SearchMatch> resultsQueue;

    public FileSearchTask(File file, String keyword, ConcurrentLinkedQueue<SearchMatch> resultsQueue) {
        this.file = file;
        this.keyword = keyword.toLowerCase();
        this.resultsQueue = resultsQueue;
    }

    @Override
    public Integer call() {
        int matchCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
                lineNum++;
                if (line.toLowerCase().contains(keyword)) {
                    matchCount++;
                    resultsQueue.add(new SearchMatch(file.getName(), lineNum, line));
                }
            }
        } catch (IOException e) {
            System.out.println("[ERROR reading file] " + file.getName() + ": " + e.getMessage());
        }
        return matchCount;
    }
}

public class MultithreadedFileSearchTool {

    private static final String SEARCH_DIR = "search_documents";

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("     MULTITHREADED FILE SEARCH TOOL BENCHMARK    ");
        System.out.println("=================================================");

        // Step 1: Create sample text documents for searching
        generateSampleSearchFiles();

        File dir = new File(SEARCH_DIR);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("[ERROR] No text files found to search.");
            return;
        }

        String searchKeyword = "exception";
        System.out.println("Target Keyword : \"" + searchKeyword + "\"");
        System.out.println("Total Files    : " + files.length);

        // ==========================================
        // 1. SINGLE-THREADED SEARCH BENCHMARK
        // ==========================================
        System.out.println("\n[1] Running Single-Threaded Search...");
        long singleStart = System.currentTimeMillis();
        int singleTotalMatches = 0;
        List<SearchMatch> singleResults = new ArrayList<>();

        for (File file : files) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNum = 0;
                while ((line = reader.readLine()) != null) {
                    lineNum++;
                    if (line.toLowerCase().contains(searchKeyword.toLowerCase())) {
                        singleTotalMatches++;
                        singleResults.add(new SearchMatch(file.getName(), lineNum, line));
                    }
                }
            } catch (IOException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
        long singleDuration = System.currentTimeMillis() - singleStart;

        System.out.println("Single-Threaded Matches Found: " + singleTotalMatches);
        System.out.printf("Single-Threaded Time         : %,d ms\n", singleDuration);


        // ==========================================
        // 2. MULTITHREADED EXECUTORSERVICE SEARCH
        // ==========================================
        System.out.println("\n[2] Running Multithreaded ExecutorService Search...");
        long multiStart = System.currentTimeMillis();
        
        // Thread pool with fixed size to prevent unbounded thread creation
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ConcurrentLinkedQueue<SearchMatch> multiResultsQueue = new ConcurrentLinkedQueue<>();
        List<Future<Integer>> futures = new ArrayList<>();

        for (File file : files) {
            Callable<Integer> task = new FileSearchTask(file, searchKeyword, multiResultsQueue);
            futures.add(executor.submit(task));
        }

        int multiTotalMatches = 0;
        for (Future<Integer> future : futures) {
            try {
                multiTotalMatches += future.get(); // Collect task results safely
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("[ERROR executing task] " + e.getMessage());
            }
        }

        executor.shutdown(); // Gracefully shutdown thread pool
        long multiDuration = System.currentTimeMillis() - multiStart;

        System.out.println("Multithreaded Matches Found  : " + multiTotalMatches);
        System.out.printf("Multithreaded Time           : %,d ms\n", multiDuration);


        // ==========================================
        // 3. DISPLAY MATCH RESULTS & BENCHMARK
        // ==========================================
        System.out.println("\n---------------------------------------------------------");
        System.out.println("                 SEARCH MATCH RESULTS                    ");
        System.out.println("---------------------------------------------------------");
        for (SearchMatch match : multiResultsQueue) {
            match.display();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("                  PERFORMANCE SUMMARY                    ");
        System.out.println("---------------------------------------------------------");
        System.out.printf("Single-Threaded Duration : %,d ms\n", singleDuration);
        System.out.printf("Multithreaded Duration   : %,d ms\n", multiDuration);
        if (multiDuration > 0) {
            double speedup = (double) singleDuration / multiDuration;
            System.out.printf("Performance Speedup      : %.2fx faster\n", speedup);
        }
        System.out.println("---------------------------------------------------------");
    }

    private static void generateSampleSearchFiles() {
        File dir = new File(SEARCH_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            createFile(new File(dir, "doc1.txt"), 
                "Java is a powerful programming language.\nIt handles exception propagation robustly.\nNullPointerException is a common runtime exception.");
            
            createFile(new File(dir, "doc2.txt"), 
                "Multithreading allows concurrent task execution.\nExecutorService manages thread pools efficiently.\nAlways handle InterruptedException properly.");
            
            createFile(new File(dir, "doc3.txt"), 
                "File I/O operations require stream closing.\nTry-with-resources prevents resource leaks.\nIOException must be caught or declared.");
            
            createFile(new File(dir, "doc4.txt"), 
                "Collections framework provides high-performance data structures.\nHashMap guarantees O(1) average lookup time.\nConcurrentModificationException occurs during unsafe iteration.");
        } catch (IOException e) {
            System.out.println("[SETUP ERROR] Failed to create sample search files: " + e.getMessage());
        }
    }

    private static void createFile(File file, String content) throws IOException {
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(content);
            }
        }
    }
}
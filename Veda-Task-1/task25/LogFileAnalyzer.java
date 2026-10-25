package task25;

import java.io.*;
import java.util.*;

public class LogFileAnalyzer {

    private static final String INPUT_LOG = "app.log";
    private static final String OUTPUT_REPORT = "log_analysis_report.txt";

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("       LOG FILE ANALYZER SYSTEM          ");
        System.out.println("=========================================");

        // Step 1: Generate sample log file if missing
        createSampleLogFileIfNotExists();

        Map<String, Integer> logLevelCounts = new HashMap<>();
        List<String> errorMessages = new ArrayList<>();
        int totalLinesProcessed = 0;
        int malformedLinesCount = 0;

        // Initialize log level counts
        logLevelCounts.put("INFO", 0);
        logLevelCounts.put("WARNING", 0);
        logLevelCounts.put("ERROR", 0);
        logLevelCounts.put("DEBUG", 0);

        // Step 2: Read and parse log file line-by-line
        try (BufferedReader reader = new BufferedReader(new FileReader(INPUT_LOG))) {
            String line;
            System.out.println("\n[1] Parsing log file line-by-line...");

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                totalLinesProcessed++;

                // Expected Log Format Example: [2026-10-25 10:15:30] [INFO] Application started successfully.
                if (line.contains("[") && line.contains("]")) {
                    try {
                        int firstBracketClose = line.indexOf(']');
                        int secondBracketOpen = line.indexOf('[', firstBracketClose);
                        int secondBracketClose = line.indexOf(']', secondBracketOpen);

                        if (secondBracketOpen != -1 && secondBracketClose != -1) {
                            String level = line.substring(secondBracketOpen + 1, secondBracketClose).trim().toUpperCase();
                            String message = line.substring(secondBracketClose + 1).trim();

                            if (logLevelCounts.containsKey(level)) {
                                logLevelCounts.put(level, logLevelCounts.get(level) + 1);
                                if (level.equals("ERROR")) {
                                    errorMessages.add(message);
                                }
                            } else {
                                logLevelCounts.put(level, logLevelCounts.getOrDefault(level, 0) + 1);
                            }
                        } else {
                            malformedLinesCount++;
                        }
                    } catch (Exception e) {
                        malformedLinesCount++;
                    }
                } else {
                    malformedLinesCount++;
                }
            }

        } catch (IOException e) {
            System.out.println("[FILE ERROR] Unable to read log file: " + e.getMessage());
            return;
        }

        // Step 3: Display Statistics Summary on Console
        System.out.println("\n-----------------------------------------");
        System.out.println("       LOG ANALYSIS STATISTICS           ");
        System.out.println("-----------------------------------------");
        System.out.println("Total Lines Processed : " + totalLinesProcessed);
        System.out.println("Malformed/Unparsed    : " + malformedLinesCount);
        System.out.println("-----------------------------------------");
        for (Map.Entry<String, Integer> entry : logLevelCounts.entrySet()) {
            System.out.printf("Level [%-7s] : %d occurrence(s)\n", entry.getKey(), entry.getValue());
        }
        System.out.println("-----------------------------------------");

        if (!errorMessages.isEmpty()) {
            System.out.println("\n[ERROR SUMMARY & ALERTS]");
            int errIdx = 1;
            for (String err : errorMessages) {
                System.out.println("  " + (errIdx++) + ". " + err);
            }
        }

        // Step 4: Write Analysis Report to Output File
        writeAnalysisReport(totalLinesProcessed, malformedLinesCount, logLevelCounts, errorMessages);
    }

    private static void createSampleLogFileIfNotExists() {
        File file = new File(INPUT_LOG);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("[2026-10-25 08:00:01] [INFO] Application context initialized successfully.\n");
                writer.write("[2026-10-25 08:05:22] [DEBUG] Database connection pool established (size: 10).\n");
                writer.write("[2026-10-25 08:30:10] [WARNING] High memory consumption detected: 84% utilized.\n");
                writer.write("[2026-10-25 09:12:45] [ERROR] NullPointerException in UserService.authenticateUser(Line 42).\n");
                writer.write("[2026-10-25 09:15:00] [INFO] User 'amitraju6789' logged in successfully.\n");
                writer.write("[2026-10-25 10:00:18] [WARNING] Deprecated API endpoint '/api/v1/old-lookup' accessed.\n");
                writer.write("[2026-10-25 10:45:33] [ERROR] SQLException: Connection timeout while querying inventory DB.\n");
                writer.write("INVALID LOG LINE FORMAT WITHOUT BRACKETS\n");
                writer.write("[2026-10-25 11:00:00] [INFO] Scheduled batch job executed successfully.\n");
                System.out.println("[SAMPLE CREATED] Generated initial sample log file: " + INPUT_LOG);
            } catch (IOException e) {
                System.out.println("[FILE ERROR] Failed to create sample log file: " + e.getMessage());
            }
        }
    }

    private static void writeAnalysisReport(int total, int malformed, Map<String, Integer> counts, List<String> errors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_REPORT))) {
            writer.write("==================================================\r\n");
            writer.write("        APPLICATION LOG ANALYSIS REPORT           \r\n");
            writer.write("==================================================\r\n");
            writer.write("Total Log Lines Scanned : " + total + "\r\n");
            writer.write("Malformed Lines Ignored : " + malformed + "\r\n\r\n");

            writer.write("--- LOG LEVEL OCCURRENCE COUNTS ---\r\n");
            for (Map.Entry<String, Integer> entry : counts.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue() + "\r\n");
            }

            writer.write("\r\n--- ERROR MESSAGES SUMMARY ---\r\n");
            int i = 1;
            for (String err : errors) {
                writer.write((i++) + ". " + err + "\r\n");
            }
            writer.write("==================================================\r\n");

            System.out.println("\n[SUCCESS] Analysis report exported successfully to: " + OUTPUT_REPORT);

        } catch (IOException e) {
            System.out.println("[FILE ERROR] Failed to write analysis report: " + e.getMessage());
        }
    }
}
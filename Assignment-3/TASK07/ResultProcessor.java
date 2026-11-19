
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultProcessor {

    static final int TOTAL_FILES = 10000;
    static final String FOLDER = "score_files";

    // Create sample student score files
    static void createFiles() throws IOException {

        Files.createDirectories(Paths.get(FOLDER));

        for (int i = 1; i <= TOTAL_FILES; i++) {

            Path path = Paths.get(FOLDER, "student" + i + ".txt");

            // 100 missing files
            if (i % 100 == 0) {
                Files.deleteIfExists(path);
                continue;
            }

            // 100 corrupted files
            if (i % 100 == 1) {
                Files.write(path,
                    "INVALID_DATA".getBytes(StandardCharsets.UTF_8));
                continue;
            }

            // Valid student records
            int marks = i % 101;

            String data = "STU" + i + "," + marks;

            Files.write(path,
                data.getBytes(StandardCharsets.UTF_8));
        }
    }

    // Process files using Callable
    static void processFiles(int poolSize) throws Exception {

        ExecutorService executor =
            Executors.newFixedThreadPool(poolSize);

        ConcurrentHashMap<String, Integer> results =
            new ConcurrentHashMap<>();

        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger failure = new AtomicInteger(0);

        List<Future<?>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= TOTAL_FILES; i++) {

            final int id = i;

            Callable<Void> task = () -> {

                try {
                    Path path = Paths.get(
                        FOLDER, "student" + id + ".txt");

                    String data = Files.readString(path);

                    String[] parts = data.trim().split(",");

                    if (parts.length != 2) {
                        throw new IllegalArgumentException(
                            "Invalid file format");
                    }

                    int marks = Integer.parseInt(parts[1]);

                    if (marks < 0 || marks > 100) {
                        throw new IllegalArgumentException(
                            "Invalid marks");
                    }

                    results.put(parts[0], marks);
                    success.incrementAndGet();

                } catch (Exception e) {

                    failure.incrementAndGet();
                }

                return null;
            };

            futures.add(executor.submit(task));
        }

        // Wait for all tasks
        for (Future<?> future : futures) {
            future.get();
        }

        executor.shutdown();

        long endTime = System.currentTimeMillis();

        System.out.println("\nThread Pool Size: " + poolSize);
        System.out.println("Successful Files: " + success.get());
        System.out.println("Failed Files: " + failure.get());
        System.out.println("Total Processed: "
                + (success.get() + failure.get()));
        System.out.println("Results Stored: " + results.size());
        System.out.println("Time Taken: "
                + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) throws Exception {

        System.out.println("SEMESTER RESULT PROCESSOR");

        createFiles();

        int[] poolSizes = {1, 2, 4, 8};

        for (int size : poolSizes) {
            processFiles(size);
        }

        System.out.println("\nAll student files processed!");
    }
}

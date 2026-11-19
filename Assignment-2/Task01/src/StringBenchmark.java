
import java.util.*;

public class StringBenchmark {

    static volatile int result;

    static String usingString(String[] fields) {
        String text = "";
        for (String field : fields) {
            text = text + field;
        }
        return text;
    }

    static String usingBuilder(String[] fields) {
        StringBuilder text = new StringBuilder();
        for (String field : fields) {
            text.append(field);
        }
        return text.toString();
    }

    static String usingBuffer(String[] fields) {
        StringBuffer text = new StringBuffer();
        for (String field : fields) {
            text.append(field);
        }
        return text.toString();
    }

    static long test(int tickets, int fieldCount, int method) {
        String[] fields = new String[fieldCount];

        for (int i = 0; i < fieldCount; i++) {
            fields[i] = "Field" + i + "-PNR192424002-";
        }

        long start = System.nanoTime();
        int checksum = 0;

        for (int i = 0; i < tickets; i++) {
            String output;

            if (method == 1)
                output = usingString(fields);
            else if (method == 2)
                output = usingBuilder(fields);
            else
                output = usingBuffer(fields);

            checksum += output.length();
        }

        long end = System.nanoTime();
        result = checksum;
        return end - start;
    }

    public static void main(String[] args) {

        int[][] workloads = {
            {1, 6},
            {1000, 50},
            {10000, 50}
        };

        String[] methods = {
            "String (+)",
            "StringBuilder",
            "StringBuffer"
        };

        System.out.println("RAILWAY E-TICKET STRING PERFORMANCE");
        System.out.println("Average of 5 runs (milliseconds)");
        System.out.println("------------------------------------------");

        for (int[] workload : workloads) {

            int tickets = workload[0];
            int fields = workload[1];

            System.out.println("\nTickets: " + tickets
                    + " | Fields per ticket: " + fields);

            for (int method = 1; method <= 3; method++) {

                // Warm-up
                test(Math.min(tickets, 100), fields, method);

                long total = 0;

                for (int run = 0; run < 5; run++) {
                    total += test(tickets, fields, method);
                }

                double averageMs = total / 5.0 / 1_000_000.0;

                System.out.printf("%-15s : %.3f ms%n",
                        methods[method - 1], averageMs);
            }
        }

        System.out.println("\nEstimated intermediate objects:");
        System.out.println("String (+): repeated intermediate Strings");
        System.out.println("StringBuilder: one builder and final String per ticket");
        System.out.println("StringBuffer: one buffer and final String per ticket");
        System.out.println("\nExperiment completed.");
    }
}

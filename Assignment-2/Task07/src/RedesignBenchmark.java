
public class RedesignBenchmark {

    static String original(int fields) {
        String result = "";

        for (int i = 0; i < fields; i++) {
            result += "FIELD" + i + ";";
        }

        return result;
    }

    static String redesigned(int fields) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < fields; i++) {
            result.append("FIELD").append(i).append(";");
        }

        return result.toString();
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};
        int repetitions = 5;

        System.out.println("Q7: ORIGINAL vs REDESIGNED SOLUTION");
        System.out.println("Measurements are average of 5 runs.");
        System.out.println("----------------------------------------");

        for (int size : sizes) {

            long originalTotal = 0;
            long redesignedTotal = 0;

            // Warm-up
            original(size);
            redesigned(size);

            String originalResult = "";
            String redesignedResult = "";

            for (int run = 0; run < repetitions; run++) {

                long start1 = System.nanoTime();
                originalResult = original(size);
                originalTotal += System.nanoTime() - start1;

                long start2 = System.nanoTime();
                redesignedResult = redesigned(size);
                redesignedTotal += System.nanoTime() - start2;
            }

            double originalAvg =
                originalTotal / (double) repetitions / 1_000_000;

            double redesignedAvg =
                redesignedTotal / (double) repetitions / 1_000_000;

            System.out.println("\nFields: " + size);
            System.out.printf("Original String: %.3f ms%n", originalAvg);
            System.out.printf("Redesigned StringBuilder: %.3f ms%n",
                              redesignedAvg);

            System.out.println("Output lengths match: "
                + (originalResult.length() == redesignedResult.length()));

            System.out.println("Output content matches: "
                + originalResult.equals(redesignedResult));
        }

        System.out.println("\nQ7 validation completed.");
    }
}

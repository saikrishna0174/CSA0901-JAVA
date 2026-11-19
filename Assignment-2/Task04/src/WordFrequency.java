
import java.util.*;

public class WordFrequency {

    static final int LOOKUPS = 100_000;
    static final int REPETITIONS = 5;

    static Map<String, Integer> createInventory(
            Map<String, Integer> map, int size) {

        for (int i = 1; i <= size; i++) {
            map.put(String.format("MED%06d", i), 100 + (i % 500));
        }

        return map;
    }

    static double benchmarkLookups(
            Map<String, Integer> inventory,
            List<String> searchKeys) {

        long total = 0;

        for (int run = 0; run < REPETITIONS; run++) {
            long start = System.nanoTime();

            long stockTotal = 0;

            for (String key : searchKeys) {
                Integer stock = inventory.get(key);

                if (stock != null) {
                    stockTotal += stock;
                }
            }

            total += System.nanoTime() - start;

            // Prevent the lookup result from being unused.
            if (stockTotal < 0) {
                System.out.println(stockTotal);
            }
        }

        return total / (double) REPETITIONS / 1_000_000;
    }

    static List<String> createSearchKeys(int size) {
        List<String> keys = new ArrayList<>();

        for (int i = 0; i < LOOKUPS; i++) {
            int medicineNumber = (i % size) + 1;
            keys.add(String.format("MED%06d", medicineNumber));
        }

        return keys;
    }

    static void displayOrder(String title, Map<String, Integer> map) {
        System.out.println(title);

        int count = 0;

        for (String key : map.keySet()) {
            System.out.print(key + " ");

            count++;

            if (count == 5) {
                break;
            }
        }

        System.out.println();
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 10000, 50000};

        System.out.println("Q4: PHARMACY INVENTORY BENCHMARK");
        System.out.println("Lookups per experiment: " + LOOKUPS);
        System.out.println("Average of " + REPETITIONS + " runs");
        System.out.println("----------------------------------------");

        for (int size : sizes) {

            Map<String, Integer> hashMap =
                    createInventory(new HashMap<>(), size);

            Map<String, Integer> treeMap =
                    createInventory(new TreeMap<>(), size);

            Map<String, Integer> linkedHashMap =
                    createInventory(new LinkedHashMap<>(), size);

            List<String> searchKeys = createSearchKeys(size);

            // Warm-up
            benchmarkLookups(hashMap, searchKeys);
            benchmarkLookups(treeMap, searchKeys);
            benchmarkLookups(linkedHashMap, searchKeys);

            double hashTime =
                    benchmarkLookups(hashMap, searchKeys);

            double treeTime =
                    benchmarkLookups(treeMap, searchKeys);

            double linkedTime =
                    benchmarkLookups(linkedHashMap, searchKeys);

            System.out.println("\nMedicine records: " + size);

            System.out.printf("HashMap: %.3f ms%n", hashTime);
            System.out.printf("TreeMap: %.3f ms%n", treeTime);
            System.out.printf("LinkedHashMap: %.3f ms%n", linkedTime);

            System.out.println("All maps contain same number of records: "
                    + (hashMap.size() == treeMap.size()
                    && treeMap.size() == linkedHashMap.size()));
        }

        System.out.println("\nMAP ITERATION ORDER DEMONSTRATION");

        Map<String, Integer> hash =
                createInventory(new HashMap<>(), 10);

        Map<String, Integer> tree =
                createInventory(new TreeMap<>(), 10);

        Map<String, Integer> linked =
                createInventory(new LinkedHashMap<>(), 10);

        displayOrder("HashMap:", hash);
        displayOrder("TreeMap:", tree);
        displayOrder("LinkedHashMap:", linked);

        System.out.println("\nQ4 benchmark completed.");
    }
}

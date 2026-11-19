
import java.util.*;

public class PlaylistBenchmark {

    static volatile long result;

    static long benchmark(String type, int size, String operation) {
        List<Integer> list = type.equals("ArrayList")
                ? new ArrayList<>()
                : new LinkedList<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
        }

        long start = System.nanoTime();

        switch (operation) {
            case "Add at end":
                for (int i = 0; i < 1000; i++)
                    list.add(i);
                break;

            case "Insert front":
                for (int i = 0; i < 1000; i++)
                    list.add(0, i);
                break;

            case "Insert middle":
                for (int i = 0; i < 1000; i++)
                    list.add(list.size() / 2, i);
                break;

            case "Remove front":
                for (int i = 0; i < 1000 && !list.isEmpty(); i++)
                    list.remove(0);
                break;

            case "Remove middle":
                for (int i = 0; i < 1000 && !list.isEmpty(); i++)
                    list.remove(list.size() / 2);
                break;

            case "Access k-th":
                long sum = 0;
                for (int i = 0; i < 1000; i++)
                    sum += list.get(size / 2);
                result = sum;
                break;

            case "Iterate":
                long total = 0;
                for (int value : list)
                    total += value;
                result = total;
                break;
        }

        return System.nanoTime() - start;
    }

    static void testArrayDeque(int size) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        long start = System.nanoTime();

        for (int i = 0; i < size; i++)
            deque.addLast(i);

        for (int i = 0; i < 1000; i++) {
            deque.addFirst(i);
            deque.addLast(i);
            deque.removeFirst();
            deque.removeLast();
        }

        long time = System.nanoTime() - start;

        System.out.printf("ArrayDeque Up Next: %.3f ms%n",
                time / 1_000_000.0);
    }

    public static void main(String[] args) {

        int[] sizes = {1000, 5000, 10000};

        String[] operations = {
            "Add at end",
            "Insert front",
            "Insert middle",
            "Remove front",
            "Remove middle",
            "Access k-th",
            "Iterate"
        };

        String[] types = {"ArrayList", "LinkedList"};

        System.out.println("PLAYLIST COLLECTION PERFORMANCE");
        System.out.println("Each operation performs 1000 repetitions");
        System.out.println("Average of 3 runs");
        System.out.println("----------------------------------------");

        for (int size : sizes) {

            System.out.println("\nPlaylist size: " + size);

            for (String operation : operations) {

                System.out.println("\n" + operation);

                for (String type : types) {

                    benchmark(type, size, operation); // warm-up

                    long total = 0;

                    for (int run = 0; run < 3; run++)
                        total += benchmark(type, size, operation);

                    System.out.printf("%-12s : %.3f ms%n",
                            type, total / 3.0 / 1_000_000.0);
                }
            }

            testArrayDeque(size);
        }

        System.out.println("\nPlaylist experiment completed.");
    }
}

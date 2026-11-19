
import java.io.*;

public class SalesReport {

    static String[] files = {"store1.txt", "store2.txt"};

    // Method 1: Manual File Closing
    static void generateManual() {

        BufferedWriter writer = null;
        int total = 0;

        try {
            writer = new BufferedWriter(
                new FileWriter("manual_report.txt"));

            for (String file : files) {

                BufferedReader reader = null;

                try {
                    reader = new BufferedReader(
                        new FileReader(file));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();

                        String[] data = line.split(",");
                        total += Integer.parseInt(data[1]);
                    }

                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            System.out.println("Reader closing error");
                        }
                    }
                }
            }

            writer.write("Total Sales: " + total);
            System.out.println("Manual report generated!");

        } catch (IOException e) {
            System.out.println("Manual Error: " + e.getMessage());

        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Writer closing error");
                }
            }
        }
    }

    // Method 2: Try-With-Resources
    static void generateSafe() {

        int total = 0;

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter("safe_report.txt"))) {

            for (String file : files) {

                try (BufferedReader reader = new BufferedReader(
                        new FileReader(file))) {

                    String line;

                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();

                        String[] data = line.split(",");
                        total += Integer.parseInt(data[1]);
                    }
                }
            }

            writer.write("Total Sales: " + total);
            System.out.println("Try-with-resources report generated!");

        } catch (IOException e) {
            System.out.println("Safe Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        generateManual();
        generateSafe();

        System.out.println("Both reports completed!");
    }
}

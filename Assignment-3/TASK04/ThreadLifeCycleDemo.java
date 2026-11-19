import java.util.concurrent.atomic.AtomicInteger;

// Method 1: Extending Thread
class ScannerThread extends Thread {

    int station;
    int scripts;
    AtomicInteger total;

    ScannerThread(int station, int scripts, AtomicInteger total) {
        this.station = station;
        this.scripts = scripts;
        this.total = total;
    }

    public void run() {
        try {
            System.out.println("Station " + station + " started");

            Thread.sleep(500);

            System.out.println("Station " + station +
                    " scanned " + scripts + " scripts");

            total.addAndGet(scripts);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Method 2: Implementing Runnable
class ScannerTask implements Runnable {

    int station;
    int scripts;
    AtomicInteger total;

    ScannerTask(int station, int scripts, AtomicInteger total) {
        this.station = station;
        this.scripts = scripts;
        this.total = total;
    }

    public void run() {
        try {
            System.out.println("Runnable Station " + station + " started");

            Thread.sleep(500);

            System.out.println("Runnable Station " + station +
                    " scanned " + scripts + " scripts");

            total.addAndGet(scripts);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Main Class
public class ThreadLifeCycleDemo {

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger total1 = new AtomicInteger(0);

        System.out.println("=== EXTENDING THREAD ===");

        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            threads[i] = new ScannerThread(i + 1, 10, total1);
            threads[i].start();
        }

        System.out.println("Station 1 alive: " + threads[0].isAlive());

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Total scripts scanned: " + total1.get());

        AtomicInteger total2 = new AtomicInteger(0);

        System.out.println("\n=== IMPLEMENTING RUNNABLE ===");

        Thread[] runnableThreads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            ScannerTask task = new ScannerTask(i + 1, 10, total2);
            runnableThreads[i] = new Thread(task);
            runnableThreads[i].start();
        }

        for (Thread t : runnableThreads) {
            t.join();
        }

        System.out.println("Total scripts scanned: " + total2.get());

        System.out.println("All scanner stations completed!");
    }
}
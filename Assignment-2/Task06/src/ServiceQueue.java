
import java.util.*;

class Customer implements Comparable<Customer> {
    String name;
    int priority;

    Customer(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    @Override
    public int compareTo(Customer other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
}

public class ServiceQueue {

    public static void main(String[] args) {

        System.out.println("1. FIFO QUEUE");

        Queue<String> normalQueue = new LinkedList<>();

        normalQueue.add("Arun");
        normalQueue.add("Divya");
        normalQueue.add("Kumar");

        System.out.println("Queue: " + normalQueue);
        System.out.println("Served: " + normalQueue.poll());
        System.out.println("Remaining: " + normalQueue);

        System.out.println("\n2. PRIORITY QUEUE");

        PriorityQueue<Customer> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Customer("Arun", 3));
        priorityQueue.add(new Customer("Divya", 1));
        priorityQueue.add(new Customer("Kumar", 2));

        System.out.println("Customers served by priority:");

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll());
        }

        System.out.println("\n3. QUEUE OPERATIONS");
        System.out.println("FIFO Queue insertion: O(1) at the tail.");
        System.out.println("FIFO Queue removal: O(1) at the head.");
        System.out.println("PriorityQueue insertion: O(log n).");
        System.out.println("PriorityQueue removal: O(log n).");
        System.out.println("PriorityQueue peek: O(1).");
    }
}

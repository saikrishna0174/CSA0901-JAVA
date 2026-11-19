
import java.util.*;

class Student {
    private final String regNo;
    private final String name;

    Student(String regNo, String name) {
        this.regNo = regNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return regNo.equals(((Student) o).regNo);
    }

    // TODO 1: hashCode() will be added after the first experiment.

    @Override
    public String toString() {
        return regNo + " " + name;
    }
}

public class Attendance {

    public static void main(String[] args) {

        System.out.println("TEST 1: SAME OBJECT SCANNED TWICE");
        Set<Student> test1 = new HashSet<>();
        Student s1 = new Student("192311001", "Arun");
        test1.add(s1);
        test1.add(s1);
        System.out.println("Predicted size: 1");
        System.out.println("Actual size: " + test1.size());

        System.out.println("\nTEST 2: DUPLICATE STUDENT OBJECTS");
        Set<Student> test2 = new HashSet<>();
        test2.add(new Student("192311001", "Arun"));
        test2.add(new Student("192311002", "Divya"));
        test2.add(new Student("192311001", "Arun"));
        System.out.println("Predicted size: 2");
        System.out.println("Actual size: " + test2.size());

        System.out.println("\nTEST 3: TREESET COMPARATOR");
        Set<Student> test3 = new TreeSet<>(
            Comparator.comparing(student -> student.toString())
        );
        test3.add(new Student("192311001", "Arun"));
        test3.add(new Student("192311001", "Kumar"));
        System.out.println("Predicted size: 2");
        System.out.println("Actual size: " + test3.size());

        System.out.println("\nAttendance experiment completed.");
    }
}

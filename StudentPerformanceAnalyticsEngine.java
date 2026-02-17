import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Student Performance Analytics Engine
 * Analyzes academic performance and generates reports
 */
public class StudentPerformanceAnalyticsEngine {

    public static void main(String[] args) {
        StudentPerformanceAnalyticsEngine engine =
                new StudentPerformanceAnalyticsEngine();

        engine.launch();
    }

    /**
     * Starts the application
     */
    public void launch() {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Student Performance Analytics System ===");

            int studentCount = readStudentCount(scanner);
            List<Student> students = collectStudentData(scanner, studentCount);

            generateReport(students);

        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
    }

    /**
     * Reads total number of students
     */
    private int readStudentCount(Scanner scanner) {

        int count;

        while (true) {
            try {
                System.out.print("Enter number of students: ");
                count = scanner.nextInt();

                if (count <= 0) {
                    throw new IllegalArgumentException(
                            "Student count must be positive.");
                }

                return count;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter an integer.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Collects student information
     */
    private List<Student> collectStudentData(Scanner scanner, int count) {

        List<Student> students = new ArrayList<>();

        for (int i = 1; i <= count; i++) {

            System.out.println("\nStudent " + i + " Details");

            System.out.print("Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            int marks = readMarks(scanner);

            students.add(new Student(name, marks));
        }

        return students;
    }

    /**
     * Reads student marks
     */
    private int readMarks(Scanner scanner) {

        int marks;

        while (true) {
            try {
                System.out.print("Marks (0-100): ");
                marks = scanner.nextInt();

                if (marks < 0 || marks > 100) {
                    throw new IllegalArgumentException(
                            "Marks must be between 0 and 100.");
                }

                return marks;

            } catch (InputMismatchException e) {
                System.out.println("Enter valid numeric marks.");
                scanner.nextLine();
            }
        }
    }

    /**
     * Generates final performance report
     */
    private void generateReport(List<Student> students) {

        System.out.println("\n===== Performance Report =====");

        double total = 0;

        for (Student student : students) {

            char grade = calculateGrade(student.getMarks());

            System.out.printf(
                    "Name: %-15s Marks: %-3d Grade: %c%n",
                    student.getName(),
                    student.getMarks(),
                    grade
            );

            total += student.getMarks();
        }

        double average = total / students.size();

        System.out.printf("\nClass Average: %.2f%n", average);
        System.out.println("==============================");
    }

    /**
     * Calculates grade
     */
    private char calculateGrade(int marks) {

        if (marks >= 90) return 'A';
        if (marks >= 80) return 'B';
        if (marks >= 70) return 'C';
        if (marks >= 60) return 'D';

        return 'F';
    }
}

/**
 * Student data model
 */
class Student {

    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Grade Calculator Application
 * Calculates student grade based on marks
 * and validates user input.
 */
public class GradeCalculator {

    public static void main(String[] args) {
        GradeCalculator calculator = new GradeCalculator();
        calculator.start();
    }

    /**
     * Starts the grade calculation process
     */
    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {

            int score = readScore(scanner);
            char grade = calculateGrade(score);
            String remark = getRemark(grade);

            displayResult(score, grade, remark);

        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Reads and validates user input
     */
    private int readScore(Scanner scanner) {
        int score;

        while (true) {
            try {
                System.out.print("Enter student score (0 - 100): ");
                score = scanner.nextInt();

                if (score < 0 || score > 100) {
                    throw new IllegalArgumentException("Score must be between 0 and 100.");
                }

                return score;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scanner.nextLine(); // clear buffer

            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Determines grade from score
     */
    private char calculateGrade(int score) {

        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';

        return 'F';
    }

    /**
     * Returns remark based on grade
     */
    private String getRemark(char grade) {

        switch (grade) {
            case 'A':
                return "Excellent Performance";
            case 'B':
                return "Very Good";
            case 'C':
                return "Good";
            case 'D':
                return "Needs Improvement";
            case 'F':
                return "Fail";
            default:
                return "No Remark";
        }
    }

    /**
     * Displays final result
     */
    private void displayResult(int score, char grade, String remark) {

        System.out.println("\n----- Result -----");
        System.out.println("Score  : " + score);
        System.out.println("Grade  : " + grade);
        System.out.println("Status : " + remark);
        System.out.println("------------------");
    }
}

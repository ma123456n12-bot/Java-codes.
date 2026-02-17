import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Personal Expense Manager
 * Records and analyzes user spending
 */
public class PersonalExpenseManager {

    public static void main(String[] args) {
        PersonalExpenseManager manager = new PersonalExpenseManager();
        manager.run();
    }

    public void run() {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Personal Expense Manager ===");

            int count = readExpenseCount(scanner);
            List<Expense> expenses = recordExpenses(scanner, count);

            generateSummary(expenses);

        } catch (Exception e) {
            System.out.println("Application Error: " + e.getMessage());
        }
    }

    private int readExpenseCount(Scanner scanner) {

        int count;

        while (true) {
            try {
                System.out.print("Enter number of expenses: ");
                count = scanner.nextInt();

                if (count <= 0)
                    throw new IllegalArgumentException("Must be positive.");

                return count;

            } catch (InputMismatchException e) {
                System.out.println("Enter valid number.");
                scanner.nextLine();
            }
        }
    }

    private List<Expense> recordExpenses(Scanner scanner, int count) {

        List<Expense> expenses = new ArrayList<>();

        for (int i = 1; i <= count; i++) {

            System.out.println("\nExpense " + i);

            System.out.print("Category: ");
            scanner.nextLine();
            String category = scanner.nextLine();

            double amount = readAmount(scanner);

            expenses.add(new Expense(category, amount));
        }

        return expenses;
    }

    private double readAmount(Scanner scanner) {

        double amount;

        while (true) {
            try {
                System.out.print("Amount: ");
                amount = scanner.nextDouble();

                if (amount <= 0)
                    throw new IllegalArgumentException("Must be positive.");

                return amount;

            } catch (InputMismatchException e) {
                System.out.println("Enter numeric value.");
                scanner.nextLine();
            }
        }
    }

    private void generateSummary(List<Expense> expenses) {

        System.out.println("\n===== Expense Summary =====");

        double total = 0;

        for (Expense e : expenses) {

            System.out.printf(
                    "Category: %-12s Amount: %.2f%n",
                    e.getCategory(),
                    e.getAmount()
            );

            total += e.getAmount();
        }

        System.out.printf("\nTotal Spending: %.2f%n", total);
        System.out.println("===========================");
    }
}

class Expense {

    private String category;
    private double amount;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

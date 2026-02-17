import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Inventory Tracking System
 * Manages product stock and availability
 */
public class InventoryTrackingSystem {

    public static void main(String[] args) {
        InventoryTrackingSystem system = new InventoryTrackingSystem();
        system.start();
    }

    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("=== Inventory Tracking System ===");

            int count = readProductCount(scanner);
            List<Product> products = readProducts(scanner, count);

            displayInventory(products);

        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
        }
    }

    private int readProductCount(Scanner scanner) {

        int count;

        while (true) {
            try {
                System.out.print("Enter number of products: ");
                count = scanner.nextInt();

                if (count <= 0)
                    throw new IllegalArgumentException("Must be positive.");

                return count;

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid integer.");
                scanner.nextLine();
            }
        }
    }

    private List<Product> readProducts(Scanner scanner, int count) {

        List<Product> products = new ArrayList<>();

        for (int i = 1; i <= count; i++) {

            System.out.println("\nProduct " + i);

            System.out.print("Name: ");
            scanner.nextLine();
            String name = scanner.nextLine();

            int quantity = readQuantity(scanner);

            products.add(new Product(name, quantity));
        }

        return products;
    }

    private int readQuantity(Scanner scanner) {

        int qty;

        while (true) {
            try {
                System.out.print("Quantity: ");
                qty = scanner.nextInt();

                if (qty < 0)
                    throw new IllegalArgumentException("Cannot be negative.");

                return qty;

            } catch (InputMismatchException e) {
                System.out.println("Enter numeric value.");
                scanner.nextLine();
            }
        }
    }

    private void displayInventory(List<Product> products) {

        System.out.println("\n===== Inventory Report =====");

        for (Product p : products) {

            String status = (p.getQuantity() < 5)
                    ? "Low Stock"
                    : "Available";

            System.out.printf(
                    "Product: %-15s Qty: %-3d Status: %s%n",
                    p.getName(),
                    p.getQuantity(),
                    status
            );
        }

        System.out.println("============================");
    }
}

class Product {

    private String name;
    private int quantity;

    public Product(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

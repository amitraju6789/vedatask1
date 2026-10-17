package task17;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1. Product Model Class
class Product {
    private String productId;
    private String name;
    private int quantity;
    private double price;

    public Product(String productId, String name, int quantity, double price) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price) { this.price = price; }

    public void displayProduct() {
        System.out.printf("| %-10s | %-20s | %-10d | $%-10.2f |\n", productId, name, quantity, price);
    }
}

// 2. Inventory System Manager Class
class InventoryManager {
    private List<Product> inventory = new ArrayList<>();

    // Reusable helper method to search by ID
    public Product findById(String productId) {
        for (Product product : inventory) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }
        return null;
    }

    // 1. Add Product
    public boolean addProduct(Product product) {
        if (findById(product.getProductId()) != null) {
            return false; // Unique Product ID constraint
        }
        inventory.add(product);
        return true;
    }

    // 2. Remove Product by ID
    public boolean removeProduct(String productId) {
        Product p = findById(productId);
        if (p != null) {
            inventory.remove(p);
            return true;
        }
        return false;
    }

    // 3. Search Products by Name (Partial/Case-insensitive match)
    public List<Product> searchByName(String nameQuery) {
        List<Product> results = new ArrayList<>();
        for (Product p : inventory) {
            if (p.getName().toLowerCase().contains(nameQuery.toLowerCase())) {
                results.add(p);
            }
        }
        return results;
    }

    // 4. Update Product Stock and Price
    public boolean updateProduct(String productId, int newQuantity, double newPrice) {
        Product p = findById(productId);
        if (p != null) {
            p.setQuantity(newQuantity);
            p.setPrice(newPrice);
            return true;
        }
        return false;
    }

    // 5. Display All Products
    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("\n[INFO] Inventory is empty.");
            return;
        }
        System.out.println("\n---------------------------------------------------------------+");
        System.out.printf("| %-10s | %-20s | %-10s | %-11s |\n", "Product ID", "Name", "Quantity", "Price");
        System.out.println("---------------------------------------------------------------+");
        for (Product p : inventory) {
            p.displayProduct();
        }
        System.out.println("---------------------------------------------------------------+");
    }
}

public class InventorySystemDemo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InventoryManager manager = new InventoryManager();

        // Seed Initial Mock Inventory Data
        manager.addProduct(new Product("P101", "Gaming Laptop", 15, 1250.00));
        manager.addProduct(new Product("P102", "Wireless Mouse", 50, 25.50));
        manager.addProduct(new Product("P103", "Mechanical Keyboard", 30, 75.00));

        boolean running = true;

        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      PRODUCT INVENTORY MANAGEMENT       ");
            System.out.println("=========================================");
            System.out.println("1. Add New Product");
            System.out.println("2. Remove Product by ID");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Search Products by Name");
            System.out.println("5. Update Stock & Price");
            System.out.println("6. Display All Inventory");
            System.out.println("7. Exit");
            System.out.print("Select an option (1-7): ");

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        String id = scanner.nextLine();
                        if (manager.findById(id) != null) {
                            System.out.println("[ERROR] Product ID already exists!");
                            break;
                        }
                        System.out.print("Enter Product Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Quantity: ");
                        int qty = scanner.nextInt();
                        System.out.print("Enter Price: ");
                        double price = scanner.nextDouble();

                        if (manager.addProduct(new Product(id, name, qty, price))) {
                            System.out.println("[SUCCESS] Product added to inventory.");
                        }
                        break;

                    case 2:
                        System.out.print("Enter Product ID to Remove: ");
                        String removeId = scanner.nextLine();
                        if (manager.removeProduct(removeId)) {
                            System.out.println("[SUCCESS] Product removed successfully.");
                        } else {
                            System.out.println("[ERROR] Product ID not found.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to Search: ");
                        String searchId = scanner.nextLine();
                        Product found = manager.findById(searchId);
                        if (found != null) {
                            System.out.println("\n[PRODUCT FOUND]");
                            System.out.println("---------------------------------------------------------------+");
                            System.out.printf("| %-10s | %-20s | %-10s | %-11s |\n", "Product ID", "Name", "Quantity", "Price");
                            System.out.println("---------------------------------------------------------------+");
                            found.displayProduct();
                            System.out.println("---------------------------------------------------------------+");
                        } else {
                            System.out.println("[NOT FOUND] No product matching ID: " + searchId);
                        }
                        break;

                    case 4:
                        System.out.print("Enter Name Query: ");
                        String query = scanner.nextLine();
                        List<Product> results = manager.searchByName(query);
                        if (!results.isEmpty()) {
                            System.out.println("\n[SEARCH RESULTS]");
                            System.out.println("---------------------------------------------------------------+");
                            System.out.printf("| %-10s | %-20s | %-10s | %-11s |\n", "Product ID", "Name", "Quantity", "Price");
                            System.out.println("---------------------------------------------------------------+");
                            for (Product p : results) {
                                p.displayProduct();
                            }
                            System.out.println("---------------------------------------------------------------+");
                        } else {
                            System.out.println("[NOT FOUND] No products matching term: " + query);
                        }
                        break;

                    case 5:
                        System.out.print("Enter Product ID to Update: ");
                        String updateId = scanner.nextLine();
                        if (manager.findById(updateId) == null) {
                            System.out.println("[ERROR] Product ID not found.");
                            break;
                        }
                        System.out.print("Enter New Quantity: ");
                        int newQty = scanner.nextInt();
                        System.out.print("Enter New Price: ");
                        double newPrice = scanner.nextDouble();

                        if (manager.updateProduct(updateId, newQty, newPrice)) {
                            System.out.println("[SUCCESS] Product updated successfully.");
                        }
                        break;

                    case 6:
                        manager.displayAllProducts();
                        break;

                    case 7:
                        System.out.println("\nExiting Inventory System. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid option! Please enter a number between 1 and 7.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid menu number.");
                scanner.next();
            }
        }

        scanner.close();
    }
}
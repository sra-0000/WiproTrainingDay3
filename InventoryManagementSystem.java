package wiprotrainingday2;


	import java.util.*;

	// Product Class
	class Product {
	    private int id;
	    private String name;
	    private int stock;
	    private double price;

	    public Product(int id, String name, int stock, double price) {
	        this.id = id;
	        this.name = name;
	        this.stock = stock;
	        this.price = price;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public int getStock() {
	        return stock;
	    }

	    public void updateStock(int quantity) {
	        this.stock += quantity;
	    }

	    public void setStock(int stock) {
	        this.stock = stock;
	    }

	    public double getPrice() {
	        return price;
	    }

	    @Override
	    public String toString() {
	        return "Product[ID: " + id + ", Name: " + name + ", Stock: " + stock + ", Price: $" + price + "]";
	    }
	}

	// Supplier Interface
	interface SupplierOperations {
	    void addSupplier(int id, String name, String contact);
	    void displaySuppliers();
	}

	// Supplier Class
	class Supplier {
	    private int id;
	    private String name;
	    private String contact;

	    public Supplier(int id, String name, String contact) {
	        this.id = id;
	        this.name = name;
	        this.contact = contact;
	    }

	    @Override
	    public String toString() {
	        return "Supplier[ID: " + id + ", Name: " + name + ", Contact: " + contact + "]";
	    }
	}

	// Inventory Class implementing SupplierOperations
	class Inventory implements SupplierOperations {
	    private Map<Integer, Product> products = new HashMap<>();
	    private Map<Integer, Supplier> suppliers = new HashMap<>();
	    private static final int LOW_STOCK_THRESHOLD = 10;

	    // Add a new product
	    public void addProduct(int id, String name, int stock, double price) {
	        if (products.containsKey(id)) {
	            System.out.println("Product ID already exists.");
	            return;
	        }
	        products.put(id, new Product(id, name, stock, price));
	        System.out.println("Product added successfully.");
	    }

	    // Update product stock
	    public void updateProductStock(int id, int quantity) {
	        Product product = products.get(id);
	        if (product == null) {
	            System.out.println("Product not found.");
	            return;
	        }
	        product.updateStock(quantity);
	        System.out.println("Product stock updated.");
	    }

	    // Remove a product
	    public void removeProduct(int id) {
	        if (products.remove(id) != null) {
	            System.out.println("Product removed successfully.");
	        } else {
	            System.out.println("Product not found.");
	        }
	    }

	    // Display low-stock alerts
	    public void lowStockAlerts() {
	        System.out.println("Low Stock Alerts:");
	        boolean hasLowStock = false;
	        for (Product product : products.values()) {
	            if (product.getStock() < LOW_STOCK_THRESHOLD) {
	                System.out.println(product);
	                hasLowStock = true;
	            }
	        }
	        if (!hasLowStock) {
	            System.out.println("All products are sufficiently stocked.");
	        }
	    }

	    // Display all products
	    public void displayProducts() {
	        if (products.isEmpty()) {
	            System.out.println("No products in inventory.");
	        } else {
	            System.out.println("Inventory:");
	            for (Product product : products.values()) {
	                System.out.println(product);
	            }
	        }
	    }

	    // Supplier operations
	    @Override
	    public void addSupplier(int id, String name, String contact) {
	        if (suppliers.containsKey(id)) {
	            System.out.println("Supplier ID already exists.");
	            return;
	        }
	        suppliers.put(id, new Supplier(id, name, contact));
	        System.out.println("Supplier added successfully.");
	    }

	    @Override
	    public void displaySuppliers() {
	        if (suppliers.isEmpty()) {
	            System.out.println("No suppliers available.");
	        } else {
	            System.out.println("Suppliers:");
	            for (Supplier supplier : suppliers.values()) {
	                System.out.println(supplier);
	            }
	        }
	    }
	}

	// Main Class
	public class InventoryManagementSystem {
	    public static void main(String[] args) {
	        Inventory inventory = new Inventory();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\nInventory Management Menu:");
	            System.out.println("1. Add Product");
	            System.out.println("2. Update Product Stock");
	            System.out.println("3. Remove Product");
	            System.out.println("4. Display Products");
	            System.out.println("5. Low Stock Alerts");
	            System.out.println("6. Add Supplier");
	            System.out.println("7. Display Suppliers");
	            System.out.println("8. Exit");
	            System.out.print("Choose an option: ");

	            int choice = scanner.nextInt();
	            switch (choice) {
	                case 1:
	                    System.out.print("Enter product ID: ");
	                    int productId = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter product name: ");
	                    String productName = scanner.nextLine();
	                    System.out.print("Enter product stock: ");
	                    int stock = scanner.nextInt();
	                    System.out.print("Enter product price: ");
	                    double price = scanner.nextDouble();
	                    inventory.addProduct(productId, productName, stock, price);
	                    break;

	                case 2:
	                    System.out.print("Enter product ID: ");
	                    int updateId = scanner.nextInt();
	                    System.out.print("Enter stock quantity to add or subtract: ");
	                    int quantity = scanner.nextInt();
	                    inventory.updateProductStock(updateId, quantity);
	                    break;

	                case 3:
	                    System.out.print("Enter product ID: ");
	                    int removeId = scanner.nextInt();
	                    inventory.removeProduct(removeId);
	                    break;

	                case 4:
	                    inventory.displayProducts();
	                    break;

	                case 5:
	                    inventory.lowStockAlerts();
	                    break;

	                case 6:
	                    System.out.print("Enter supplier ID: ");
	                    int supplierId = scanner.nextInt();
	                    scanner.nextLine(); // Consume newline
	                    System.out.print("Enter supplier name: ");
	                    String supplierName = scanner.nextLine();
	                    System.out.print("Enter supplier contact: ");
	                    String supplierContact = scanner.nextLine();
	                    inventory.addSupplier(supplierId, supplierName, supplierContact);
	                    break;

	                case 7:
	                    inventory.displaySuppliers();
	                    break;

	                case 8:
	                    System.out.println("Exiting...");
	                    scanner.close();
	                    return;

	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    }
	}



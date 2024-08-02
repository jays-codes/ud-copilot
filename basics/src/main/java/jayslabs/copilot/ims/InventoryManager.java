package jayslabs.copilot.ims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Inventory manager class that uses a map with product ID as key,
 * and provides methods to add, remove, list, and update products.
 */
public class InventoryManager {

    // Map to store products
    private Map<Integer, Product> products;

    // Constructor to initialize the map
    public InventoryManager() {
        this.products = new HashMap<>();
    }

    /**
     * Adds a product to the inventory.
     *
     * @param product the product to add
     * @return true if the product was added, false if the product already exists
     * @throws IllegalArgumentException if the product is null
     */
    public boolean addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (products.containsKey(product.getId())) {
            return false;
        }
        products.put(product.getId(), product);
        return true;
    }

    /**
     * Removes a product from the inventory.
     *
     * @param id the id of the product to remove
     * @return true if the product was removed, false if the product does not exist
     */
    public boolean removeProduct(int id) {
        if (!products.containsKey(id)) {
            return false;
        }
        products.remove(id);
        return true;
    }

    /**
     * Lists all products in the inventory.
     *
     * @return a list of all products
     */
    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * Updates the quantity of a product in the inventory.
     *
     * @param id the id of the product to update
     * @param quantity the new quantity of the product
     * @return true if the product was updated, false if the product does not exist
     */
    public boolean updateProductQuantity(int id, int quantity) {
        return products.computeIfPresent(id, (key, product) -> {
            product.setQuantity(quantity);
            return product;
        }) != null;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id the id of the product to retrieve
     * @return an Optional containing the product if found, or an empty Optional if not found
     */
    public Optional<Product> getProductById(int id) {
        return Optional.ofNullable(products.get(id));
    }

    /**
     * Main method to test the InventoryManager class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        try {
            InventoryManager inventoryManager = new InventoryManager();

            Product product1 = new Product(1, "Product 1", 10);
            Product product2 = new Product(2, "Product 2", 20);

            inventoryManager.addProduct(product1);
            inventoryManager.addProduct(product2);

            System.out.println("Products in inventory:");
            inventoryManager.listProducts().forEach(System.out::println);

            inventoryManager.updateProductQuantity(1, 15);

            System.out.println("\nProduct 1 after updating quantity:");
            inventoryManager.getProductById(1).ifPresent(System.out::println);

            inventoryManager.removeProduct(2);

            System.out.println("\nProducts in inventory after removing product 2:");
            inventoryManager.listProducts().forEach(System.out::println);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
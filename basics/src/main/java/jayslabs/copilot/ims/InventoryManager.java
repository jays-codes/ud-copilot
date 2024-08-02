package jayslabs.copilot.ims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//inventory manager class that uses a map and id as key, and adds, removes, lists and updates a product
public class InventoryManager {

    //map to store products
    private Map<Integer, Product> products;

    //constructor to initialize the map
    public InventoryManager() {
        this.products = new HashMap<>();
    }

    //method to add a product to the inventory
    public boolean addProduct(Product product) {
        if (products.containsKey(product.getId())) {
            return false;
        }
        products.put(product.getId(), product);
        return true;
    }

    //method to remove a product from the inventory
    public boolean removeProduct(int id) {
        if (!products.containsKey(id)) {
            return false;
        }
        products.remove(id);
        return true;
    }

    //method to list all products in the inventory
    public List<Product> listProducts() {
        return new ArrayList<>(products.values());
    }

    //method to update the quantity of a product in the inventory
    public boolean updateProductQuantity(int id, int quantity) {
        if (!products.containsKey(id)) {
            return false;
        }
        Product product = products.get(id);
        product.setQuantity(quantity);
        return true;
    }

    //method to get a product by id
    public Product getProductById(int id) {
        return products.get(id);
    }

    //main method to test the inventory manager
    public static void main(String[] args) {
        InventoryManager inventoryManager = new InventoryManager();

        // Add products to the inventory
        inventoryManager.addProduct(new Product(1, "Product 1", 10));
        inventoryManager.addProduct(new Product(2, "Product 2", 20));
        inventoryManager.addProduct(new Product(3, "Product 3", 30));

        // List all products in the inventory
        List<Product> products = inventoryManager.listProducts();
        for (Product product : products) {
            System.out.println(product);
        }

        // Update the quantity of a product
        inventoryManager.updateProductQuantity(1, 15);

        // Get a product by id
        Product product = inventoryManager.getProductById(1);
        System.out.println("Product 1: " + product);

        // Remove a product from the inventory
        inventoryManager.removeProduct(2);

        // List all products in the inventory
        products = inventoryManager.listProducts();
        for (Product p : products) {
            System.out.println(p);
        }
    }









    
}

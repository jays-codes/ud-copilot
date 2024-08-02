package jayslabs.copilot.ims;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryManagerTest {
    private InventoryManager inventoryManager;

    @BeforeEach
    public void setUp() {
        inventoryManager = new InventoryManager();
    }

    @Test
    public void testAddProduct() {
        Product product = new Product(1, "Product 1", 10);
        assertTrue(inventoryManager.addProduct(product));
        List<Product> products = inventoryManager.listProducts();
        assertEquals(1, products.size());
        assertEquals("Product 1", products.get(0).getName());
    }

    @Test
    public void testAddDuplicateProduct() {
        Product product1 = new Product(1, "Product 1", 10);
        Product product2 = new Product(1, "Product 1", 10);
        assertTrue(inventoryManager.addProduct(product1));
        assertFalse(inventoryManager.addProduct(product2));
        List<Product> products = inventoryManager.listProducts();
        assertEquals(1, products.size());
    }

    @Test
    public void testRemoveProduct() {
        Product product = new Product(1, "Product 1", 10);
        inventoryManager.addProduct(product);
        assertTrue(inventoryManager.removeProduct(1));
        List<Product> products = inventoryManager.listProducts();
        assertEquals(0, products.size());
    }

    @Test
    public void testRemoveNonExistentProduct() {
        assertFalse(inventoryManager.removeProduct(1));
    }

    @Test
    public void testListProducts() {
        Product product1 = new Product(1, "Product 1", 10);
        Product product2 = new Product(2, "Product 2", 20);
        inventoryManager.addProduct(product1);
        inventoryManager.addProduct(product2);
        List<Product> products = inventoryManager.listProducts();
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Product 2", products.get(1).getName());
    }

    @Test
    public void testUpdateProductQuantity() {
        Product product = new Product(1, "Product 1", 10);
        inventoryManager.addProduct(product);
        assertTrue(inventoryManager.updateProductQuantity(1, 15));
        Optional<Product> updatedProduct = inventoryManager.getProductById(1);
        assertTrue(updatedProduct.isPresent());
        assertEquals(15, updatedProduct.get().getQuantity());
    }

    @Test
    public void testUpdateNonExistentProductQuantity() {
        assertFalse(inventoryManager.updateProductQuantity(1, 15));
    }

    @Test
    public void testGetProductById() {
        Product product = new Product(1, "Product 1", 10);
        inventoryManager.addProduct(product);
        Optional<Product> retrievedProduct = inventoryManager.getProductById(1);
        assertTrue(retrievedProduct.isPresent());
        assertEquals("Product 1", retrievedProduct.get().getName());
    }

    @Test
    public void testGetNonExistentProductById() {
        Optional<Product> retrievedProduct = inventoryManager.getProductById(1);
        assertFalse(retrievedProduct.isPresent());
    }
}
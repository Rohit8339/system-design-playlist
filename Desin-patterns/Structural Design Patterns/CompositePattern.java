import java.util.ArrayList;
import java.util.List;

// =====================================================
// PROBLEM CODE (WITHOUT COMPOSITE PATTERN)
// =====================================================

/*
Problem:

Cart can contain:

1. Individual Products
2. Product Bundles

Since Product and ProductBundle are different types,
we need instanceof checks while calculating price.

If a new type is added later, this logic must be modified.
*/

// Individual Product
class Product {

    private int productPrice;
    private String productName;

    Product(int productPrice, String productName) {
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }
}

// Product Bundle
class ProductBundle {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getPrice() {

        int total = 0;

        for (Product product : products) {
            total += product.getProductPrice();
        }

        return total;
    }
}

/*
 * Problem
 * 
 * for(Object item : cart){
 * 
 * if(item instanceof Product){
 * ...
 * }
 * 
 * else if(item instanceof ProductBundle){
 * ...
 * }
 * }
 * 
 * Problems
 * 
 * 1. Uses instanceof checks
 * 2. Difficult to extend
 * 3. Cart knows too much about object types
 */

// =====================================================
// SOLUTION CODE (COMPOSITE PATTERN)
// =====================================================

/*
 * Solution:
 * 
 * Treat Product and ProductBundle uniformly.
 * 
 * Both implement the same interface.
 * 
 * Now the cart doesn't care whether it is
 * a single product or a bundle.
 */

// Common component
interface CartItem {

    int getPrice();
}

// Leaf Object
class ProductItem implements CartItem {

    private int productPrice;
    private String productName;

    ProductItem(int productPrice, String productName) {
        this.productPrice = productPrice;
        this.productName = productName;
    }

    @Override
    public int getPrice() {
        return productPrice;
    }
}

// Composite Object
class Bundle implements CartItem {

    private String bundleName;

    private List<CartItem> items = new ArrayList<>();

    Bundle(String bundleName) {
        this.bundleName = bundleName;
    }

    // Add product or another bundle
    public void addItem(CartItem item) {
        items.add(item);
    }

    @Override
    public int getPrice() {

        int total = 0;

        for (CartItem item : items) {
            total += item.getPrice();
        }

        return total;
    }
}

public class CompositePattern {

    public static void main(String[] args) {

        ProductItem iphone = new ProductItem(1000, "IPhone");

        ProductItem charger = new ProductItem(120, "Charger");

        ProductItem headphones = new ProductItem(100, "Headphones");

        ProductItem pen = new ProductItem(10, "Pen");

        ProductItem pencil = new ProductItem(12, "Pencil");

        // Bundle 1
        Bundle iphoneCombo = new Bundle("IPhone Combo");

        iphoneCombo.addItem(iphone);
        iphoneCombo.addItem(charger);
        iphoneCombo.addItem(headphones);

        // Bundle 2
        Bundle schoolCombo = new Bundle("School Combo");

        schoolCombo.addItem(pen);
        schoolCombo.addItem(pencil);

        // Cart can contain anything
        List<CartItem> cart = new ArrayList<>();

        cart.add(iphoneCombo);
        cart.add(schoolCombo);

        int totalAmount = 0;

        // No instanceof checks required
        for (CartItem item : cart) {
            totalAmount += item.getPrice();
        }

        System.out.println(totalAmount);
    }
}

/*
 * OUTPUT
 * 
 * 1242
 * 
 * 
 * CLASS DIAGRAM
 * 
 * 
 * CartItem
 * ^
 * |
 * +--------+--------+
 * | |
 * | |
 * ProductItem Bundle
 * |
 * |
 * List<CartItem>
 * 
 * 
 * FLOW
 * 
 * Cart
 * |
 * +--> ProductItem
 * |
 * +--> Bundle
 * |
 * +--> ProductItem
 * |
 * +--> ProductItem
 * 
 * 
 * BENEFITS
 * 
 * 1. No instanceof checks
 * 2. Treat single objects and groups uniformly
 * 3. Easy to extend
 * 4. Cleaner client code
 */
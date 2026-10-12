import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<CartItem> items = new ArrayList<>();

    // method for finding item from cart by productId, agar item mil jata h to return krdo otherwise null
    private CartItem findItem(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getProductId() == productId) {
                return item;
            }
        }
        return null;
    }

    public void addProduct(Product product, int qty) throws ProductOutOfStockException {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity 0 ya negative nahi ho sakti.");
        }
        if (product.getStock() == 0) {
            throw new ProductOutOfStockException(product.getName() + " out of stock hai.");
        }

        CartItem existing = findItem(product.getProductId());
        if (existing != null) {
            int newQty = existing.getQuantity() + qty;
            if (newQty > product.getStock()) {
                throw new ProductOutOfStockException(
                    "Sirf " + product.getStock() + " units available hain.");
            }
            existing.setQuantity(newQty);
        } else {
            if (qty > product.getStock()) {
                throw new ProductOutOfStockException(
                    "Sirf " + product.getStock() + " units available hain.");
            }
            items.add(new CartItem(product, qty));
        }
        System.out.println("Cart mein add ho gaya.");
    }

    public void removeProduct(int productId) {
        CartItem item = findItem(productId);
        if (item == null) {
            System.out.println("Ye product cart mein nahi hai.");
            return;
        }
        items.remove(item);
        System.out.println("Product cart se hata diya.");
    }

    public void updateQuantity(int productId, int newQty) throws ProductOutOfStockException {
        if (newQty <= 0) {
            throw new IllegalArgumentException("Quantity 0 ya negative nahi ho sakti.");
        }
        CartItem item = findItem(productId);
        if (item == null) {
            System.out.println("Ye product cart mein nahi hai.");
            return;
        }
        if (newQty > item.getProduct().getStock()) {
            throw new ProductOutOfStockException(
                "Sirf " + item.getProduct().getStock() + " units available hain.");
        }
        item.setQuantity(newQty);
        System.out.println("Quantity update ho gayi.");
    }

    public void viewCart() {
        if (items.isEmpty()) {
            System.out.println("Cart khali hai.");
            return;
        }
        System.out.println("---- Your Cart ----");
        for (CartItem item : items) {
            System.out.printf("ID: %d | %s | Price: %.2f | Qty: %d | Subtotal: %.2f%n",
                item.getProduct().getProductId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                item.getSubtotal());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (CartItem item : items) {
            total = total + item.getSubtotal();
        }
        return total;
    }

    public void checkout() {
        if (items.isEmpty()) {
            System.out.println("Cart khali hai, checkout nahi ho sakta.");
            return;
        }
        double total = calculateTotal();
        for (CartItem item : items) {
            item.getProduct().reduceStock(item.getQuantity());
        }
        items.clear();
        System.out.printf("Checkout successful! Total paid: %.2f%n", total);
    }
}
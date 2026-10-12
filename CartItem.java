public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getSubtotal() {  // ek product ka total price calculate krne k liye , quantity * price
        return product.getPrice() * quantity; //Product class mei bnaya h getPrice() method , (getter)
    }
}
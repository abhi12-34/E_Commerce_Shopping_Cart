public class Customer {
    private int customerId;
    private String name;
    private ShoppingCart cart;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.cart = new ShoppingCart();
    }

    public int getCustomerId() { return customerId; }
    public String getName() { return name; }
    public ShoppingCart getCart() { return cart; }
}
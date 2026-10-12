import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Product findProduct(List<Product> products, int id) {
        for (Product p : products) {
            if (p.getProductId() == id) {
                return p;
            }
        }
        return null;
    }

    static void viewProducts(List<Product> products) {
        System.out.println("---- Products ----");
        for (Product p : products) {
            System.out.printf("ID: %d | %s | %s | Price: %.2f | Stock: %d%n",
                p.getProductId(), p.getName(), p.getCategory(),
                p.getPrice(), p.getStock());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "Laptop", "Electronics", 55000, 5));
        products.add(new Product(2, "Mouse", "Electronics", 500, 20));
        products.add(new Product(3, "Notebook", "Stationery", 60, 100));
        products.add(new Product(4, "Pen", "Stationery", 10, 0));

        System.out.print("Apna naam batao: ");
        String name = sc.nextLine();
        Customer customer = new Customer(101, name);
        ShoppingCart cart = customer.getCart();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. View Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. Remove Product from Cart");
            System.out.println("4. Update Quantity");
            System.out.println("5. View Cart");
            System.out.println("6. Calculate Total");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        viewProducts(products);
                        break;
                    case 2: {
                        System.out.print("Product ID: ");
                        int id = sc.nextInt();
                        Product p = findProduct(products, id);
                        if (p == null) {
                            System.out.println("Product nahi mila.");
                            break;
                        }
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        cart.addProduct(p, qty);
                        break;
                    }
                    case 3:
                        System.out.print("Product ID: ");
                        cart.removeProduct(sc.nextInt());
                        break;
                    case 4:
                        System.out.print("Product ID: ");
                        int uid = sc.nextInt();
                        System.out.print("New Quantity: ");
                        int newQty = sc.nextInt();
                        cart.updateQuantity(uid, newQty);
                        break;
                    case 5:
                        cart.viewCart();
                        break;
                    case 6:
                        System.out.printf("Total: %.2f%n", cart.calculateTotal());
                        break;
                    case 7:
                        cart.checkout();
                        break;
                    case 8:
                        System.out.println("Thank you, " + customer.getName() + "!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Galat choice.");
                }
            } catch (ProductOutOfStockException e) {
                System.out.println("Stock Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Number daalo, text nahi.");
                sc.nextLine();
            }
        }
    }
}
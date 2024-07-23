package final_project;

import java.util.ArrayList;

public class Cart{
 private ArrayList<Product> cart;

    public Cart() {
        this.cart = new ArrayList<Product>();
    }

    // Method to clear the cart
    public void clearCart() {
        cart.clear();
        System.out.println("Cart has been cleared.");
    }

    public void addToCart(Product product) {
        cart.add(product);
        System.out.println(product.getName() + " has been added to the cart.");
    }

    public void removeFromCart(String prodName) {
        boolean removed = false;
        for (Product product : cart) {
            if (product.getName().equalsIgnoreCase(prodName)) {
                cart.remove(product);
                System.out.println(product.getName() + " has been removed from the cart.");
                removed = true;
                break;
            }
        }
        if (!removed) {
            System.out.println("Product not found. No product removed.");
        }
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("The cart is empty.");
        } else {
            System.out.println("Cart contains:");
            for (Product product : cart) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
    }
}
package final_project;

import java.util.ArrayList;

public class Cart{
 private ArrayList<Product> cart;

    public Cart() {
        this.cart = new ArrayList<Product>();
    }
     public Cart(String cartString) {
        this.cart = new ArrayList<Product>();
        if (cartString != null && !cartString.isEmpty()) {
            String[] items = cartString.split(",");
            for (String item : items) {
                String[] details = item.split("-");
                if (details.length == 2) {
                    try {
                        cart.add(new Product(details[0], Double.parseDouble(details[1])));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart has been cleared.");
    }

    public void addToCart(String prodName, Database db) {
        Product product=db.findProduct(prodName);
        if (!product.getName().equals("")){
            cart.add(product);
            System.out.println(product.getName() + " has been added to the cart.");
        }else{
            System.out.println(product.getName() + " can't be added to cart");
        }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product product : cart) {
            sb.append(product.toString()).append(",");
        }
        return sb.toString();
    }
}
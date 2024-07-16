package final_project;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class Menu{
    //private ArrayList<Product> menu;
    private Database db;
        public Menu(Database db) {
        this.db = db;
    }
     public void viewMenu() {
        ArrayList<Product> menu = db.readProductsFromFile("products.txt");
        if (menu.isEmpty()) {
            System.out.println("The menu is empty.");
        } else {
            System.out.println("Menu:");
            for (Product product : menu) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
    }
    public void viewMenuPriceSorted() {
        ArrayList<Product> menu = db.readProductsFromFile("products.txt");
        if (menu.isEmpty()) {
            System.out.println("The menu is empty.");
        } else {
            Collections.sort(menu, new Comparator<Product>() {
                @Override
                public int compare(Product p1, Product p2) {
                    return Double.compare(p2.getPrice(), p1.getPrice());
                }
            });

            System.out.println("Menu:");
            for (Product product : menu) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
    }

}
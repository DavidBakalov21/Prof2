package final_project;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("fgd");
        Database db = new Database();
        Menu menu = new Menu(db);
        menu.viewMenu();

        Client me = Database.logIn("password123", "john@example.com");
        me.getCart().viewCart();
    }
}
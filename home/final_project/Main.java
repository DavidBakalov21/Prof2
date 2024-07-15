package final_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("");
        Database db = new Database();
        Menu menu = new Menu(db);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please login or Resister");
        String entranceChaoice = scanner.nextLine();
        Client me;
        switch (entranceChaoice) {
            case "login":
                System.out.print("Enter email: ");
                String emailLogin = scanner.nextLine();
                System.out.print("Enter password: ");
                String passwordLogin = scanner.nextLine();
                me= db.logIn(passwordLogin, emailLogin);
                break;
            case "register":
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String passwordRegister = scanner.nextLine();
                System.out.print("Enter email: ");
                String emailRegister = scanner.nextLine();
                System.out.println("Are you admin");
                String isAdmin = scanner.nextLine();
                System.out.println("Enter your paymentMethod");
                String paymentMethod = scanner.nextLine();
                me=db.register(username, passwordRegister, emailRegister, Boolean.parseBoolean(isAdmin),paymentMethod);
                break;
        }
        /*
        menu.viewMenu();

        Client me = Database.logIn("password123", "john@example.com");
        me.getCart().viewCart();

        me.getCart().addToCart(new Product("baNana", 5.0));
        db.save(me);*/
    }
}
package final_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static Client entrance(Scanner scanner){
        System.out.println("Please login or resister");
        String entranceChaoice = scanner.nextLine();
        Client returnClient=null;
        switch (entranceChaoice) {
            case "login":
                System.out.print("Enter email: ");
                String emailLogin = scanner.nextLine();
                System.out.print("Enter password: ");
                String passwordLogin = scanner.nextLine();
                returnClient=db.logIn(passwordLogin, emailLogin);
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
                returnClient=db.register(username, passwordRegister, emailRegister, Boolean.parseBoolean(isAdmin),paymentMethod);
                break;
        }
        return returnClient;
    }
    public static void choiceMake(Scanner scanner, Client me, Database db, String choice ){
        switch (choice){
            case "addToCart":
                System.out.println("What do you want?");
                String Product = scanner.nextLine();
                me.getCart().addToCart(Product);
                break;
            case "clearCart":
                me.getCart().clearCart();
                break;
            case "viewCart":
                me.getCart().viewCart();

        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("");
        Database db = new Database();
        Menu menu = new Menu(db);
        Scanner scanner = new Scanner(System.in);
        
        Client me=null;
        while (Client==null){
            me= entrance(scanner);
        }

        System.out.println("Here is the menu");
        menu.viewMenu();

        while(true){
            System.out.println("Fill your cart with products");
            String choice = scanner.nextLine();
            choiceMake(scanner, me, db, choice);

        }
     //   me.getCart().addToCart(new Product("baNana", 5.0));
        db.save(me);
        /*
        

        Client me = Database.logIn("password123", "john@example.com");
        me.getCart().viewCart();

        */
    }
}
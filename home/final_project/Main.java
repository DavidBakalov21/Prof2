package final_project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static Client entrance(Scanner scanner, Database db){
        System.out.println("Please login or register");
        String entranceChaoice = scanner.nextLine();
        Client returnClient=null;
        try {
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
        }catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return returnClient;
    }
    public static Boolean choiceMake(Scanner scanner, Client me, Database db, String choice, Menu menu ){
        switch (choice){
            case "addToCart":
                System.out.println("What do you want?");
                String productAdd = scanner.nextLine();
                me.getCart().addToCart(productAdd, db);
                db.save(me);
                return false;
            case "clearCart": //OK
                me.getCart().clearCart();
                db.save(me);
                return false;
                
            case "viewCart": //OK
                me.getCart().viewCart();
                return false;
                
            case "removeProduct"://OK
                System.out.println("What do you want to remove?");
                String productRemove = scanner.nextLine();
                me.getCart().removeFromCart(productRemove);
                db.save(me);
                return false;
                
            case "editProfile":
                System.out.println("Enter new name:");
                String newName=scanner.nextLine();
                System.out.println("Enter new password:");
                String newPassword=scanner.nextLine();
                me.editName(newName);
                me.editPassword(newPassword);
                db.save(me);
                return false;
                
            case "viewMenu"://OK
                menu.viewMenu();
                return false;
                
            case "viewSortedMenu"://OK
                menu.viewMenuPriceSorted();
                return false;
            case "setPayment":
                System.out.println("Enter new payment method:");
                String newPayment=scanner.nextLine();
                me.getPayment().setPaymentMethod(newPayment);
                
            case "logout":
                me=null;
                return true;
            case "help":
                System.out.println("viewMenu-view menu");
                System.out.println("viewSortedMenu-view menu sorted by prices");
                System.out.println("editProfile-edit user's name and password");
                System.out.println("removeProduct-remove product from cart");
                System.out.println("viewCart-view cart");
                System.out.println("clearCart-clear cart");
                System.out.println("addToCart-add product to cart");
                System.out.println("logout-logout cart");
            default:
                System.out.println("No such command, use 'help' command");
                
            return false;

        }
    }
    public static void main(String[] args) {
        Database db = new Database();
        Menu menu = new Menu(db);
        Scanner scanner = new Scanner(System.in);
        
        Client me=null;
        while (me==null){
            me= entrance(scanner,db);
        }

        System.out.println("Here is the menu");
        menu.viewMenu();

        while(true){
            System.out.println("What do you want ? ");
            String choice = scanner.nextLine();
            if(choiceMake(scanner, me, db, choice, menu)==true){
                break;
            }

        }
    }
}
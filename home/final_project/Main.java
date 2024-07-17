package final_project;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static String promptUserInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
    public static Client entrance(Database db){
        String entranceChaoice = promptUserInput("Please login or register");
        Client returnClient = null;
        try {
        switch (entranceChaoice) {
            case Constants.LOGIN:
                String emailLogin = promptUserInput("Enter email: ");
                String passwordLogin = promptUserInput("Enter password: ");
                returnClient = db.logIn(passwordLogin, emailLogin);
                break;
            case Constants.REGISTER:
                String username = promptUserInput("Enter username: ");
                String passwordRegister = promptUserInput("Enter password: ");
                String emailRegister = promptUserInput("Enter email: ");
                String isAdmin = promptUserInput("Are you admin");
                String paymentMethod = promptUserInput("Enter your paymentMethod");
                returnClient = db.register(username, passwordRegister, emailRegister, Boolean.parseBoolean(isAdmin),paymentMethod);
                break;
        }
        }catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return returnClient;
    }
    public static Boolean choiceMake( Client me, Database db, String choice, Menu menu, Confirmation conf ){
        switch (choice){
            case Constants.ADD_TO_CART:
                String productAdd = promptUserInput("What do you want to add?");
                me.getCart().addToCart(productAdd, db);
                db.save(me);
                return false;

            case Constants.CLEAR_CART: //OK
                me.getCart().clearCart();
                db.save(me);
                System.out.println("Cart has been cleared.");
                return false;
                
            case Constants.VIEW_CART: //OK
                me.getCart().viewCart();
                return false;
                
            case Constants.REMOVE_PRODUCT://OK
                String productRemove = promptUserInput("What do you want to remove?");
                me.getCart().removeFromCart(productRemove);
                db.save(me);
                return false;
                
            case Constants.EDIT_PROFILE:
                String newName = promptUserInput("Enter new name:");
                String newPassword = promptUserInput("Enter new password:");
                me.editName(newName);
                me.editPassword(newPassword);
                db.save(me);
                return false;
                
            case Constants.VIEW_MENU://OK
                menu.viewMenu();
                return false;
                
            case Constants.VIEW_SORTED_MENU://OK
                menu.viewMenuPriceSorted();
                return false;

            case Constants.SET_PAYMENT:
                String newPayment = promptUserInput("Enter new payment method:");
                me.getPayment().setPaymentMethod(newPayment);
                db.save(me);
                return false;

            case Constants.LOGOUT:
                return true;

            case Constants.HELP:
                System.out.println("viewMenu-view menu");
                System.out.println("viewSortedMenu-view menu sorted by prices");
                System.out.println("editProfile-edit user's name and password");
                System.out.println("removeProduct-remove product from cart");
                System.out.println("viewCart-view cart");
                System.out.println("clearCart-clear cart");
                System.out.println("addToCart-add product to cart");
                System.out.println("logout-logout cart");
                System.out.println("confirm-confirm order");
                return false;

            case Constants.CONFIRMATION:
                System.out.println("Thanks for your order");
                System.out.println(me.getName()+", Your order is:");
                me.getCart().viewCart();
                System.out.println("You will pay: "+conf.ConfirmOrder(me));
                return false;

            default:
                System.out.println("No such command, use 'help' command");
                return false;
        }
    }
    
    public static void main(String[] args) {
        Database db = new Database();
        Menu menu = new Menu(db);
        Confirmation conf=new Confirmation();
        Client me = null;
        while (me == null){
            me = entrance(db);
        }

        System.out.println("Here is the menu");
        menu.viewMenu();

        while(true){
            String choice = promptUserInput("What do you want ?");
            if(choiceMake(me, db, choice, menu, conf)==true){
                break;
            }
        }
        System.out.println("Bye");
    }
}
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
                String isAdmin = promptUserInput("Are you admin(true/false): ");
                String paymentMethod = promptUserInput("Enter your paymentMethod");
                if (!username.equals("") && !passwordRegister.equals("") && !emailRegister.equals("") && (isAdmin.equals("true") || isAdmin.equals("false"))){
                    returnClient = db.register(username, passwordRegister, emailRegister, Boolean.parseBoolean(isAdmin),paymentMethod);  
                }else{
                    System.out.println("impossible to register");
                }
                break;
        }
        }catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return returnClient;
    }
    
    public static Boolean choiceMake( Client me, Database db, String choice, Menu menu, Confirmation conf ){
        switch (choice){
            case Constants.ADD_TO_CART: //OK
                String productAdd = promptUserInput("What do you want to add?");
                boolean addResult = me.getCart().addToCart(productAdd, db);
                if (addResult){
                    db.save(me);
                    System.out.println(productAdd + " has been added to the cart.");
                }else{
                    System.out.println(productAdd + " can't be added to cart");
                }
                return false;

            case Constants.CLEAR_CART: //OK
                me.getCart().clearCart();
                db.save(me);
                System.out.println("Cart has been cleared.");
                return false;
                
            case Constants.VIEW_CART: //OK
                me.getCart().viewCart();
                return false;
                
            case Constants.REMOVE_PRODUCT: //OK
                String productRemove = promptUserInput("What do you want to remove?");
                boolean deleteResult = me.getCart().removeFromCart(productRemove);
                if (deleteResult){
                    db.save(me);
                    System.out.println(productRemove + " has been removed from the cart.");
                }else{
                    System.out.println("Product not found. No product removed.");
                }
                return false;
                
            case Constants.EDIT_PROFILE: //OK
                String newName = promptUserInput("Enter new name:");
                String newPassword = promptUserInput("Enter new password:");
                boolean successName = me.editName(newName);
                boolean successPassword = me.editPassword(newPassword);
                if (successName && successPassword){
                    db.save(me);
                    System.out.println("Success");
                }else{
                    System.out.println("Something went wrong");
                }
                return false;
                
            case Constants.VIEW_MENU://OK
                menu.viewMenu();
                return false;
                
            case Constants.VIEW_SORTED_MENU://OK
                menu.viewMenuPriceSorted();
                return false;

            case Constants.SET_PAYMENT://OK
                String newPayment = promptUserInput("Enter new payment method:");
                boolean result = me.getPayment().setPaymentMethod(newPayment);
                if (result){
                    db.save(me);
                    System.out.println("Success");
                }else{
                    System.out.println("Something went wrong");
                }
                
                return false;

            case Constants.LOGOUT://OK
                return true;

            case Constants.HELP://OK
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

            case Constants.CONFIRMATION://OK
                System.out.println("Thanks for your order");
                System.out.println(me.getName()+", Your order is:");
                me.getCart().viewCart();
                System.out.println("You will pay: "+conf.ConfirmOrder(me)+"$");
                db.save(me);
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
            if(choiceMake(me, db, choice, menu, conf)){
                break;
            }
        }
        System.out.println("Bye");
    }
}
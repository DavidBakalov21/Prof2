package final_project;

public class Client{
    private String name;
    private String password;
    private String email;
    private boolean isAdmin;
    private Cart cart;
    private PaymentMethod payment;
    private int bonuses;

    public Client(String name, String password, String email, boolean  isAdmin, Cart cart, PaymentMethod payment, int bonuses){
        this.name=name;
        this.password=password;
        this.email=email;
        this.isAdmin=isAdmin;
        this.cart=cart;
        this.payment=payment;
        this.bonuses=bonuses;
    }

    public String getEmail(){
        return email;
    }

    public PaymentMethod getPayment(){
        return payment;
    }

    public Boolean editName(String newName){
        if (!newName.equals("")){
            name=newName;
            return true;
        }
        return false;
    }

    public Boolean editPassword(String newPassword){
        if (!newPassword.equals("")){
            password=newPassword;
            return true;
        }
        return false;
    }
    
    public Boolean isUserAdmin(){
        return isAdmin;
    }

    public int getBonuses(){
        return bonuses;
    }

    public Cart getCart(){
        return cart;
    }

    public void addBonuses(int amount){
        bonuses+=amount;
    }

    public String getName() {
        return name;
    }
 
    public String getPassword(){
        return password;
    }
    
    public String generateString(){
         return String.format("%s,%s,%s,%b,%s,%d;%s",
                name, password, email, isAdmin, payment.getMethod(), bonuses, cart.toString());

    }
}
package final_project;

public class Client{
    private String name;
    private String password;
    private String email;
    private boolean  isAdmin;
    private Cart cart;
    private PaymentMethod payment;
    private int bonuses;

    public Client(String name, String password, String eamil, boolean  isAdmin){
        this.name=name;
        this.password=password;
        this.email=eamil;
        this.isAdmin=isAdmin;
        this.cart=new Cart();
        this.payment=new PaymentMethod("human souls");
        this.bonuses=0;
    }

    public void editName(String newName){
        name=newName;
    }
    public void editPassword(String newPassword){
        password=newPassword;
    }

    public void editEmail(String newEmail){
        email=newEmail;
    }

    public Cart getCart(){
        return cart;
    }

    public void addBonuses(int amount){
        bonuses+=amount;
    }
}
package final_project;

public class Product{

    public Product(Double price, String name){
        this.price=price;
        this.name=name;
    }

    private double price;
    private String name;

    public double getPrice(){
        return price;
    }

    public String getName(){
        return name;
    }
}
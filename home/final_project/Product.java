package final_project;

public class Product{

    public Product(String name, double price){
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

    @Override
    public String toString() {
        return name + "-" + price;
    }
}
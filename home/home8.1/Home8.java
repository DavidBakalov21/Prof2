interface Product {
    double getCoefficient();
    double getBasePrice();
}

class PricePrinter<PRIORITY extends Number> {
    private final PRIORITY priority;

    public PricePrinter(PRIORITY priority) {
        this.priority = priority;
    }

    public <T1 extends Product, T2 extends Product> void printTotalPrice(T1 obj1, T2 obj2) {
        double totalPrice = priority.doubleValue() * obj1.getCoefficient() * obj1.getBasePrice() + obj2.getCoefficient() * obj2.getBasePrice();
        System.out.println("Total Price: " + totalPrice);
    }
}

class Milk implements Product{
    private double coefficient;  
    private double basePrice;  
    public Milk(double coefficient, double basePrice) {  
        this.coefficient = coefficient;  
        this.basePrice = basePrice;  
    }  

    @Override
    public double getCoefficient(){
        return coefficient;
    }

    @Override
    public double getBasePrice(){
        return basePrice;
    }
}

class Cookies implements Product{
    private double coefficient;  
    private double basePrice;  
    public Cookies(double coefficient, double basePrice) {  
        this.coefficient = coefficient;  
        this.basePrice = basePrice;  
    }  

    @Override
    public double getCoefficient(){
        return coefficient;
    }

    @Override
    public double getBasePrice(){
        return basePrice;
    }
}

class Pineapple implements Product{
    private double coefficient;  
    private double basePrice;  
    public Pineapple(double coefficient, double basePrice) {  
        this.coefficient = coefficient;  
        this.basePrice = basePrice;  
    }  

    @Override
    public double getCoefficient(){
        return coefficient;
    }

    @Override
    public double getBasePrice(){
        return basePrice;
    }
}

public class Home8 {
    public static void main(String[] args) {
        Milk milk = new Milk(2.0, 3.0);
        Cookies cookies = new Cookies(3.0, 6.0);
        Pineapple pineapple = new Pineapple(4.0, 8.0);
        PricePrinter<Integer> presenter = new PricePrinter<>(2);

        presenter.printTotalPrice(milk, cookies);
        presenter.printTotalPrice(pineapple, cookies);
    }
}
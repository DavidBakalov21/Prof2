
package home4_1;
public class Pizza {
    private String first;
    private String second;
    private String third;
    private String fourth;
    private double price;

    public Pizza() {
        this.price = 0.0;
    }

    public void setFirst(String first, double cost) {
        this.first = first;
        this.price += cost;
    }

    public void setSecond(String second, double cost) {
        this.second = second;
        this.price += cost;
    }

    public void setThird(String third, double cost) {
        this.third = third;
        this.price += cost;
    }

    public void setFourth(String fourth, double cost) {
        this.fourth = fourth;
        this.price += cost;
    }

    public String showPizza() {
        return String.format("First: %s Second: %s Third: %s Fourth: %s Price: $%.2f",
                first, second, third, fourth, price);
    }
}

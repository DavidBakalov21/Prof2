
package home4_1;
public abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void createNewPizzaProduct() {
        pizza = new Pizza();
    }

    public abstract void setFirst(String first, double cost);
    public abstract void setSecond(String second, double cost);
    public abstract void setThird(String third, double cost);
    public abstract void setFourth(String fourth, double cost);
}

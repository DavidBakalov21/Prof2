package home4_1;
public class BestPizzaBuilder extends PizzaBuilder {

    @Override
    public void setFirst(String first, double cost) {
        pizza.setFirst(first, cost);
    }

    @Override
    public void setSecond(String second, double cost) {
        pizza.setSecond(second, cost);
    }

    @Override
    public void setThird(String third, double cost) {
        pizza.setThird(third, cost);
    }

    @Override
    public void setFourth(String fourth, double cost) {
        pizza.setFourth(fourth, cost);
    }
}

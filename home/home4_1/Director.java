package home4_1;
public class Director {
    private PizzaBuilder builder;

    public void setBuilder(PizzaBuilder builder) {
        this.builder = builder;
    }

    public void constructPizza(String first, String second, String third, String fourth,
                               double firstPrice, double secondPrice, double thirdPrice, double fourthPrice) {
        builder.createNewPizzaProduct();
        builder.setFirst(first, firstPrice);
        builder.setSecond(second, secondPrice);
        builder.setThird(third, thirdPrice);
        builder.setFourth(fourth, fourthPrice);
    }
}

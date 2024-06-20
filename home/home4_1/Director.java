package home4_1;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Director {
    private PizzaBuilder builder;

    public void setBuilder(PizzaBuilder builder) {
        this.builder = builder;
    }

    public void constructPizza(List<SimpleEntry<String, Double>> ingredients) {
        builder.createNewPizzaProduct();
        builder.setIngredients(ingredients);
    }
}
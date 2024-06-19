package home4_1;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class BestPizzaBuilder extends PizzaBuilder {

    @Override
    public void setIngredients(List<SimpleEntry<String, Double>> ingredients) {
        for (SimpleEntry<String, Double> ingredient : ingredients) {
            pizza.addIngredient(ingredient.getKey(), ingredient.getValue());
        }
    }
}

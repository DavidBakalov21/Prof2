
package home4_1;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public abstract class PizzaBuilder {
    protected Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void createNewPizzaProduct() {
        pizza = new Pizza();
    }

    public abstract void setIngredients(List<SimpleEntry<String, Double>> ingredients);
}
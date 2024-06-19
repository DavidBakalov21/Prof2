
package home4_1;
import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private List<String> ingredients;
    private double price;

    public Pizza() {
        this.ingredients = new ArrayList<>();
        this.price = 0.0;
    }

    public void addIngredient(String ingredient, double cost) {
        this.ingredients.add(ingredient);
        this.price += cost;
    }

    public String showPizza() {
        StringBuilder pizzaDescription = new StringBuilder("Ingredients: ");
        for (String ingredient : ingredients) {
            pizzaDescription.append(ingredient).append(" ");
        }
        pizzaDescription.append(String.format("Price: $%.2f", price));
        return pizzaDescription.toString();
    }
}
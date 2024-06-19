package home4_1;
import org.junit.jupiter.api.Test;  
import static org.junit.jupiter.api.Assertions.*; 

public class BuilderTest {  
  
    @Test  
    public void tenDollarPizza() {  
   
        Director director = new Director();
        BestPizzaBuilder bestPizzaBuilder = new BestPizzaBuilder();
        director.setBuilder(bestPizzaBuilder);
        List<SimpleEntry<String, Double>> ingredients = new ArrayList<>();
        ingredients.add(new SimpleEntry<>("mayo", 1.0));
        ingredients.add(new SimpleEntry<>("meat", 2.0));
        ingredients.add(new SimpleEntry<>("mozzarella", 3.0));
        ingredients.add(new SimpleEntry<>("onions", 4.0));
        director.constructPizza(ingredients);
        Pizza gamingPizza = bestPizzaBuilder.getPizza()

        // Operate:
        String result = gamingPizza.showPizza();

        // Check:
        assertEquals("First: mayo Second: meat Third: mozzarella Fourth: onions Price: $10,00", result);
    }  

    @Test  
    public void zeroDollarPizza() {  


        Director director = new Director();
        BestPizzaBuilder bestPizzaBuilder = new BestPizzaBuilder();
        director.setBuilder(bestPizzaBuilder);
        List<SimpleEntry<String, Double>> ingredients = new ArrayList<>();
        ingredients.add(new SimpleEntry<>("nothing", 0));
        ingredients.add(new SimpleEntry<>("nothing", 0));
        ingredients.add(new SimpleEntry<>("nothing", 0));
        ingredients.add(new SimpleEntry<>("nothing", 0));
        director.constructPizza(ingredients);
        Pizza gamingPizza = bestPizzaBuilder.getPizza()

        // Operate:
        String result = gamingPizza.showPizza();

        // Check:
        assertEquals("First: nothing Second: nothing Third: nothing Fourth: nothing Price: $0,00", result);
    }

}
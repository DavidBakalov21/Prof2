package home4_1;
import org.junit.jupiter.api.Test;  
import static org.junit.jupiter.api.Assertions.*; 

public class BuilderTest {  
  
    @Test  
    public void tenDollarPizza() {  
   
        Director director = new Director();
        BestPizzaBuilder bestPizzaBuilder = new BestPizzaBuilder();
        director.setBuilder(bestPizzaBuilder);
        director.constructPizza("mayo", "meat", "mozzarella", "onions", 1, 2, 3, 4);
        Pizza gamingPizza = bestPizzaBuilder.getPizza();

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
        director.constructPizza("nothing", "nothing", "nothing", "nothing", 0,0,0,0);
        Pizza gamingPizza = bestPizzaBuilder.getPizza();

        // Operate:
        String result = gamingPizza.showPizza();

        // Check:
        assertEquals("First: nothing Second: nothing Third: nothing Fourth: nothing Price: $0,00", result);
    }

}
package home4_1;

import java.util.Scanner;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

class Constants {
    public static final String custom = "custom";
    public static final String done = "done";
    public static final String predefined = "predefined";
    public static Map<String, Double> productList = new HashMap<>();
    public static Map<String, String[]> predefinedList = new HashMap<>();

    static {
        predefinedList.put("Carbonara", new String[]{"ham", "mozzarella", "onion", "onion"});
        predefinedList.put("Hawaii", new String[]{"ham", "mozzarella", "mozzarella", "mayo"});
        predefinedList.put("Special", new String[]{"tomatoes", "ketchup", "mozzarella", "mayo"});
    }
}

public class Main {

    public static Map<String, Double> readProducts(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    Constants.productList.put(name, price);
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return Constants.productList;
    }

    public static Optional<String> ingredientInputer(String message, Map<String, Double> availableProducts, Scanner scanner) {
        System.out.println("Pizza builder " + message);
        String product = scanner.nextLine().trim();
        if (availableProducts.containsKey(product)) {
            return Optional.of(product);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String[]> buildPredef(Scanner scanner) {
        System.out.println("Here are predefined pizzas:");
        System.out.println("Carbonara (ham, mozzarella, onion, onion)");
        System.out.println("Hawaii (ham, mozzarella, mozzarella, mayo)");
        System.out.println("Special (tomatoes, ketchup, mozzarella, mayo)");
        System.out.println("Make your choice:");

        String pizza = scanner.nextLine().trim();
        if (Constants.predefinedList.containsKey(pizza)) {
            return Optional.of(Constants.predefinedList.get(pizza));
        } else {
            return Optional.empty();
        }
    }

    public static Optional<String[]> buildCustom(Scanner scanner) {
        String[] ingredients = new String[4];

        for (Map.Entry<String, Double> product : Constants.productList.entrySet()) {
            System.out.println(product.getKey() + "-" + product.getValue() + "$");
        }

        Constants.productList.put("nothing", 0.0);

        ingredients[0] = ingredientInputer("What first do you want", Constants.productList, scanner).orElse("nothing");
        ingredients[1] = ingredientInputer("What second do you want", Constants.productList, scanner).orElse("nothing");
        ingredients[2] = ingredientInputer("What third do you want", Constants.productList, scanner).orElse("nothing");
        ingredients[3] = ingredientInputer("What fourth do you want", Constants.productList, scanner).orElse("nothing");

        return Optional.of(ingredients);
    }

    public static void main(String[] args) {
        Director director = new Director();
        PizzaBuilder bestPizzaBuilder = new BestPizzaBuilder();
        director.setBuilder(bestPizzaBuilder);

        System.out.println("Custom or predefined");
        Constants.productList = readProducts("ingredients.txt");
        Scanner scanner = new Scanner(System.in);
        String numsEntered = scanner.nextLine().trim();
        Optional<String[]> ingredients;

        if (numsEntered.equals(Constants.predefined)) {
            ingredients = buildPredef(scanner);
        } else if (numsEntered.equals(Constants.custom)) {
            ingredients = buildCustom(scanner);
        } else {
            System.out.println("no such options");
            scanner.close();
            return;
        }

        if (ingredients.isPresent()) {
            System.out.println("Enter <done> to finish order");
            String doneChoice = scanner.nextLine().trim();

            if (doneChoice.equals(Constants.done)) {
                String[] selectedIngredients = ingredients.get();
                director.constructPizza(selectedIngredients[0], selectedIngredients[1], selectedIngredients[2], selectedIngredients[3],
                        Constants.productList.get(selectedIngredients[0]), Constants.productList.get(selectedIngredients[1]),
                        Constants.productList.get(selectedIngredients[2]), Constants.productList.get(selectedIngredients[3]));
                Pizza pizza = bestPizzaBuilder.getPizza();
                
                System.out.println("Your pizza is " + pizza.showPizza());
            } else {
                System.out.println("Exiting");
            }
        } else {
            System.out.println("Invalid selection. Exiting.");
        }

        scanner.close();
    }
}

package home4_1;
import java.util.List;
import java.util.Scanner;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

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

    public static Optional<String> ingredientInputer(Map<String, Double> availableProducts, Scanner scanner) {
        System.out.println("Pizza builder: Enter product ");
        String product = scanner.nextLine().trim();
        if (availableProducts.containsKey(product)) {
            return Optional.of(product);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<SimpleEntry<String, Double>>> buildPredef(Scanner scanner) {
        System.out.println("Here are predefined pizzas:");
        System.out.println("Carbonara (ham, mozzarella, onion, onion)");
        System.out.println("Hawaii (ham, mozzarella, mozzarella, mayo)");
        System.out.println("Special (tomatoes, ketchup, mozzarella, mayo)");
        System.out.println("Make your choice:");

        String pizza = scanner.nextLine().trim();
        if (Constants.predefinedList.containsKey(pizza)) {
            List<SimpleEntry<String, Double>> ingredients = new ArrayList<>();
            for (String ingredient : Constants.predefinedList.get(pizza)) {
                ingredients.add(new SimpleEntry<>(ingredient, Constants.productList.getOrDefault(ingredient, 0.0)));
            }
            return Optional.of(ingredients);
        } else {
            return Optional.empty();
        }
    }

    public static Optional<List<SimpleEntry<String, Double>>> buildCustom(Scanner scanner) {
        List<SimpleEntry<String, Double>> ingredients = new ArrayList<>();

        for (Map.Entry<String, Double> product : Constants.productList.entrySet()) {
            System.out.println(product.getKey() + "-" + product.getValue() + "$");
        }

        for (int i = 0; i < 4; i++) {
            Optional<String> ingredient = ingredientInputer(Constants.productList, scanner);
            if (ingredient.isPresent()) {
                ingredients.add(new SimpleEntry<>(ingredient.get(), Constants.productList.get(ingredient.get())));
            } else {
                System.out.println("Invalid ingredient entered, defaulting to 'nothing'.");
                ingredients.add(new SimpleEntry<>("nothing", 0.0));
            }
        }

        return Optional.of(ingredients);
    }

    public static void handlePredefined(Scanner scanner, Director director, PizzaBuilder pizzaBuilder) {
        Optional<List<SimpleEntry<String, Double>>> ingredients = buildPredef(scanner);
        if (ingredients.isPresent()) {
            finalizeOrder(scanner, director, pizzaBuilder, ingredients.get());
        } else {
            System.out.println("Invalid selection. Exiting.");
        }
    }

    public static void handleCustom(Scanner scanner, Director director, PizzaBuilder pizzaBuilder) {
        Optional<List<SimpleEntry<String, Double>>> ingredients = buildCustom(scanner);
        if (ingredients.isPresent()) {
            finalizeOrder(scanner, director, pizzaBuilder, ingredients.get());
        } else {
            System.out.println("Invalid selection. Exiting.");
        }
    }

    public static void handleErrors() {
        System.out.println("No such options");
    }

    public static void finalizeOrder(Scanner scanner, Director director, PizzaBuilder pizzaBuilder, List<SimpleEntry<String, Double>> ingredients) {
        System.out.println("Enter <done> to finish order");
        String doneChoice = scanner.nextLine().trim();

        if (doneChoice.equals(Constants.done)) {
            director.constructPizza(ingredients);
            Pizza pizza = pizzaBuilder.getPizza();
            System.out.println("Your pizza is " + pizza.showPizza());
        } else {
            System.out.println("Exiting");
        }
    }

    public static void main(String[] args) {
        Director director = new Director();
        PizzaBuilder bestPizzaBuilder = new BestPizzaBuilder();
        director.setBuilder(bestPizzaBuilder);

        System.out.println("Custom or predefined");
        Constants.productList = readProducts("ingredients.txt");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case Constants.predefined:
                handlePredefined(scanner, director, bestPizzaBuilder);
                break;
            case Constants.custom:
                handleCustom(scanner, director, bestPizzaBuilder);
                break;
            default:
                handleErrors();
                break;
        }

        scanner.close();
    }
}
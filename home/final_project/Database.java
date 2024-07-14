package final_project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Database {

    public ArrayList<Product> readProductsFromFile(String filePath) {
        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1].replace("$", ""));
                    products.add(new Product(name, price));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }

    public static Client logIn(String password, String email) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                String[] userDetails = parts[0].split(",");
                if (userDetails.length < 6) continue;
                String storedPassword = userDetails[1];
                String storedEmail = userDetails[2];
                if (storedPassword.equals(password) && storedEmail.equals(email)) {
                    String name = userDetails[0];
                    boolean isAdmin = Boolean.parseBoolean(userDetails[3]);
                    String paymentMethod = userDetails[4];
                    int bonuses = Integer.parseInt(userDetails[5]);
                    Cart cart = parts.length > 1 ? new Cart(parts[1]) : new Cart();
                    return new Client(name, storedPassword, storedEmail, isAdmin, cart, new PaymentMethod(paymentMethod), bonuses);
                }
            }
        }
        return null;
    }

}

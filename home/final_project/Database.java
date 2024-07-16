package final_project;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
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
    public Product findProduct(String prodName) {
        try (BufferedReader br = new BufferedReader(new FileReader("products.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2 && parts[0].equals(prodName)) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1].replace("$", ""));
                    return new Product(name, price);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Product("",0.0);
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

public Client register(String name, String password, String email, Boolean isAdmin, String paymentMethod) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length < 6) continue;
                String storedPassword = userDetails[1];
                String storedEmail = userDetails[2];
                if (storedPassword.equals(password) && storedEmail.equals(email)) {
                   return new Client("","","",false, new Cart(), new PaymentMethod(""),0);
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true))) {
            Client returnClient = new Client(name, password, email, isAdmin, new Cart(), new PaymentMethod(paymentMethod), 0);
            writer.write(returnClient.generateString());
            writer.newLine();
            return returnClient;
        } catch (IOException e) {
            e.printStackTrace();
            return new Client("","","",false, new Cart(), new PaymentMethod(""),0);
        }
    }

    public boolean save(Client client) {
        ArrayList<String> fileContent = new ArrayList<>();
        boolean found = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length > 2 && userDetails[2].equals(client.getEmail())) {
                    fileContent.add(client.generateString());
                    found = true;
                } else {
                    fileContent.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (!found) {
            fileContent.add(client.generateString());
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"))) {
            for (String record : fileContent) {
                writer.write(record);
                writer.newLine();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

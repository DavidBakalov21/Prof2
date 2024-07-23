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
                    products.add(new Product(price, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return products;
    }
   
}
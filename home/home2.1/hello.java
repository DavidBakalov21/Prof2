import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;

class Constants {
    public static final String delete = "delete";
    public static final String bread = "bread";
}

class Database {
    private String databasePath;
    private Map<String, Integer> statsData = new HashMap<>();  

    public Database(String file) {
        this.databasePath = file;
        LoadData();
    }

    public void clearDatabase() {
        statsData.clear();
        saveData();
    }

    public boolean deleteUser(String nameToFind){
        if (statsData.containsKey(nameToFind)) {
            statsData.remove(nameToFind);
            saveData();
            return true;
        }
        return false;
    }

    public int UserIncrement(String nameToFind){
        int currentCount = statsData.getOrDefault(nameToFind, 0);
        currentCount++;
        statsData.put(nameToFind, currentCount);
        saveData();
        return currentCount;
    }

    public boolean checkName(String nameToFind) {
        return statsData.containsKey(nameToFind);
    }

    private void LoadData(){
        try (BufferedReader reader = new BufferedReader(new FileReader(databasePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String name = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    statsData.put(name, count);
                }
            }
        } catch (Exception e) {}
    }

    public void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(databasePath, false))) {
            for (Map.Entry<String, Integer> entry : statsData.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }
        } catch (Exception e) {}
    }
 
}

public class hello {
        
   public static void notFoundNameHandling(String additionalCommand, String nameToFind, Database db){
        if (additionalCommand.equals(Constants.delete)) {
            System.out.println("There is no such user ");
            return;
        }
        db.UserIncrement(nameToFind);
        System.out.printf("Welcome %s%n", nameToFind);
        return;
    }

   public static void FoundNameHandling(String additionalCommand, String nameToFind, Database db){
        if (additionalCommand.equals(Constants.delete)) {
            if (db.deleteUser(nameToFind)){
                System.out.println("All data about this user was cleared");
                return;
            }
            System.out.println("There was no such user");
            return;
        }
        System.out.printf("Hello again(x%d),%s%n", db.UserIncrement(nameToFind), nameToFind);
    }

    public static void main(String[] args) {
        Database db = new Database("database.txt");

        if (args.length < 1) {
            System.out.println("Please provide a name to find.");
            return;
        }

        String nameToFind = args[0];
        String additionalCommand = args.length > 1 ? args[1] : "";
       
        if (nameToFind.equals(Constants.bread))
        {
            db.clearDatabase();
            System.out.println("all data was cleared");
            return;
        }
        
        if (!db.checkName(nameToFind)) {
            notFoundNameHandling(additionalCommand,nameToFind,db);
        }else{
            FoundNameHandling(additionalCommand,nameToFind,db);
        }
    }
}
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
private String database;
private Map<String, Integer> statsData = new HashMap<>();    
public Database(String file) {
        this.database = file;
        LoadData();
    }
    private void LoadData(){
            try (BufferedReader file = new BufferedReader(new FileReader(database))) {
            String line;
            while ((line = file.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String name = parts[0];
                int count = Integer.parseInt(parts[1]);
                statsData.put(name, count);
            }
        } catch (Exception e) {}
    }
    private void SaveData(){
   try (BufferedWriter fileEdit = new BufferedWriter(new FileWriter(database))) {
        for (Map.Entry<String, Integer> entry : statsData.entrySet()) {
            fileEdit.write(entry.getKey() + " " + entry.getValue());
            fileEdit.newLine();
        }
    } catch (Exception e) {}
    }
  private String deleteUser(String nameToFind){
        statsData.remove(nameToFind);
        SaveData();
        return "All data about this user was cleared";
    }
    private String UserIncrement(String nameToFind){
        String returnValue;
        if(statsData.containsKey(nameToFind)){
             statsData.put(nameToFind, statsData.get(nameToFind) + 1);
            returnValue="Hello again(x" + statsData.get(nameToFind) + ")," + nameToFind;
        }else{
           
             statsData.put(nameToFind, 1);
            returnValue="Welcome " +nameToFind; 
        }
         SaveData();
        return returnValue;
    }
    public void clearDatabase() {
        statsData.clear();
        SaveData();
    }
   public String PerformChanges(String nameToFind, String additionalCommand){
        if(!statsData.containsKey(nameToFind)){
            if (additionalCommand.equals(Constants.delete)) {
               return "There is no such user ";
            }
            return UserIncrement(nameToFind);
        }else{
           if (additionalCommand.equals(Constants.delete)) {
                return deleteUser(nameToFind);
            }
            return UserIncrement(nameToFind); 
        }
    }
}
public class AdvancedJava {
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
        System.out.println(db.PerformChanges(nameToFind, additionalCommand));
    }
}
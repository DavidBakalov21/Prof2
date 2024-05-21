import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
class Constants {
    public static final String file = "database.txt";
    public static final String delete = "delete";
    public static final String bread = "bread";
}
class Functionality {
    String AdditionalCommand = "";
    String WordToFind;
    String lineFound;
    ArrayList<String> lines = new ArrayList<>();
    int lineNumber = 0;
    boolean found = false;
private String deleteAll(){
    try (FileWriter fileWriter = new FileWriter(Constants.file, false)) {
        System.out.println("All data was cleared");
        return "deleted";
    } catch (Exception e) {
        return "something failed";
    }
   
}
private String deleteUser(){
    for (int i = 0; i < lines.size(); i++) {
        if (lines.get(i).split(" ")[0].equals(WordToFind)) {
            lines.remove(i);
            i--;
        }
    }
    try (FileWriter writer = new FileWriter(Constants.file, false)) {
        for (String lineDel : lines) {
            if (!lineDel.equals("")) {
                writer.write(lineDel + "\n");
            }
        }
    } catch (Exception e) {}
    System.out.println("All data about this user was cleared");
    return "success";
}
private String FindLine(){
    for (String lineFinder : lines) {
        lineNumber++;
        if (!lineFinder.equals("")) {
            if (lineFinder.split(" ")[0].equals(WordToFind)) {
                found = true;
                lineFound = lineFinder;
                return "found";
            }
        }
    }
    return "not found";
}
private String FillLines(){
    try (BufferedReader reader = new BufferedReader(new FileReader(Constants.file))) {
        String line;

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        return "success";
    } catch (Exception e) {
        return "something failed";
    }
}
private void AddToExistingUser(){
    String lineArray = lines.get(lineNumber - 1);
    String[] arraySplitted = lineArray.split(" ");
    int num = Integer.parseInt(arraySplitted[1]);
    num++;
    lines.set(lineNumber - 1, arraySplitted[0] + " " + num);
    try (FileWriter writer = new FileWriter(Constants.file, false)) {
        for (String lineWrite : lines) {
            if (!lineWrite.equals("")) {
                writer.write(lineWrite + "\n");
            }
        }
    } catch (Exception e) {}
    System.out.println("Hello again(x" + num + ")," + WordToFind);
}
private void AddNewUser(){
    System.out.println("Welcome " + WordToFind);
    try (FileWriter fileEdit = new FileWriter(Constants.file, true)) {
        fileEdit.write(WordToFind + " 1\n");
    } catch (Exception e) {}
}
    public void FunctionalityFunction(int argc, String[] argv) {
        if (argc < 1) {
            System.out.println("Please provide a word to find.");
            return;
        }
        WordToFind = argv[0];
        if (argc > 1) {
            AdditionalCommand = argv[1];
        }
        System.out.println(AdditionalCommand);
        if (WordToFind.equals(Constants.bread)) {
            deleteAll();
            return;
        }
      

        FillLines();
        FindLine();
        if (!found) {
            if (AdditionalCommand.equals("delete")) {
                System.out.println("There is no such user");
                return;
            }
            AddNewUser();
        } else {

            
            if (AdditionalCommand.equals("delete")) {
                deleteUser();
                return;
            }
            AddToExistingUser();
          
        }

    }
}

public class AdvHello {
    public static void main(String[] args) {
        Functionality functionality = new Functionality();
        functionality.FunctionalityFunction(args.length, args);
    }
}
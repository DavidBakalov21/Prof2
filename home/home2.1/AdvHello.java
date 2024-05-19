import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

class Functionality {
    String argument2 = "";
    String file = "database.txt";
    String WordToFind;
    String lineFound;

    public void FunctionalityFunction(int argc, String[] argv) {
        if (argc < 1) {
            System.out.println("Please provide a word to find.");
            return;
        }
        WordToFind = argv[0];
        if (argc > 1) {
            argument2 = argv[1];
        }
        System.out.println(argument2);
        if (WordToFind.equals("bread")) {
            try (FileWriter fileWriter = new FileWriter(file, false)) {
                System.out.println("All data was cleared");
                return;
            } catch (Exception e) {}
        }
        int lineNumber = 0;
        boolean found = false;

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (Exception e) {}
        for (String lineFinder : lines) {
            lineNumber++;
            if (!lineFinder.equals("")) {
                if (lineFinder.split(" ")[0].equals(WordToFind)) {
                    found = true;
                    lineFound = lineFinder;
                    break;
                }
            }
        }
        if (!found) {
            if (argument2.equals("delete")) {
                System.out.println("There is no such user");
                return;
            }
            System.out.println("Welcome " + WordToFind);
            try (FileWriter fileEdit = new FileWriter(file, true)) {
                fileEdit.write(WordToFind + " 1\n");
            } catch (Exception e) {}
        } else {
            if (argument2.equals("delete")) {
                for (int i = 0; i < lines.size(); i++) {
                    if (lines.get(i).split(" ")[0].equals(WordToFind)) {
                        lines.remove(i);
                        i--;
                    }
                }
                try (FileWriter writer = new FileWriter(file, false)) {
                    for (String lineDel : lines) {
                        if (!lineDel.equals("")) {
                            writer.write(lineDel + "\n");
                        }
                    }
                } catch (Exception e) {}
                System.out.println("All data about this user was cleared");
                return;
            }
            String lineArray = lines.get(lineNumber - 1);
            String[] arraySplitted = lineArray.split(" ");
            int num = Integer.parseInt(arraySplitted[1]);
            num++;
            lines.set(lineNumber - 1, arraySplitted[0] + " " + num);
            try (FileWriter writer = new FileWriter(file, false)) {
                for (String lineWrite : lines) {
                    if (!lineWrite.equals("")) {
                        writer.write(lineWrite + "\n");
                    }
                }
            } catch (Exception e) {}
            System.out.println("Hello again(x" + num + ")," + WordToFind);
        }

    }
}

public class AdvHello {
    public static void main(String[] args) {
        Functionality functionality = new Functionality();
        functionality.FunctionalityFunction(args.length, args);
    }
}
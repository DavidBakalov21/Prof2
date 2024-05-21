import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
class Image{
    ArrayList<String[]> lines = new ArrayList<>();
    private String listToStr(String[] line) {
        StringBuilder sb = new StringBuilder();
        for (String s : line) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public boolean colorChecker(String color){
        String[] elements=color.split(",");
        if (elements.length < 3) {

            System.out.println("1");
            System.out.println(elements.length);
            return false;
        }
        for (int i = 0; i < elements.length; i++){
            for (int j = 0; j < elements[i].length(); j++){
                if (Character.isLetter(elements[i].charAt(j))) {
                    System.out.println("2");
                    return false;
                }
            }
            int num = Integer.parseInt(elements[i]);
            if (num < 0 || num >255) {
                System.out.println("3");
                return false;
            }
        }
        return true;
    }
    
public int FillMatrix(String filename){
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;

        while ((line = reader.readLine()) != null) {
           String[] SplitedLine=line.split(" ");
            for (int i = 0; i <SplitedLine.length; i++) {
                if (!colorChecker(SplitedLine[i])) {
                    System.out.println("Please, provide image with valid colors");
                    return 1;
                }
            }
            lines.add(SplitedLine);
        }
        return 0;
    } catch (Exception e) {
        return 1;
    }
}
public int changeMatrix(String color, String HatredColor){
for (int i = 0; i < lines.size(); i++){
    if (lines.get(i).length!= 16){
        System.out.println("Please, provide 16x16 image");
        return 1;
    }

    for (int j = 0; j < lines.get(i).length; j++){
        if (lines.get(i)[j].equals(color)){
            if (i-1>=0){
                lines.get(i-1)[j]=color;
            }
            if (j-1>=0){
                lines.get(i)[j-1]=color;
            }
        }
     
        if (lines.get(i)[j].equals(HatredColor) && colorChecker(HatredColor)) {
        lines.get(i)[j]=color;    
        }
    }
 
}
return 0;
}

public int changeFile(String filename) {
    try (BufferedWriter fileEdit = new BufferedWriter(new FileWriter(filename))) {
        for (String[] line : lines) {
            String lineStr = listToStr(line);
            if (!lineStr.isEmpty()) {
                fileEdit.write(lineStr);
                fileEdit.newLine();
            }
        }
    } catch (Exception e) {
        return 1; 
    }
    return 0;
}
public int FunctionalityFunction(int argc, String[] argv) {
    if (argc < 2) {
        System.out.println("Error:Please provide all arguments");
        return 0;
    }
    String filename = argv[0];
    String color = argv[1];
    String HatedColor = (argc > 2) ? argv[2] : "";
    if (!colorChecker(color)) {
        System.out.println("Please, enter existing color for example 255,97,105");
        return 0;
    }
    if (FillMatrix(filename) == 1) {
        return 0;
    }
    if (lines.size() != 16)
    {
        System.out.println("Please, provide 16x16 image");
        return 0;
    }
    if (changeMatrix(color,HatedColor) == 1) {
        return 0;
    }
    changeFile(filename);
    System.out.println("Image was changed successfully");
    return 0;
}
}

public class imageChanger {
    public static void main(String[] args) {
        Image image = new Image();
        image.FunctionalityFunction(args.length, args);
    }
}
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
class Constants {
    public static final int MatrixSize = 16;
}
class Pixel
{
    private String color;
    public Pixel(String col){
        this.color=col;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String setCol){
        this.color=setCol;
    }
    public boolean isValid(){
        String[] elements=color.split(",");
        if (elements.length < 3) {
            return false;
        }
        for (int i = 0; i < elements.length; i++){
            for (int j = 0; j < elements[i].length(); j++){
                if (Character.isLetter(elements[i].charAt(j))) {
                    return false;
                }
            }
            int num = Integer.parseInt(elements[i]);
            if (num < 0 || num >255) {
                return false;
            }
        }
        return true;
    }
}
 
class Image{
   private ArrayList<ArrayList<Pixel>> lines = new ArrayList<>();
   private String file;
   private Pixel color;
   private Pixel hatedColor;
public Image(String filePath, Pixel colorArgument, Pixel hatred){
        this.file=filePath;
        this.color=colorArgument;
        this.hatedColor=hatred;
    }
private String listToStr(ArrayList<Pixel> line) {
        StringBuilder sb = new StringBuilder();
        for (Pixel s : line) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(s.getColor());
        }
        return sb.toString();
    } 
public ArrayList<ArrayList<Pixel>> getLines(){
    return lines;
}
public String loadMatrix(){
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
           String[] SplitedLine=line.split(" ");
           ArrayList<Pixel> pixels=new ArrayList<>();
            if (SplitedLine.length!= Constants.MatrixSize){
                return "Please, provide 16x16 image";
             }       
            for (int i = 0; i <SplitedLine.length; i++) {
                Pixel pix=new Pixel(SplitedLine[i]);
                if (!pix.isValid()) {
                    return "Please, provide image with valid colors";
                }
                pixels.add(pix);
            }
            lines.add(pixels);
        }
        return "success";
    } catch (Exception e) {
        return "";
    }
}
private int saveFile() {
    try (BufferedWriter fileEdit = new BufferedWriter(new FileWriter("Output"+file))) {
        for (ArrayList<Pixel> line : lines) {
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

public int changeMatrix(){
for (int i = 0; i < lines.size(); i++){
    for (int j = 0; j < lines.get(i).size(); j++){
        String gotColor=color.getColor();
        String gotHatredColor=hatedColor.getColor();
        if (lines.get(i).get(j).getColor().equals(gotColor)){
            if (i-1>=0){
                lines.get(i-1).get(j).setColor(gotColor);
            }
            if (j-1>=0){
                lines.get(i).get(j-1).setColor(gotColor);
            }
        }
     
        if (lines.get(i).get(j).getColor().equals(gotHatredColor) && hatedColor.isValid()) {
        lines.get(i).get(j).setColor(gotColor);    
        }
    }
 
}
return saveFile();
}
}

public class imageChanger {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Error:Please provide all arguments");
            return;
        }
    String filename = args[0];
    String color = args[1];
    String HatedColor = (args.length > 2) ? args[2] : "";
    Pixel colorPicked=new Pixel(color);
    Pixel colorOfHate=new Pixel(HatedColor);
    if (!colorPicked.isValid()) {
        System.out.println("Please, enter existing color for example 255,97,105");
        return;
    }
    Image image = new Image(filename,colorPicked,colorOfHate);
    String loadResult=image.loadMatrix();
    if(!loadResult.equals("success")){
        System.out.println(loadResult);
        return;
    }
    if(image.getLines().size()!=Constants.MatrixSize){
       System.out.format("Please, provide %dx%d image%n", Constants.MatrixSize, Constants.MatrixSize);
        return;
    }
    image.changeMatrix();
    System.out.println("Image was changed successfully");
    }
}
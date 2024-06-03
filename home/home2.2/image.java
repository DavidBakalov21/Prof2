import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

class Constants {
    public static final int MatrixSize = 16;
    public static final int Fine = 0;
    public static final int sizeProblem = 1;
    public static final int colorProblem = 2;
}

class Color {
    int r = -1;
    int g = -1;
    int b = -1;
}

class Pixel{
    
    public Pixel() {}

    public Pixel(String col) {
        String[] values = col.split(",");
        if (values.length == 3) {
            color.r = convertToInt(values[0]);
            color.g = convertToInt(values[1]);
            color.b = convertToInt(values[2]);
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Pixel col){
        this.color=col.getColor();
    }
    
    public String toString() {
        return String.format("%d,%d,%d", color.r, color.g, color.b);
    }

    public boolean isValid() {
        return (color.r >= 0 && color.r <= 255) &&
               (color.g >= 0 && color.g <= 255) &&
               (color.b >= 0 && color.b <= 255);
    }

    public boolean compareColor(Pixel compare) {
        return (compare.color.b == color.b) &&
               (compare.color.g == color.g) &&
               (compare.color.r == color.r);
    }

    private int convertToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Color color = new Color();

}

class ImageMatrix{
    private Pixel[][] lines;
    private String pixelMatrixFile;

     public ImageMatrix(String filePath) {
        this.pixelMatrixFile = filePath;
        this.lines = new Pixel[Constants.MatrixSize][Constants.MatrixSize];

    }
    public int loadMatrix(){
        try (BufferedReader reader = new BufferedReader(new FileReader(pixelMatrixFile))) {
            String line;
            int indexCounter = 0;
            while ((line = reader.readLine()) != null) {
            String[] SplitedLine=line.split(" ");
            Pixel[] pixels=new Pixel[Constants.MatrixSize];
                if (SplitedLine.length!= Constants.MatrixSize){
                    return Constants.sizeProblem;
                }       
                for (int i = 0; i <SplitedLine.length; i++) {
                    Pixel pix=new Pixel(SplitedLine[i]);
                    if (!pix.isValid()) {
                        return Constants.colorProblem;
                    }
                    pixels[i]=pix;
                }
                if (indexCounter == Constants.MatrixSize) {
                    return Constants.sizeProblem;
                }
                lines[indexCounter]=pixels;
                indexCounter++;
            }    
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return Constants.Fine;
    }

    public void changeMatrix(Pixel favouriteColor, Pixel hatedColor){
        for (int i = 0; i < Constants.MatrixSize; i++) {
            for (int j = 0; j < Constants.MatrixSize; j++) {
                if (lines[i][j].compareColor(favouriteColor)) {
                    if (i > 0) {
                        lines[i - 1][j].setColor(favouriteColor);
                    }
                    if (j > 0) {
                        lines[i][j - 1].setColor(favouriteColor);
                    }
                }
                if (lines[i][j].compareColor(hatedColor) && hatedColor.isValid()) {
                    lines[i][j].setColor(favouriteColor);    
                }
            }
        }
    }
    
    public void saveFile() {
        try (BufferedWriter fileEdit = new BufferedWriter(new FileWriter("Output" + pixelMatrixFile))) {
            for (int i = 0; i < Constants.MatrixSize; i++) {
                String lineStr = arrayToString(lines[i]);
                if (!lineStr.isEmpty()) {
                    fileEdit.write(lineStr);
                    if (i < Constants.MatrixSize - 1) {
                        fileEdit.newLine();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String arrayToString(Pixel[] line) {
        return Arrays.stream(line)
                     .map(Pixel::toString)
                     .collect(Collectors.joining(" "));
    }
}

public class image {
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
        if(!colorPicked.isValid()){
            System.out.println("Please, enter existing color for example 255,97,105");
            return;
        }
        ImageMatrix image = new ImageMatrix(filename);
        int loadResult=image.loadMatrix();
        switch(loadResult) {
            case Constants.sizeProblem:
                System.out.println("Please, provide 16x16 image");
                break;
            case Constants.colorProblem:
                System.out.println("Please, provide image with valid colors");
                break;
            case Constants.Fine:
                image.changeMatrix(colorPicked, colorOfHate);
                image.saveFile();
                System.out.println("Image was changed successfully");
                break;
            default:
                System.out.println("Unknown error happened");
                break;
        }
        return;
    }
}
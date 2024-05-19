import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
class BiggestNumberFinder{
    String numsEntered;
    ArrayList<Double> doubleList= new ArrayList<>();
    void GetList(){
        Scanner scannerNum = new Scanner(System.in); 
        System.out.println("Input your nums in this format: 1.1,2.0,3.0,-4");
        System.out.println("If you want to exit enter 'exit' or '' ");
        this.numsEntered= scannerNum.nextLine();
    }
    void dobleListCreation(){
        this.doubleList.clear();
        String[] numsArray = this.numsEntered.split(",");
        ArrayList<String> StringList = new ArrayList<>();
        for (String num : numsArray) {
            StringList.add(num.replaceAll(" ",""));
        }
         StringList.removeIf(num -> num.charAt(0) != '-');
            for (String el : StringList){
                try{
                    this.doubleList.add(Double.parseDouble(el));
                } catch (NumberFormatException e) {
                    System.out.println("Can't work with " + el);
                }

            }
    }
    public void EternalLoop(){
        while (true){
        GetList();
        if (numsEntered.equals("exit") || numsEntered.equals("")){
            break;
        }
        dobleListCreation();
        if (this.doubleList.size()<1){
            System.out.println("You did not provide any operatable values, please try again ");  
            continue;
        } 
            System.out.println("The biggest negative value is " + Double.toString(Collections.max(doubleList)));  
        }
    }
}

public class task1 {
    public static void main(String[] args) {
    BiggestNumberFinder finder = new BiggestNumberFinder();
    finder.EternalLoop();
    }
}
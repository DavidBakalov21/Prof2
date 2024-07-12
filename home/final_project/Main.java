package final_project;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("fgd");
       Database db = new Database();  
        Menu menu = new Menu(db);
        menu.viewMenu();
    }
}
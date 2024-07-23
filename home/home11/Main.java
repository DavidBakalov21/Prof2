import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;
class Constants{
    public static final ArrayList<String> booksConstant = new ArrayList<>(
        Arrays.asList(
            "The Topographer’s Clown.", "The Chamber of Beaver.", 
            "The Ironer of Kanban.", "The Piglet of Tire.", 
            "The Border of the Unix.", "The Half-Time Convince.", 
            "The Earthly Pillows.", "The Censorship of the Ping.", 
            "The True Powers.", "The Overturn of the Ling."
        )
    );
}

class Caffe {
    private ArrayList<String> books;
    private int firstElement = 0;
    private int lastElement;
    private String userName;
    public Caffe(ArrayList<String> booksArgument, String name) {
        this.books = new ArrayList<String>(booksArgument);
        this.lastElement = books.size();
        this.userName=name;
    }

    public String getUserName(){
        return userName;
    }

    public String chooseBook() {
        if (books.isEmpty()) {
            return "";
        }
        int chosenIndex = randomInt(firstElement, lastElement - 1);
        String chosenBook = books.get(chosenIndex);
        removeFromList(chosenBook);
        lastElement = books.size();
        return chosenBook;
    }

    private int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private void removeFromList(String book) {
        books.remove(book);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name:");
        String name = scanner.nextLine();

        Caffe caffe = new Caffe(Constants.booksConstant, name);
        
        while (true) {
            String chosenBook = caffe.chooseBook();
            if (chosenBook.isEmpty()) {
                System.out.println("Au revoir, " + caffe.getUserName() +" !");
                break;
            }
            System.out.println("How about: " + chosenBook + " ?");
            System.out.println("Yes/No?");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("Yes")) {
                System.out.println("Enjoy your book, " + caffe.getUserName() +" !");
                break;
            }
        }

        scanner.close();
    }
}
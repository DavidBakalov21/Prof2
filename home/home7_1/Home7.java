import java.util.Scanner;
class Constants {
    public static final String INTRO = "Intro";
    public static final String STARMAN = "Starman";
    public static final String SHOW_MUST_GO_ON = "Show must go on";
    public static final String LET_IT_BE = "Let it be";
    public static final String IN_THE_END = "But in the end, it doesn't even matter";
    public static final String dangerous = "dangerous";
    public static final String fun = "fun";
    public static final String sad = "sad";
    public static final String silly = "silly";
}

class StateMachine{
    private String currentSong;

    public StateMachine() {
        currentSong = Constants.INTRO;
    }

    public String getSong(){
        return currentSong;
    }

    public void setSong(String command) {
        switch (currentSong) {
            case Constants.INTRO:
                switch (command) {
                    case Constants.dangerous:
                        currentSong=Constants.LET_IT_BE;
                        break;
                    case Constants.fun:
                        currentSong=Constants.STARMAN;
                        break;
                    case Constants.sad:
                        currentSong=Constants.IN_THE_END;
                        break;
                }
                break;
            case Constants.STARMAN:
                switch (command) {
                    case Constants.dangerous:
                        currentSong=Constants.SHOW_MUST_GO_ON;
                        break;
                    case Constants.fun:
                        currentSong=Constants.IN_THE_END;
                        break;
                    case Constants.silly:
                        currentSong=Constants.INTRO;
                        break;
                }
                break;
            case Constants.SHOW_MUST_GO_ON:
                switch (command) {
                    case Constants.fun:
                        currentSong=Constants.STARMAN;
                        break;
                    case Constants.sad:
                        currentSong=Constants.LET_IT_BE;
                        break;
                }
                break;
            case Constants.LET_IT_BE:
                switch (command) {
                    case Constants.dangerous:
                        currentSong=Constants.INTRO;
                        break;
                    case Constants.silly:
                        currentSong=Constants.SHOW_MUST_GO_ON;
                        break;
                }
                break; 
            case Constants.IN_THE_END:
                System.out.println("Termination");
                System.exit(0);
                break;   
        }
    }
}

public class Home7 {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    StateMachine songManager = new StateMachine();
    while (true) {
            System.out.println("Current Song: " + songManager.getSong());
            System.out.print("Command ");
            String command = scanner.nextLine();
            songManager.setSong(command);
        }
    }
}
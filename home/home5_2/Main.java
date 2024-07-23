import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Configuration {
    private static Configuration conf;
    private Map<Character, String[]> configMap = new HashMap<>();

    private Configuration() {
        loadConf();
    }

    public static synchronized Configuration getConf() {
        if (conf == null) {
            conf = new Configuration();
        }
        return conf;
    }

    private void loadConf() {
        try (BufferedReader br = new BufferedReader(new FileReader("config.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                char Start = parts[0].charAt(0);
                char End = parts[0].charAt(2);
                String preWord = parts[1];
                String postWord = parts[2];
                for (char c = Start; c <= End; c++) {
                    configMap.put(c, new String[]{preWord, postWord});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] getWords(char c) {
        return configMap.getOrDefault(c, new String[]{"NotFound", "NotFound"});
    }
}

class WordThread extends Thread {
    private String word;

    public WordThread(String word) {
        this.word = word;
    }

    public void run() {
        try {
            Thread.sleep(1000);
            Configuration config = Configuration.getConf();
            char firstLetter = Character.toLowerCase(word.charAt(0));
            String[] words = config.getWords(firstLetter);
            System.out.println(words[0] + " " + word + " " + words[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputWords = new String[5];

        System.out.println("Enter your 5 words:");
        for (int i = 0; i < 5; i++) {
            inputWords[i] = scanner.next();
        }

        for (String word : inputWords) {
            for (int i = 0; i < 5; i++) {
                new Thread(new WordThread(word)).start();
            }
        }

        scanner.close();
    }
}
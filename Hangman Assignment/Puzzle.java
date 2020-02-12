import java.util.ArrayList;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Puzzle {
    String word;
    String visibleWord;
    ArrayList<String> GuessedLetters;
    ArrayList<String> words = new ArrayList<String>();
    
    public Puzzle() {
        this.word = "Starcraft";
        this.visibleWord = "";
        this.GuessedLetters = new ArrayList<String>();
        this.words = new ArrayList<String>();
        
        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);
            
            while (scanner.hasNext()) {
                this.words.add(scanner.next());
            }
            
            scanner.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        this.word = this.words.get((int)(Math.random() * ((this.words.size() - 0) + 1) + 0));
        
        createVisibleWord();
    }
    
    public void show() {
        System.out.println("Word: " + visibleWord);
        System.out.print("Guesses: ");
        for (int i = 0; i < GuessedLetters.size(); i++) {
            System.out.print(GuessedLetters.get(i) + " ");
        }
        System.out.println("");
    }
    
    public void createVisibleWord() {
        for (int i = 0; i < this.word.length(); i++) {
            this.visibleWord = this.visibleWord + "_";
        }
    }
    
    public boolean makeGuess(String guess) {
        boolean foundLetter = false;
        for (int i = 0; i < this.word.length(); i++) {
            if (this.word.substring(i, i+1).equalsIgnoreCase(guess)) {
                String b = this.visibleWord.substring(0, i);
                String a = this.visibleWord.substring(i + 1, this.visibleWord.length());
                this.visibleWord = b + word.charAt(i) + a;
                foundLetter = true;
            }
        }
        
        if (foundLetter == true) {
            return true;
        } else {
            this.GuessedLetters.add(guess);
            return false;
        }
    }
    
    public boolean isUnsolved() {
        if (this.word.equalsIgnoreCase(visibleWord)) {
            return false;
        } else {
            return true;
        }
    }
    
    public String getWord() {
        return this.word;
    }
    
    public boolean check(String guess) {
        if (guess != null) {
            if (guess.length() == 1) {
                if ((Character.isLetter(guess.charAt(0)) == true)) {
                    return true;
                } else {
                    System.out.println("Error. Guess is not a letter.");
                }
            } else {
                System.out.println("Error. Guess does not have length of 1.");
            }
        } else {
            System.out.println("Error. Guess is null.");
        }
        
        return false;
    }
}

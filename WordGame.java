import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

// Class to handle game logic
public class WordGame {
    private static final char[] VOWELS = { 'a', 'e', 'i', 'o', 'u' };
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static String[] WORD_LIST;

    private Random random = new Random();
    private List<Character> selectedLetters;
    protected char[] word;
    private boolean[] indexUsed;

    public WordGame() {
        // Load words from the text file
        WORD_LIST = loadWordList("src/main/java/words.txt");

        int wordLength = random.nextInt(4) + 3;
        word = new char[wordLength];
        Arrays.fill(word, '_');

        selectedLetters = new ArrayList<>();
        generateLetters();
        indexUsed = new boolean[wordLength];
    }

    private String[] loadWordList(String fileName) {
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(fileName));
        } catch (Exception e) {
            System.out.println("Error reading word list: " + e.getMessage());
            return new String[0]; // Return an empty list if there's an error
        }
        return lines.toArray(new String[0]);
    }

    private void generateLetters() {
        // Ensure at least one vowel
        selectedLetters.clear();
        selectedLetters.add(VOWELS[random.nextInt(VOWELS.length)]);
        // Fill the rest with random letters
        for (int i = 1; i < 4; i++) {
            selectedLetters.add(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
    }

    public boolean makeMove(String letterInput, int wordIndex) {
        if (letterInput.length() != 1) {
            System.out.println("Invalid letter input. Enter a single character.");
            return false;
        }
        char chosenLetter = letterInput.charAt(0);

        if (!selectedLetters.contains(chosenLetter)) {
            System.out.println("Invalid letter. Choose from the available letters.");
            return false;
        }

        if (wordIndex < 0 || wordIndex >= word.length) {
            System.out.println("Invalid index. Try again.");
            return false;
        }

        if (word[wordIndex] != '_') {
            System.out.println("That index is already filled. Try again.");
            return false;
        }

        word[wordIndex] = chosenLetter;
        generateLetters();
        return true;
    }

    public boolean isWordComplete() {
        for (char c : word) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    public boolean isWordValid() {
        String finalWord = new String(word);
        return Arrays.asList(WORD_LIST).contains(finalWord);
    }

    public void displayGameState() {
        System.out.println("\nCurrent word: " + Arrays.toString(word));
        System.out.println("Available letters to choose from: " + selectedLetters.toString().replaceAll("[\\[\\],]", ""));
    }

    public int getWordLength() {
        return word.length;
    }
}

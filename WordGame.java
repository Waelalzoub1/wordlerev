import java.util.Random;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class WordGame {

    private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] WORD_LIST = {"cat", "dog", "sun", "run", "ant", "egg", "sea", "sky"}; // Example word list

    private Random random = new Random();
    private List<Character> selectedLetters; // Changed to List
    private char[] word;
    private boolean[] indexUsed;

    public WordGame() {
        int wordLength = random.nextInt(4) + 3; // Random length between 3 and 6
        word = new char[wordLength];
        Arrays.fill(word, '_'); // Initialize with underscores

        selectedLetters = new ArrayList<>();
        generateLetters(); // Populate the list
        indexUsed = new boolean[wordLength];
    }

    private void generateLetters() {
        // Ensure at least one vowel
        selectedLetters.add(VOWELS[random.nextInt(VOWELS.length)]);
        // Fill the rest with random letters
        for (int i = 1; i < 4; i++) {
            selectedLetters.add(ALPHABET[random.nextInt(ALPHABET.length)]);
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Word Game!");
        System.out.println("Try to form a word using the following letters:");
        System.out.println(selectedLetters);
        System.out.println("The word has " + word.length + " letters.");

        while (true) {
            System.out.println("Current word: " + Arrays.toString(word));
            System.out.println("Available letters: " + selectedLetters);

            System.out.print("Choose a letter from the available letters and an index (1-" + word.length + "): ");
            String letterInput = scanner.next();
            int wordIndex = scanner.nextInt() - 1; // Adjust index to be 0-based

            if (letterInput.length() != 1) {
                System.out.println("Invalid letter input. Enter a single character.");
                continue;
            }

            char chosenLetter = letterInput.charAt(0);

            if (!selectedLetters.contains(chosenLetter)) {
                System.out.println("Invalid letter. Choose from the available letters.");
                continue;
            }

            if (wordIndex < 0 || wordIndex >= word.length) {
                System.out.println("Invalid index. Try again.");
                continue;
            }

            if (word[wordIndex] != '_') {
                System.out.println("That index is already filled. Try again.");
                continue;
            }

            word[wordIndex] = chosenLetter;
            selectedLetters.remove(Character.valueOf(chosenLetter)); // Remove the chosen letter

            System.out.println("Current word: " + Arrays.toString(word));

            boolean allFilled = true;
            for (char c : word) {
                if (c == '_') {
                    allFilled = false;
                    break;
                }
            }

            if (allFilled) {
                String finalWord = new String(word);
                if (Arrays.asList(WORD_LIST).contains(finalWord)) {
                    System.out.println("Congratulations! You formed the word: " + finalWord);
                } else {
                    System.out.println("Sorry, that word is not in the list.");
                }
                break;
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        WordGame game = new WordGame();
        game.play();
    }
}

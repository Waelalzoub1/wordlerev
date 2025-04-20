import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

// Class to manage the game flow
public class GameManager {
    private int score;
    private Random random = new Random();

    public GameManager() {
        score = 0;
    }

    public void startGame() {
        int round = 0;
        int desiredRounds = 5;
        WordGame game = new WordGame();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nWelcome to the Word Game!");
        System.out.println("Form a word by placing the available letters in the empty spaces (_)");
        System.out.print("how many rounds would you like to play? ");
        desiredRounds = scanner.nextInt();
        System.out.println("Let's start!");
        System.out.println("--------------------------------------------------------------");

        // Main game loop
        while (true) {
            game.displayGameState();
            System.out.print("Choose a letter from the available letters and an index (1-" + game.getWordLength() + "): ");
            String letterInput = scanner.next();
            int wordIndex = scanner.nextInt() - 1;
            
            if (game.makeMove(letterInput, wordIndex)) {
                if (game.isWordComplete()) {
                    round++;
                    if (game.isWordValid()) {
                        score += game.getWordLength();
                        System.out.println("Congratulations! You formed a valid word. current score: " + score);
                    } else {
                        System.out.println("Sorry, that word is not in the list. let's play another round.");
                        System.out.println("your score is: " + score);
                    }
                    game = new WordGame();
                }
                System.out.println("--------------------------------------------------------------");
            }
            if (round == desiredRounds) break;
        }
        if ((score/2)+1 == desiredRounds)
        System.out.println("Congrats you won with a score of: " + score);
        else System.out.println("You lost with a score of: " + score); scanner.close();
    }

    public static void main(String[] args) {
        GameManager manager = new GameManager();
        manager.startGame();
    }
}

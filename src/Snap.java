import java.util.Scanner;
import java.util.Timer;

public class Snap extends CardGame {
    Commands commands = new Commands();
    Scanner scanner = new Scanner(System.in);
    boolean gameWin = false;

    Timer timer = new Timer();


    public Snap(String name) {
        super(name);

    }

    public String userInput() {
        return commands.getInput();
    }

    private void restartSnap() {
        if (commands.restartGame().equals("y")) {
            System.out.println("Restarting game");
            gameWin = false;
            snapGame();
        } else {
            gameWin = true;
            System.out.println("Good bye!");
        }
    }


    public void snapLoop() {
        while (!gameWin) {
            Card currentCard = dealCard();
            System.out.println("Press enter to deal card");
            userInput();
            Card nextCard = dealCard();
            System.out.println("Press enter to deal card");
            if (currentCard.getSymbol().equals(nextCard.getSymbol())) {
                String winningCondition = userInput();
                if (winningCondition.equals("snap")) {
                    gameWin = true;
                    System.out.println("Congratulations, you've won!");
                    System.out.println("Would you like to play again? Y/N");
                    restartSnap();
                }
            }
        }
    }

    public void snapGame() {
        createDeck();
        sortCards(CardSorting.byValue);
        System.out.println("Please press enter to start the game");
        userInput();
        snapLoop();

    }
}


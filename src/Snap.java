import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Snap extends CardGame {
    Commands commands = new Commands();
    Scanner scanner = new Scanner(System.in);
    boolean gameWin = false;

    Player player = new Player();

    int score = player.getScore();

    Card currentCard = new Card();
    Card nextCard = new Card();



    public Snap(String name) {
        super(name);

    }

    public String userInput() {
        return commands.getInput();
    }

    public int incrementScore() {
        return score++;
    }

    private void restartSnap() {
        if (commands.restartGame().equals("y")) {
            System.out.println("Restarting the game");
            System.out.println("Your score so far: " + score);
            gameWin = false;
            snapLoop();
        } else {
            gameWin = true;
            System.out.println("Good bye! Your score was: " + score);
        }
    }

    public boolean snapHappened() {
        if (currentCard.getSymbol().equals(nextCard.getSymbol())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userSnapped(String input) {
        if (input.equals("snap")) {
            return true;

        }
        return false;
    }



    public void snapLoop() {
        System.out.println("Please press enter to start the game");
        userInput();

        while (!gameWin) {
            currentCard = dealCard();
            System.out.println("Press enter to deal card");
            String input = userInput();
            if (userSnapped(input) && !snapHappened()) {
                score--;
                System.out.println("IT WASN'T A SNAP, YOU'RE LOSING A POINT");
                System.out.println("Your score: " + score);
            }
             else if (snapHappened() && userSnapped(input)) {
                    incrementScore();
                    gameWin = true;
                    System.out.println("Congratulations, you've won!");
                    System.out.println("Would you like to play again? Y/N");
                    restartSnap();
                }
            nextCard = currentCard;

            }
        }
    // END OF LOOP



    public void snapGame() {
        createDeck();
        sortCards(CardSorting.shuffle);
        System.out.println("Welcome to the game of Snap");
        String playerName = player.getPlayerName();
        System.out.println("Hello " + playerName);

        snapLoop();

    }
}


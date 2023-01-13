import java.util.Scanner;
import java.util.Timer;

public class Snap extends CardGame {
    Commands commands = new Commands();
    boolean gameWin = false;
    Timer timer = new Timer();

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

    public int incrementScore(){
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
            System.out.println("Good bye!");
        }
    }

    public boolean snapHappened(){
        if (currentCard.getSymbol().equals(nextCard.getSymbol())) {
            return true;
        } else {
           return false;
        }
    }

    public String userSnapper = userInput();


    public void snapLoop() {
        System.out.println("Please press enter to start the game");
        userInput();
            currentCard = dealCard();

        while (!gameWin) {
            System.out.println("Press enter to deal card");
            userInput();
            if (userSnapper.equals("snap") && !snapHappened()){
                score--;
                System.out.println("IT WASN'T A SNAP, YOUR SCORE " + score);
            } else {
                nextCard = dealCard();
            }
            if (snapHappened() && userSnapper.equals("snap")) {
                    incrementScore();
                    gameWin = true;
                    System.out.println("Congratulations, you've won!");
                    System.out.println("Would you like to play again? Y/N");
                    restartSnap();
                }
            }
        }

    public void snapGame() {
        createDeck();
        sortCards(CardSorting.byValue);
        System.out.println("Welcome to the game of Snap");
        String playerName = player.getPlayerName();
        System.out.println("Hello " + playerName);

        snapLoop();

    }
}


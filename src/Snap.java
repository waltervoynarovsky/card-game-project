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

    int countdown = 2000;

    Timer timer = new Timer("Timer", true);





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
            System.out.println("Good bye! Your score was: " + score);
        }
    }

    public boolean snapHappened(){
        if (currentCard.getSymbol().equals(nextCard.getSymbol())) {
            return true;
        } else {
           return false;
        }
    }

    public boolean userSnapped(String input){
       if (input.equals("snap")){
        return true;

       }
       return false;
    }

    public void setTimer() { // TIMER
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                nextCard = dealCard();
                System.out.println("You missed a snap!");
            }
        };
        timer.schedule(task, 2000);

    }

    public void snapLoop() {
        System.out.println("Please press enter to start the game");
        userInput();

        while (!gameWin) {
             currentCard = dealCard();
            System.out.println("Press enter to deal card");
            String input = userInput();
            if (userSnapped(input) && !snapHappened()){
                score--;
                System.out.println("IT WASN'T A SNAP, YOU'RE LOSING A POINT");
                System.out.println("Your score: " + score);
            }
            nextCard = currentCard;
            if (snapHappened()) {
                    setTimer(); // TIMER
                    if (userSnapped(input)){
                    timer.cancel(); // TIMER
                    incrementScore();
                    gameWin = true;
                    System.out.println("Congratulations, you've won!");
                    System.out.println("Would you like to play again? Y/N");
                    restartSnap();
                    }
                }
            } // END OF LOOP

//        System.out.println("End of  Game Loop");
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


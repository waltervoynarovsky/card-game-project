import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Snap extends CardGame {
    Commands commands = new Commands();
    Scanner scanner = new Scanner(System.in);
    boolean gameWin = false;

    Player player1 = new Player();

    Player player2 = new Player();

    int score1 = player1.getScore();
    int score2 = player2.getScore();

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

    public int incrementScore(Player player) {
        return player.setScore(player.getScore() + 1);
    }

    private void restartSnap() {
        if (commands.restartGame().equals("y")) {
            System.out.println("Restarting the game");
            System.out.println("Your score so far: " + score1);
            gameWin = false;
            onePlayerSnapLoop();
        } else {
            gameWin = true;
            System.out.println("Good bye! Your score was: " + score1);
        }
    }

    private void restart2ManSnap() {
        if (commands.restartGame().equals("y")) {
            System.out.println("Restarting the game");
            gameWin = false;
            twoPlayerSnapLoop();
        } else {
            gameWin = true;
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

//    public void setTimer() { // TIMER
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            public void run() {
//                nextCard = dealCard();
//                System.out.println("You missed a snap!");
//            }
//        };
//        timer.schedule(task, 2000);
//
//    }

    public void onePlayerSnapLoop() {
        String player1Name = player1.getPlayerName();
        System.out.println("Hello " + player1Name);
        System.out.println("Please press enter to start the game");
        userInput();

        while (!gameWin) {
            currentCard = dealCard(player1Deck());
            System.out.println("Press enter to deal card");
            String input = userInput();
            if (userSnapped(input) && !snapHappened()) {
                score1--;
                System.out.println("IT WASN'T A SNAP, YOU'RE LOSING A POINT");
                System.out.println("Your score: " + score1);
            }
            nextCard = currentCard;
            if (snapHappened() && userSnapped(input)) {
                    timer.cancel(); // TIMER
                    incrementScore(player1);
                    gameWin = true;
                    System.out.println("Congratulations, you've won!");
                    System.out.println("Would you like to play again? Y/N");
                    restartSnap();
                }
            }
        } // END OF LOOP

//        System.out.println("End of  Game Loop");


    public ArrayList<Card> player1Deck() {
        ArrayList<Card> player1Deck = new ArrayList<>();
        System.out.println(getDeck());
        for (int i = 0; i < 25; i++) {
            player1Deck.add(getDeck().get(i));
            getDeck().remove(i);
        }

        return player1Deck;
    }

    public ArrayList<Card> player2Deck() {
        ArrayList<Card> player2Deck = new ArrayList<>();
        System.out.println(getDeck());
        for (int i = 0; i < 25; i++) {
            player2Deck.add(getDeck().get(i));
            getDeck().remove(i);
        }
        return player2Deck;
    }


    public void twoPlayerSnapLoop() {
        ArrayList<Card> cardsPlayed = new ArrayList<>();
        System.out.println("Please enter player 1 name");
        String player1Name = player1.getPlayerName();
        System.out.println("Please enter player 2 name");
        String player2Name = player2.getPlayerName();
        System.out.println("Hello " + player1Name + " and " + player2Name);
        player1Deck();
        player2Deck();
        System.out.println("Please press enter to start the game");
        userInput();
        while (!gameWin) {
            userInput();
            Card player1Card = dealCard(player1Deck());
            cardsPlayed.add(player1Card);
            System.out.println("Player 1 turn, please press enter");
            String input = userInput();
            if (userSnapped(input) && !snapHappened()) {
                score1--;
                System.out.println("IT WASN'T A SNAP, YOU'RE LOSING A POINT");
                System.out.println("Your score: " + score1);
            } else if (userSnapped(input) && !snapHappened()){
                incrementScore(player1);
                player1Deck().addAll(cardsPlayed);
            }
            System.out.println("Player 2 turn, please press enter");
            Card player2Card = dealCard(player2Deck());
            cardsPlayed.add(player2Card);
            userInput();
            if (userSnapped(input) && !snapHappened()) {
                score2--;
                System.out.println("IT WASN'T A SNAP, YOU'RE LOSING A POINT");
                System.out.println("Your score: " + score2);
            } else if (userSnapped(input) && !snapHappened()){
                incrementScore(player2);
                player2Deck().addAll(cardsPlayed);
            }

            if (player1Deck().size() == 0) {
                System.out.println("Congratulations, " + player2Name + " has won!" );
            } else if (player2Deck().size() == 0) {
                System.out.println("Congratulations, " + player1Name + " has won!");
                gameWin = true;
                System.out.println("Congratulations, you've won!");
                System.out.println("Would you like to play again? Y/N");
                restartSnap();
            }
        }
    } // END OF LOOP







    public void snapGame() {
        createDeck();
        sortCards(CardSorting.byValue);
        System.out.println("Welcome to the game of Snap");
        System.out.println("Single player or 2 players game? 1/2");
        if (commands.getPlayersNumber() == 1){
            onePlayerSnapLoop();

        } else if (commands.getPlayersNumber() == 2){
            twoPlayerSnapLoop();
        } else {
            System.out.println("Please Enter either '1' or '2'");
        }




    }
}


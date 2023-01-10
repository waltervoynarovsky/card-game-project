public class Main {
    public static void main(String[] args) {

        CardGame cardgame = new CardGame("Snap");
        cardgame.createDeck();
//        System.out.println(cardgame.getDeck());
//        cardgame.dealCard();
//        cardgame.dealCard();
//        cardgame.dealCard();
        System.out.println(cardgame.getDeck().size());
        cardgame.sortCards(CardSorting.byValue);
        cardgame.sortCards(CardSorting.shuffle);

    }
}
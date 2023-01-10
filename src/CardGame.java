import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardGame {
    private String name;
    private static ArrayList<Card> deckOfCards = new ArrayList<>(52);

    private final String[] suit = {"♠", "♣", "♥", "♦"};
    private final String[] symbol = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    private final int[] value = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};


    public void createDeck() {

        for (int i = 0; i < suit.length; i++) {
            for (int j = 0; j < symbol.length; j++) {
                deckOfCards.add(new Card(suit[i], symbol[j], value[j]));
            }
        }
    }

    public CardGame(String name) {
        this.name = name;
    }

    public ArrayList<Card> getDeck() {
        return deckOfCards;
    }

    public Card dealCard() {
        Card topCard = deckOfCards.get(deckOfCards.size() - 1);
        deckOfCards.remove(deckOfCards.size() - 1);
        System.out.println(topCard);
        return topCard;
    }

    public void sortCards(CardSorting cardSorting) {
        switch (cardSorting) {
            case byValue:
                deckOfCards.sort(new SortDeckInNumberOrder());
                break;
            case bySuit:
                deckOfCards.sort(new SortDeckIntoSuits());
                break;
            case shuffle:
                Collections.shuffle(deckOfCards);
                break;

        }
        for (Card card : deckOfCards) {
            System.out.println(card);
        }

    }

}
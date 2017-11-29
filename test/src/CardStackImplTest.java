import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CardStackImplTest {
    private CardStackImpl cardStack = new CardStackImpl(StackPosition.FOUR);

    @Test //[1]
    public void setUpNewStackTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.HEARTS, Rank.EIGHT));
        cardList.add(new Card(Suit.CLUBS, Rank.SEVEN));
        cardList.add(new Card(Suit.DIAMONDS, Rank.TEN));
        cardList.add(new Card(Suit.CLUBS, Rank.JACK));
        cardList.add(new Card(Suit.SPADES, Rank.THREE));
        cardList.add(new Card(Suit.CLUBS, Rank.KING));

        cardStack.setUpNewStack(cardList);

        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[2]
    public void putCardOnStackTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.CLUBS, Rank.SEVEN));
        cardList.add(new Card(Suit.DIAMONDS, Rank.TEN));
        cardList.add(new Card(Suit.CLUBS, Rank.KING));

        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.SEVEN));
        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.TEN));
        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.KING));

        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[3]
    public void removeCardFromStackTest() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(Suit.CLUBS, Rank.SEVEN));
        cardList.add(new Card(Suit.DIAMONDS, Rank.TEN));
        cardList.add(new Card(Suit.CLUBS, Rank.KING));

        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.SEVEN));
        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.TEN));
        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.KING));

        Card card = cardList.get(2);
        cardStack.removeCardFromStack(card);

        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(2);
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }
}
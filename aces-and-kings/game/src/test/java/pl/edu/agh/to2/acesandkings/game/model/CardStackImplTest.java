package pl.edu.agh.to2.acesandkings.game.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;

import java.util.ArrayList;
import java.util.List;

public class CardStackImplTest {
    private CardStackImpl cardStack;
    private List<Card> cardList;

    @Before
    public void setUp() throws Exception {
        cardStack = new CardStackImpl(StackPosition.CLUBS_ACE);

        cardList = new ArrayList<>();
        cardList.add(new Card(Suit.CLUBS, Rank.ACE));
        cardList.add(new Card(Suit.CLUBS, Rank.TWO));
        cardList.add(new Card(Suit.CLUBS, Rank.THREE));

        cardStack.setUpNewStack(cardList);
    }

    @Test //[1]
    public void setUpNewStackTest() {
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[2]
    public void removeCardFromStackWithParamTest() {
        Card card = cardList.get(cardList.size() - 1);
        cardStack.removeCardFromStack(card);
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        card = cardList.get(cardList.size() - 1);
        cardStack.removeCardFromStack(card);
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        card = cardList.get(cardList.size() - 1);
        cardStack.removeCardFromStack();
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
    }

    @Test //[3]
    public void removeIncorrectCardFromStackWithParamTest() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        cardStack.removeCardFromStack(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[4]
    public void removeCardFromStackWithNoParamTest() {
        cardStack.removeCardFromStack();
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(cardList.size() - 1);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        cardStack.removeCardFromStack();
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(cardList.size() - 1);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
    }

    @Test //[5]
    public void removeCardFromEmptyStackWithNoParamTest() {
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
    }

    @Test //[6]
    public void changeStackStateTest() {
        Assert.assertTrue(cardStack.getState().equals(State.INACTIVE));
        cardStack.changeStackState();
        Assert.assertTrue(cardStack.getState().equals(State.ACTIVE));
        cardStack.changeStackState();
        Assert.assertTrue(cardStack.getState().equals(State.INACTIVE));
    }

    @Test //[7]
    public void putCardOnAcesStackTest() {
        // bad card for this stack
        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        // add correct card
        Card card = new Card(Suit.CLUBS, Rank.FOUR);
        cardStack.putCardOnStack(card);
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.add(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[8]
    public void putCardOnKingsStackTest() {
        cardStack = new CardStackImpl(StackPosition.HEART_KING);

        cardList = new ArrayList<>();
        cardList.add(new Card(Suit.HEARTS, Rank.KING));
        cardList.add(new Card(Suit.HEARTS, Rank.QUEEN));
        cardList.add(new Card(Suit.HEARTS, Rank.JACK));

        cardStack.setUpNewStack(cardList);

        // bad card for this stack
        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        // add correct card
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        cardStack.putCardOnStack(card);
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.add(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[8]
    public void putCardOnEmptyStackTest() {
        cardStack = new CardStackImpl(StackPosition.FOUR);

        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(cardStack.getStack().size() == 1);

        // adding another card
        cardStack.putCardOnStack(new Card(Suit.HEARTS, Rank.TEN));
        Assert.assertTrue(cardStack.getStack().size() == 1);
    }
}
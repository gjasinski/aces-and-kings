package pl.edu.agh.to2.acesandkings.game.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CardStackImplTest {
    private CardStackImpl cardStack;
    private List<Card> cardList;

    @Before
    public void setUp() throws Exception {
        cardStack = new CardStackImpl(StackPosition.FOUR);
        cardList = new ArrayList<>();
        cardList.add(new Card(Suit.CLUBS, Rank.SEVEN));
        cardList.add(new Card(Suit.DIAMONDS, Rank.TEN));
        cardList.add(new Card(Suit.CLUBS, Rank.KING));

        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.SEVEN));
        cardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.TEN));
        cardStack.putCardOnStack(new Card(Suit.CLUBS, Rank.KING));
    }

    @Test //[1]
    public void putCardOnStackTest() {
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[2]
    public void setUpNewStackTest() {
        cardStack.setUpNewStack(cardList);
        Assert.assertTrue(cardStack.getStack().equals(cardList));
    }

    @Test //[3]
    public void removeCardFromStackWithParamTest() {
        Card card = cardList.get(cardList.size() - 1);
        cardStack.removeCardFromStack(card);
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(card);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        card = new Card(Suit.CLUBS, Rank.ACE);
        cardStack.removeCardFromStack(card);
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

    @Test //[4]
    public void removeCardFromEmptyStackWithParamTest() {
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
    }

    @Test //[5]
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

    @Test //[6]
    public void removeCardFromEmptyStackWithNoParamTest() {
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 0);
    }

    @Test //[7]
    public void changeStackStateTest() {
        Assert.assertTrue(cardStack.getState().equals(State.INACTIVE));
        cardStack.changeStackState();
        Assert.assertTrue(cardStack.getState().equals(State.ACTIVE));
        cardStack.changeStackState();
        Assert.assertTrue(cardStack.getState().equals(State.INACTIVE));
    }
}
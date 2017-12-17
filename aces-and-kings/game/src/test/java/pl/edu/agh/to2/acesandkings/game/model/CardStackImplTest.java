package pl.edu.agh.to2.acesandkings.game.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;

import java.util.ArrayList;
import java.util.List;

public class CardStackImplTest {
    private CardStackImpl acesCardStack;
    private CardStackImpl kingsCardStack;
    private CardStackImpl middleCardStack;
    private List<Card> acesCardList;
    private List<Card> kingsCardList;
    private List<Card> middleCardList;

    @Before
    public void setUp() throws Exception {
        // ACES stack
        acesCardStack = new CardStackImpl(StackPosition.CLUBS_ACE);
        acesCardList = new ArrayList<>();
        acesCardList.add(new Card(Suit.CLUBS, Rank.ACE));
        acesCardList.add(new Card(Suit.CLUBS, Rank.TWO));
        acesCardList.add(new Card(Suit.CLUBS, Rank.THREE));
        acesCardStack.setUpNewStack(acesCardList);

        // KINGS stack
        kingsCardStack = new CardStackImpl(StackPosition.HEART_KING);
        kingsCardList = new ArrayList<>();
        kingsCardList.add(new Card(Suit.HEARTS, Rank.KING));
        kingsCardList.add(new Card(Suit.HEARTS, Rank.QUEEN));
        kingsCardList.add(new Card(Suit.HEARTS, Rank.JACK));
        kingsCardStack.setUpNewStack(kingsCardList);

        // MIDDLE stack
        middleCardStack = new CardStackImpl(StackPosition.THREE);
        middleCardList = new ArrayList<>();
        middleCardList.add(new Card(Suit.DIAMONDS, Rank.QUEEN));
        middleCardList.add(new Card(Suit.CLUBS, Rank.SEVEN));
        middleCardList.add(new Card(Suit.HEARTS, Rank.JACK));
        middleCardStack.setUpNewStack(middleCardList);
    }

    @Test //[1]
    public void setUpNewStackTest() {
        Assert.assertTrue(acesCardStack.getStack().equals(acesCardList));
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));
    }

    @Test //[2]
    public void removeCardFromAcesStackWithParamTest() {
        removeCardFromStackWithParamTest(acesCardStack, acesCardList);
    }

    @Test //[2]
    public void removeCardFromKingsStackWithParamTest() {
        removeCardFromStackWithParamTest(kingsCardStack, kingsCardList);
    }

    private void removeCardFromStackWithParamTest(CardStackImpl cardStack, List<Card> cardList) {
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

        cardStack.removeCardFromStack();
        // can't remove last card on KINGS/ACES stack position
        Assert.assertTrue(cardStack.getStack().equals(cardList));
        Assert.assertTrue(cardStack.getStack().size() == 1);
    }

    @Test //[3]
    public void removeCardFromMiddleStackWithParamTest() {
        Card card = middleCardList.get(middleCardList.size() - 1);
        middleCardStack.removeCardFromStack(card);
        Assert.assertFalse(middleCardStack.getStack().equals(middleCardList));
        middleCardList.remove(card);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));

        card = middleCardList.get(middleCardList.size() - 2);
        middleCardStack.removeCardFromStack(card);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));

        middleCardStack.changeStackState(State.ACTIVE);
        middleCardStack.removeCardFromStack(card);
        Assert.assertFalse(middleCardStack.getStack().equals(middleCardList));
        middleCardList.remove(card);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));

        card = middleCardList.get(middleCardList.size() - 1);
        middleCardStack.removeCardFromStack(card);
        Assert.assertFalse(middleCardStack.getStack().equals(middleCardList));
        middleCardList.remove(card);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));

        middleCardStack.removeCardFromStack();
        Assert.assertTrue(middleCardStack.getStack().size() == 0);
    }

    @Test //[4]
    public void removeIncorrectCardFromStackWithParamTest() {
        Card card = new Card(Suit.CLUBS, Rank.ACE);
        acesCardStack.removeCardFromStack(card);
        Assert.assertTrue(acesCardStack.getStack().equals(acesCardList));

        kingsCardStack.removeCardFromStack(card);
        Assert.assertTrue(kingsCardStack.getStack().equals(kingsCardList));

        middleCardStack.changeStackState(State.ACTIVE);
        middleCardStack.removeCardFromStack(card);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));
    }

    @Test //[5]
    public void removeCardFromAcesStackWithNoParamTest() {
        removeCardFromStackWithNoParamTest(acesCardStack, acesCardList);
    }

    @Test //[6]
    public void removeCardFromKingsStackWithNoParamTest() {
        removeCardFromStackWithNoParamTest(kingsCardStack, kingsCardList);
    }

    private void removeCardFromStackWithNoParamTest(CardStackImpl cardStack, List<Card> cardList) {
        cardStack.removeCardFromStack();
        Assert.assertFalse(cardStack.getStack().equals(cardList));
        cardList.remove(cardList.size() - 1);
        Assert.assertTrue(cardStack.getStack().equals(cardList));

        cardStack.removeCardFromStack();
        Assert.assertFalse(cardStack.getStack().equals(cardList));

//      can't remove last card on KINGS/ACES position
        cardStack.removeCardFromStack();
        Assert.assertTrue(cardStack.getStack().size() == 1);
    }

    @Test //[7]
    public void removeCardFromMiddleStackWithNoParamTest() {
        middleCardStack.removeCardFromStack();
        Assert.assertFalse(middleCardStack.getStack().equals(middleCardList));
        middleCardList.remove(middleCardList.size() - 1);
        Assert.assertTrue(middleCardStack.getStack().equals(middleCardList));

        middleCardStack.removeCardFromStack();
        Assert.assertFalse(middleCardStack.getStack().equals(middleCardList));

        middleCardStack.removeCardFromStack();
        Assert.assertTrue(middleCardStack.getStack().size() == 0);
    }

    @Test //[8]
    public void removeCardFromEmptyStackWithNoParamTest() {
        middleCardStack.removeCardFromStack();
        middleCardStack.removeCardFromStack();
        middleCardStack.removeCardFromStack();
        Assert.assertTrue(middleCardStack.getStack().size() == 0);
        middleCardStack.removeCardFromStack();
        Assert.assertTrue(middleCardStack.getStack().size() == 0);
    }

    @Test //[9]
    public void changeStackStateTest() {
        Assert.assertTrue(middleCardStack.getState().equals(State.INACTIVE));
        middleCardStack.changeStackState(State.ACTIVE);
        Assert.assertTrue(middleCardStack.getState().equals(State.ACTIVE));
        middleCardStack.changeStackState(State.INACTIVE);
        Assert.assertTrue(middleCardStack.getState().equals(State.INACTIVE));
    }

    @Test //[10]
    public void putCardOnAcesStackTest() {
        // bad card for this stack
        acesCardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(acesCardStack.getStack().equals(acesCardList));

        // add correct card
        Card card = new Card(Suit.CLUBS, Rank.FOUR);
        acesCardStack.putCardOnStack(card);
        Assert.assertFalse(acesCardStack.getStack().equals(acesCardList));
        acesCardList.add(card);
        Assert.assertTrue(acesCardStack.getStack().equals(acesCardList));
    }

    @Test //[11]
    public void putCardOnKingsStackTest() {
        // bad card for this stack
        kingsCardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(kingsCardStack.getStack().equals(kingsCardList));

        // add correct card
        Card card = new Card(Suit.HEARTS, Rank.TEN);
        kingsCardStack.putCardOnStack(card);
        Assert.assertFalse(kingsCardStack.getStack().equals(kingsCardList));
        kingsCardList.add(card);
        Assert.assertTrue(kingsCardStack.getStack().equals(kingsCardList));
    }

    @Test //[12]
    public void putCardOnEmptyStackTest() {
        acesCardStack = new CardStackImpl(StackPosition.FOUR);

        acesCardStack.putCardOnStack(new Card(Suit.DIAMONDS, Rank.THREE));
        Assert.assertTrue(acesCardStack.getStack().size() == 1);

        // adding another card
        acesCardStack.putCardOnStack(new Card(Suit.HEARTS, Rank.TEN));
        Assert.assertTrue(acesCardStack.getStack().size() == 1);
    }

    // TODO isRemoveCardFromStackAllowed
    // TODO isPutCardOnStackAllowed
}
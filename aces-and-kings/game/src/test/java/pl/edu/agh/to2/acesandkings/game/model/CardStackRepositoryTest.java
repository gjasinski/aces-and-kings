package pl.edu.agh.to2.acesandkings.game.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardStackRepositoryTest {
    private CardStackRepository cardStackRepository;

    @Before
    public void setUp() throws Exception {
        List<CardStackImpl> cardStackList = new ArrayList<>();

        cardStackList.add(new CardStackImpl(StackPosition.SPADES_ACE));
        cardStackList.add(new CardStackImpl(StackPosition.CLUBS_KING));
        cardStackList.add(new CardStackImpl(StackPosition.EIGHT));
        cardStackList.add(new CardStackImpl(StackPosition.ACE));

        List<Card> stack0 = new ArrayList<>();
        List<Card> stack1 = new ArrayList<>();
        List<Card> stack2 = new ArrayList<>();

        stack0.add(new Card(Suit.SPADES, Rank.ACE));
        stack0.add(new Card(Suit.SPADES, Rank.TWO));

        stack1.add(new Card(Suit.CLUBS, Rank.KING));

        stack2.add(new Card(Suit.DIAMONDS, Rank.TWO));
        stack2.add(new Card(Suit.HEARTS, Rank.FOUR));
        stack2.add(new Card(Suit.SPADES, Rank.JACK));
        stack2.add(new Card(Suit.CLUBS, Rank.QUEEN));

        cardStackList.get(0).setUpNewStack(stack0);
        cardStackList.get(1).setUpNewStack(stack1);
        cardStackList.get(2).setUpNewStack(stack2);

        cardStackRepository = new CardStackRepository();
        cardStackRepository.setCardStackList(cardStackList);
    }

    @Test //[1]
    public void setUpTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().size() == 4);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 2);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 4);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 0);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().get(1).equals(new Card(Suit.HEARTS, Rank.FOUR)));
    }

    @Test //[2]
    public void putCardOnStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 2);
        // put correct card on ACES stack
        cardStackRepository.putCardOnStack(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.THREE));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 3);

        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);
        // put correct card on KINGS stack
        cardStackRepository.putCardOnStack(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.QUEEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 2);

        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 0);
        // put card on empty stack
        cardStackRepository.putCardOnStack(StackPosition.ACE, new Card(Suit.SPADES, Rank.FIVE));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 1);

        //  put card on middle stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 4);
        cardStackRepository.putCardOnStack(StackPosition.EIGHT, new Card(Suit.SPADES, Rank.FIVE));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 5);
    }

    @Test //[3]
    public void setUpNewCardOrderTest() {
        List<Card> cardStack = cardStackRepository.getCardStackList().get(2).getStack();
        List<Card> newOrder = new ArrayList<>();
        newOrder.add(cardStack.get(3));
        newOrder.add(cardStack.get(1));
        newOrder.add(cardStack.get(0));
        newOrder.add(cardStack.get(2));

        cardStackRepository.setUpNewCardOrder(StackPosition.EIGHT, newOrder);
        Assert.assertTrue(newOrder.equals(cardStackRepository.getCardStackList().get(2).getStack()));
    }

    @Test //[4]
    public void removeCardFromStackTestWithParamTest() {
        // remove from ACES stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 2);
        cardStackRepository.removeCardFromStack(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.TWO));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 1);
//        can't remove incorrect card
        cardStackRepository.removeCardFromStack(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.TEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 1);

        // remove from KINGS stack
        cardStackRepository.putCardOnStack(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.QUEEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 2);
        cardStackRepository.removeCardFromStack(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.QUEEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);
//        can't remove incorrect card
        cardStackRepository.removeCardFromStack(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.TEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);

        // remove from middle stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 4);
//        remove first card - always possible
        cardStackRepository.removeCardFromStack(StackPosition.EIGHT, new Card(Suit.CLUBS, Rank.QUEEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 3);
//        removing cards from ACTIVE stack - always possible
        cardStackRepository.changeStackState(StackPosition.EIGHT, State.ACTIVE);
        cardStackRepository.removeCardFromStack(StackPosition.EIGHT, new Card(Suit.HEARTS, Rank.FOUR));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 2);
        cardStackRepository.removeCardFromStack(StackPosition.EIGHT, new Card(Suit.DIAMONDS, Rank.TWO));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 1);
        cardStackRepository.removeCardFromStack(StackPosition.EIGHT, new Card(Suit.SPADES, Rank.JACK));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 0);
    }

    @Test //[5]
    public void removeCardFromStackTestWithNoParamTest() {
        Optional<Card> card;
//        remove from ACES stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 2);
        card = cardStackRepository.removeCardFromStack(StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 1);
        Assert.assertTrue(card.isPresent() && card.get().equals(new Card(Suit.SPADES, Rank.TWO)));
//        can't remove last card from ACES stack
        card = cardStackRepository.removeCardFromStack(StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 1);
        Assert.assertTrue(!card.isPresent());

//        remove card from KINGS stack
        cardStackRepository.putCardOnStack(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.QUEEN));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 2);
        card = cardStackRepository.removeCardFromStack(StackPosition.CLUBS_KING);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);
        Assert.assertTrue(card.isPresent() && card.get().equals(new Card(Suit.CLUBS, Rank.QUEEN)));
//        can't remove last card from KINGS stack
        card = cardStackRepository.removeCardFromStack(StackPosition.CLUBS_KING);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);
        Assert.assertTrue(!card.isPresent());

//        remove from middle stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 4);
        card = cardStackRepository.removeCardFromStack(StackPosition.EIGHT);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 3);
        Assert.assertTrue(card.isPresent() && card.get().equals(new Card(Suit.CLUBS, Rank.QUEEN)));
        card = cardStackRepository.removeCardFromStack(StackPosition.EIGHT);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 2);
        Assert.assertTrue(card.isPresent() && card.get().equals(new Card(Suit.SPADES, Rank.JACK)));

//        remove from empty stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 0);
        card = cardStackRepository.removeCardFromStack(StackPosition.ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 0);
        Assert.assertTrue(!card.isPresent());
    }

    @Test //[6]
    public void changeStackStateTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getState().equals(State.INACTIVE));
        cardStackRepository.changeStackState(StackPosition.EIGHT, State.ACTIVE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getState().equals(State.ACTIVE));
        cardStackRepository.changeStackState(StackPosition.EIGHT, State.INACTIVE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getState().equals(State.INACTIVE));
    }

    @Test //[7]
    public void isRemoveCardFromStackAllowedTest() {
        // remove card from border stack
        Assert.assertTrue(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.TWO)));
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.ACE)));
        cardStackRepository.removeCardFromStack(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.TWO));
        // can't remove last card from border stack
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.ACE)));
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.KING)));
        // remove card from middle stack in State.INACTIVE
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.EIGHT, new Card(Suit.SPADES, Rank.JACK)));
        cardStackRepository.changeStackState(StackPosition.EIGHT, State.ACTIVE);
        // remove card from middle stack in State.ACTIVE
        Assert.assertTrue(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.EIGHT, new Card(Suit.SPADES, Rank.JACK)));
        // remove card from empty stack
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.ACE, new Card(Suit.CLUBS, Rank.QUEEN)));
        cardStackRepository.changeStackState(StackPosition.ACE, State.INACTIVE);
        Assert.assertFalse(cardStackRepository.isRemoveCardFromStackAllowed(StackPosition.ACE, new Card(Suit.CLUBS, Rank.QUEEN)));
    }

    @Test //[8]
    public void isPutCardOnStackAllowedTest() {
        // put on border stack
        Assert.assertTrue(cardStackRepository.isPutCardOnStackAllowed(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.THREE)));
        Assert.assertFalse(cardStackRepository.isPutCardOnStackAllowed(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.FOUR)));
        Assert.assertTrue(cardStackRepository.isPutCardOnStackAllowed(StackPosition.CLUBS_KING, new Card(Suit.CLUBS, Rank.QUEEN)));
        Assert.assertFalse(cardStackRepository.isPutCardOnStackAllowed(StackPosition.CLUBS_KING, new Card(Suit.SPADES, Rank.FOUR)));
        // put on middle stack
        Assert.assertFalse(cardStackRepository.isPutCardOnStackAllowed(StackPosition.EIGHT, new Card(Suit.SPADES, Rank.FOUR)));
        // put on empty stack
        Assert.assertTrue(cardStackRepository.isPutCardOnStackAllowed(StackPosition.ACE, new Card(Suit.SPADES, Rank.FOUR)));
    }
}
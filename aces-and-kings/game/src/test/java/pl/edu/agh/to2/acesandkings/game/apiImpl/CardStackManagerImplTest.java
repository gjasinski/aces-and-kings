package pl.edu.agh.to2.acesandkings.game.apiImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import java.util.ArrayList;
import java.util.List;

public class CardStackManagerImplTest {
    private CardStackRepository cardStackRepository;
    private CardStackManagerImpl cardStackManager;
    private CardStackImpl handCardStack;
    private CardStackImpl middleCardStack;
    private List<Card> cardsOnMiddleStack;

    @Before
    public void setUp() {
        List<CardStackImpl> cardStackList = new ArrayList<>();

        middleCardStack = new CardStackImpl(StackPosition.TWO);
        handCardStack = new CardStackImpl(StackPosition.HAND_STACK);
        cardStackList.add(middleCardStack);
        cardStackList.add(handCardStack);

        cardsOnMiddleStack = new ArrayList<>();

        cardsOnMiddleStack.add(new Card(Suit.SPADES, Rank.TWO));
        cardsOnMiddleStack.add(new Card(Suit.DIAMONDS, Rank.KING));

        cardStackList.get(0).setUpNewStack(cardsOnMiddleStack);

        cardStackRepository = new CardStackRepository();
        cardStackRepository.setCardStackList(cardStackList);

        cardStackManager = new CardStackManagerImpl(cardStackRepository);
    }

    @Test
    public void activateCardStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
        cardStackManager.activateCardStack(StackPosition.TWO, new Card(Suit.DIAMONDS, Rank.TWO));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.ACTIVE));
    }

    @Test
    public void deactivateCardStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
        cardStackManager.activateCardStack(StackPosition.TWO, new Card(Suit.DIAMONDS, Rank.TWO));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.ACTIVE));
        cardStackManager.deactivateCardStack();
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
    }

    @Test
    public void whenActivatingCardStackThenAllCardsShouldBeTransferredToHandStackWithExtraCardTest() {
        //given
        Card cardFromExtraStack = new Card(Suit.DIAMONDS, Rank.TWO);
        List<Card> cardsShouldBeOnHandStack = cardsOnMiddleStack;
        cardsShouldBeOnHandStack.add((cardFromExtraStack));

        //when
        cardStackManager.activateCardStack(StackPosition.TWO, cardFromExtraStack);

        //then
        Assert.assertEquals(0, middleCardStack.getStack().size());
        Assert.assertEquals(cardsShouldBeOnHandStack, handCardStack.getStack());
    }

    @Test
    public void whenDeactivatingCardStackThenAllCardsShouldBeTransferredToMiddleStackTest() {
        //given
        Card cardFromExtraStack = new Card(Suit.DIAMONDS, Rank.TWO);
        List<Card> cardsShouldBeOnHandStack = cardsOnMiddleStack;
        cardsShouldBeOnHandStack.add((cardFromExtraStack));

        //when
        cardStackManager.activateCardStack(StackPosition.TWO, cardFromExtraStack);
        cardStackManager.deactivateCardStack();

        //then
        Assert.assertEquals(0, handCardStack.getStack().size());
        Assert.assertEquals(cardsShouldBeOnHandStack, middleCardStack.getStack());
    }
}

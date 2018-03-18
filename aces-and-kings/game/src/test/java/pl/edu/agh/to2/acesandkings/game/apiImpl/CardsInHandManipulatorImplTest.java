package pl.edu.agh.to2.acesandkings.game.apiImpl;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardsInHandManipulatorImplTest {
    private CardStackRepository cardStackRepository;
    private CardsInHandManipulatorImpl cardsInHandManipulator;

    @Before
    public void setUp() {
        List<CardStackImpl> cardStackList = new ArrayList<>();

        cardStackList.add(new CardStackImpl(StackPosition.SPADES_ACE));
        cardStackList.add(new CardStackImpl(StackPosition.TWO));
        cardStackList.add(new CardStackImpl(StackPosition.HAND_STACK));

        List<Card> stack0 = new ArrayList<>();
        List<Card> stack1 = new ArrayList<>();

        stack0.add(new Card(Suit.SPADES, Rank.ACE));
        stack1.add(new Card(Suit.SPADES, Rank.THREE));
        stack1.add(new Card(Suit.SPADES, Rank.FOUR));
        stack1.add(new Card(Suit.SPADES, Rank.TWO));

        cardStackList.get(0).setUpNewStack(stack0);
        cardStackList.get(1).setUpNewStack(stack1);

        cardStackRepository = new CardStackRepository();
        cardStackRepository.setCardStackList(cardStackList);

        cardsInHandManipulator = new CardsInHandManipulatorImpl(cardStackRepository);
    }

    @Test
    public void moveCardFromHandToStackTest() {
        //given
        cardStackRepository.changeStackState(StackPosition.TWO, State.ACTIVE);
        cardStackRepository.moveCardsFromStackToStack(StackPosition.TWO, StackPosition.HAND_STACK);
        Optional<CardStackImpl> handStackOptional = cardStackRepository.getCardStackList()
                .stream()
                .filter(stack -> stack.getPosition() == StackPosition.HAND_STACK)
                .findAny();
        Optional<CardStackImpl> stackSpadeAceOptional = cardStackRepository.getCardStackList()
                .stream()
                .filter(stack -> stack.getPosition() == StackPosition.SPADES_ACE)
                .findAny();
        Assert.assertTrue(handStackOptional.isPresent());
        Assert.assertTrue(stackSpadeAceOptional.isPresent());
        CardStackImpl spadeAceStack = stackSpadeAceOptional.get();
        CardStackImpl handStack = handStackOptional.get();

        //when
        List<Card> cards = handStack.getStack().subList(0, 3);
        Assert.assertTrue(spadeAceStack.getStack().size() == 1);
        Assert.assertTrue(handStack.getStack().size() == 3);
        cardsInHandManipulator.moveCardFromHandToStack(cards.get(2), StackPosition.SPADES_ACE);
        Assert.assertTrue(spadeAceStack.getStack().size() == 2);
        Assert.assertTrue(handStack.getStack().size() == 2);
        cardsInHandManipulator.moveCardFromHandToStack(cards.get(0), StackPosition.SPADES_ACE);
        Assert.assertTrue(spadeAceStack.getStack().size() == 3);
        Assert.assertTrue(handStack.getStack().size() == 1);
        cardsInHandManipulator.moveCardFromHandToStack(cards.get(1), StackPosition.SPADES_ACE);
        Assert.assertTrue(spadeAceStack.getStack().size() == 4);
        Assert.assertTrue(handStack.getStack().size() == 0);
    }

    @Test
    public void setUpNewOrderTest() {
        List<Card> cards = cardStackRepository.getCardStackList().get(1).getStack().subList(0, 3);
        List<Card> newOrderedCards = Lists.reverse(cards);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().equals(cards));
        cardStackRepository.setUpNewCardOrder(StackPosition.TWO, newOrderedCards);
        Assert.assertFalse(cardStackRepository.getCardStackList().get(1).getStack().equals(cards));
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().equals(newOrderedCards));
    }
}

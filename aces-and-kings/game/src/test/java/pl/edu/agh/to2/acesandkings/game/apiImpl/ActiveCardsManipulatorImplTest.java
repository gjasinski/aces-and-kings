package pl.edu.agh.to2.acesandkings.game.apiImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import java.util.ArrayList;
import java.util.List;

public class ActiveCardsManipulatorImplTest {
    private CardStackRepository cardStackRepository;
    private ActiveCardsManipulatorImpl activeCardsManipulator;

    @Before
    public void setUp() throws Exception {
        List<CardStackImpl> cardStackList = new ArrayList<>();

        cardStackList.add(new CardStackImpl(StackPosition.SPADES_ACE));
        cardStackList.add(new CardStackImpl(StackPosition.SPADES_KING));
        cardStackList.add(new CardStackImpl(StackPosition.TWO));
        cardStackList.add(new CardStackImpl(StackPosition.THREE));

        List<Card> stack0 = new ArrayList<>();
        List<Card> stack1 = new ArrayList<>();
        List<Card> stack2 = new ArrayList<>();
        List<Card> stack3 = new ArrayList<>();

        stack0.add(new Card(Suit.SPADES, Rank.ACE));
        stack1.add(new Card(Suit.SPADES, Rank.KING));
        stack2.add(new Card(Suit.SPADES, Rank.THREE));
        stack2.add(new Card(Suit.SPADES, Rank.TWO));
        stack3.add(new Card(Suit.SPADES, Rank.QUEEN));
        stack3.add(new Card(Suit.SPADES, Rank.JACK));

        cardStackList.get(0).setUpNewStack(stack0);
        cardStackList.get(1).setUpNewStack(stack1);
        cardStackList.get(2).setUpNewStack(stack2);
        cardStackList.get(3).setUpNewStack(stack3);

        cardStackRepository = new CardStackRepository();
        cardStackRepository.setCardStackList(cardStackList);

        activeCardsManipulator = new ActiveCardsManipulatorImpl(cardStackRepository);
    }

    @Test
    public void moveActiveCardToAcesStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 2);
        activeCardsManipulator.moveActiveCardToStack(StackPosition.TWO, StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 1);
        activeCardsManipulator.moveActiveCardToStack(StackPosition.TWO, StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(2).getStack().size() == 0);
    }

    @Test
    public void moveActiveCardToKingsStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 2);
        activeCardsManipulator.moveActiveCardToStack(StackPosition.THREE, StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 1);
        activeCardsManipulator.moveActiveCardToStack(StackPosition.THREE, StackPosition.SPADES_ACE);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(3).getStack().size() == 0);
    }

    @Test
    public void moveCardFromAcesToKingsStackTest() {
        for (int i = 1; i < Rank.values().length - 1; i++) {
            cardStackRepository.putCardOnStack(StackPosition.SPADES_ACE, new Card(Suit.SPADES, Rank.values()[i]));
        }
//        Aces stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 12);
//        Kings stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 1);

        activeCardsManipulator.moveCardFromOneBorderStackToOther(StackPosition.SPADES_ACE, StackPosition.SPADES_KING);
//        Aces stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 11);
//        Kings stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 2);
    }

    @Test
    public void moveCardFromKingsToAcesStackTest() {
        for (int i = Rank.values().length - 2; i >0 ; i--) {
            cardStackRepository.putCardOnStack(StackPosition.SPADES_KING, new Card(Suit.SPADES, Rank.values()[i]));
        }
//        Kings stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 12);
//        Aces stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 1);

        activeCardsManipulator.moveCardFromOneBorderStackToOther(StackPosition.SPADES_KING, StackPosition.SPADES_ACE);
//       Kings stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(1).getStack().size() == 11);
//       Aces stack
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getStack().size() == 2);
    }

}

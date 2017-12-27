package pl.edu.agh.to2.acesandkings.game.apiImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CardStackManagerImplTest {
    private CardStackRepositoryImpl cardStackRepository;
    private CardStackManagerImpl cardStackManager;

    @Before
    public void setUp() throws Exception {
        List<CardStackImpl> cardStackList = new ArrayList<>();

        cardStackList.add(new CardStackImpl(StackPosition.TWO));

        List<Card> stack0 = new ArrayList<>();

        stack0.add(new Card(Suit.SPADES, Rank.TWO));
        stack0.add(new Card(Suit.DIAMONDS, Rank.KING));

        cardStackList.get(0).setUpNewStack(stack0);

        cardStackRepository = new CardStackRepositoryImpl();
        cardStackRepository.setCardStackList(cardStackList);

        cardStackManager = new CardStackManagerImpl(cardStackRepository);
    }

    @Test
    public void activateCardStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
        cardStackManager.activateCardStack(StackPosition.TWO);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.ACTIVE));
    }

    @Test
    public void deactivateCardStackTest() {
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
        cardStackManager.activateCardStack(StackPosition.TWO);
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.ACTIVE));
        cardStackManager.deactivateCardStack();
        Assert.assertTrue(cardStackRepository.getCardStackList().get(0).getState().equals(State.INACTIVE));
    }
}

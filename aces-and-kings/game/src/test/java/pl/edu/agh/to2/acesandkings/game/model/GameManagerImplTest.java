package pl.edu.agh.to2.acesandkings.game.model;

import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class GameManagerImplTest {
    @Test
    public void afterInitializingNewGameThenOnMiddleAndExtraStackShouldBe96Cards() {
        //given
        int onMiddleStacksShouldBe96Cards = 96;
        GameManagerImpl gameManager = new GameManagerImpl();

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        int numberOfCardsOnMiddleStacks = countCardsOnMiddleStacks(cardStackObservables);
        assertEquals(onMiddleStacksShouldBe96Cards, numberOfCardsOnMiddleStacks);
    }

    @Test
    public void afterInitializingNewGameThenDifferenceInSizeOfMiddleStackShouldBeMaxOneCard() {
        //given
        GameManagerImpl gameManager = new GameManagerImpl();

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        Optional<Integer> maxNumberOfCardsOnMiddleStack = getMiddleCardStackSizeStream(cardStackObservables)
                .max(Comparator.naturalOrder());
        Optional<Integer> minNumberOfCardsOnMiddleStack = getMiddleCardStackSizeStream(cardStackObservables)
                .min(Comparator.naturalOrder());
        assertTrue(minNumberOfCardsOnMiddleStack.isPresent());
        assertTrue(maxNumberOfCardsOnMiddleStack.isPresent());
        assertTrue(maxNumberOfCardsOnMiddleStack.get() - minNumberOfCardsOnMiddleStack.get() <= 1);
    }

    private Stream<Integer> getMiddleCardStackSizeStream(List<CardStackObservable> cardStackObservables) {
        return cardStackObservables
                    .stream()
                    .filter(stack -> stack.getPosition().isMiddleStackPosition())
                    .map(stack -> stack.getUnmodifiableObservableStack()
                            .size());
    }

    @Test
    public void afterInitializingNewGameThenOnBorderStacksShouldBe8Cards() {
        //given
        int onBorderStacksShouldBe8Cards = 8;
        GameManagerImpl gameManager = new GameManagerImpl();

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        int numberOfCardsOnMiddleStacks = countCardsOnBorderStacks(cardStackObservables);
        assertEquals(onBorderStacksShouldBe8Cards, numberOfCardsOnMiddleStacks);
    }

    private int countCardsOnMiddleStacks(List<CardStackObservable> cardStackObservables) {
        int cardCounter = 0;
        for (CardStackObservable cardStackObservable : cardStackObservables) {
            if (cardStackObservable.getPosition().isMiddleStackPosition()) {
                cardCounter += cardStackObservable.getUnmodifiableObservableStack().size();
            }
            if (cardStackObservable.getPosition() == StackPosition.EXTRA_STACK) {
                cardCounter += cardStackObservable.getUnmodifiableObservableStack().size();
            }
        }
        return cardCounter;
    }

    private int countCardsOnBorderStacks(List<CardStackObservable> cardStackObservables) {
        int cardCounter = 0;
        for (CardStackObservable cardStackObservable : cardStackObservables) {
            if (cardStackObservable.getPosition().isPositionAce() || cardStackObservable.getPosition().isPositionKing()) {
                cardCounter += cardStackObservable.getUnmodifiableObservableStack().size();
            }
        }
        return cardCounter;
    }

}
package pl.edu.agh.to2.acesandkings.game.apiImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.GameModule;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GameManagerImplTest {
    private GameManager gameManager;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new GameModule());
        gameManager = injector.getInstance(GameManager.class);
    }


    @Test
    public void afterInitializingNewGameThenOnMiddleAndExtraStackShouldBe96Cards() {
        //given
        int onMiddleStacksShouldBe96Cards = 96;

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        int numberOfCardsOnMiddleStacks = countCardsOnMiddleStacks(cardStackObservables);
        assertEquals(onMiddleStacksShouldBe96Cards, numberOfCardsOnMiddleStacks);
    }

    @Test
    public void afterInitializingNewGameThenDifferenceInSizeOfMiddleStackShouldBeMaxOneCard() {
        //given

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

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        int numberOfCardsOnMiddleStacks = countCardsOnBorderStacks(cardStackObservables);
        assertEquals(onBorderStacksShouldBe8Cards, numberOfCardsOnMiddleStacks);
    }

    @Test
    public void afterInitializingNewGameTwiceThenOnAllStacksShouldBe104Cards() {
        //given
        int onStacksShouldBe104Cards = 104;

        //when
        gameManager.initializeNewGame();
        List<CardStackObservable> cardStackObservables = gameManager.initializeNewGame();

        //then
        int numberOfCardsOnAllStacks = countCardsOnBorderStacks(cardStackObservables) +
                countCardsOnMiddleStacks(cardStackObservables);
        assertEquals(onStacksShouldBe104Cards, numberOfCardsOnAllStacks);
    }


    @Test
    public void afterInitializingSavedGameThen() {
        //given
        CardStackRepository cardStackRepository = new CardStackRepository();
        GamePlayer gamePlayer = mock(GamePlayer.class);
        GameManager gameManager = new GameManagerImpl(cardStackRepository, gamePlayer);
        Board board = mock(Board.class);
        doReturn(getSomeRandomNewGame()).when(board).getStacks();
        doReturn(board).when(gamePlayer).restoreGame(anyInt(), anyInt());
        int onMiddleStacksShouldBe96Cards = 96;

        //when
        List<CardStackObservable> cardStackObservables = gameManager.initializeSavedGame();

        //then
        int numberOfCardsOnMiddleStacks = countCardsOnMiddleStacks(cardStackObservables);
        assertEquals(onMiddleStacksShouldBe96Cards, numberOfCardsOnMiddleStacks);
    }

    private Object getSomeRandomNewGame() {
        return this.gameManager.initializeNewGame()
                .stream()
                .map(stack -> (CardStack) stack)
                .collect(Collectors.toList());
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
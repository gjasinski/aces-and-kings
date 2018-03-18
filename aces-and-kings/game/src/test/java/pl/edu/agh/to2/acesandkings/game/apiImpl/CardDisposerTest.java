package pl.edu.agh.to2.acesandkings.game.apiImpl;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.game.apiImpl.CardDisposer;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CardDisposerTest {
    private CardDisposer cardDisposer;
    private Map<StackPosition, CardStackImpl> stackMap;

    @Before
    public void setUpCardDisposer() {
        List<CardStackImpl> stacks = new ArrayList<>();
        this.stackMap = new HashMap<>();
        for (StackPosition position : StackPosition.values()) {
            CardStackImpl cardStackMock = mock(CardStackImpl.class);
            doReturn(position).when(cardStackMock).getPosition();
            stacks.add(cardStackMock);
            stackMap.put(cardStackMock.getPosition(), cardStackMock);
        }
        this.cardDisposer = new CardDisposer(stacks);
    }

    @Test
    public void whenCreatingCardDisposerThenOnBorderAceStackShouldBeAceCard() {
        //given
        List<Card> expectedList = new LinkedList<>();
        expectedList.add(new Card(Suit.HEARTS, Rank.ACE));

        //when
        setUpCardDisposer();
        cardDisposer.flushDisposing();

        //then
        CardStackImpl borderAceHearts = this.stackMap.get(StackPosition.HEART_ACE);
        verify(borderAceHearts, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingSixthCardThenItShouldBeAddedOnSixthStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card disposedCard = new Card(Suit.HEARTS, Rank.FIVE);
        expectedList.add(disposedCard);
        CardStackImpl aceStack = this.stackMap.get(StackPosition.SIX);

        //when
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(5);
        this.cardDisposer.disposeCard(disposedCard);
        cardDisposer.flushDisposing();

        //then
        verify(aceStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingSixthCardAndCardIsSixThenNextCardShouldBeAddedOnExtraStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeDisposedOnSixthStack = new Card(Suit.HEARTS, Rank.SIX);
        Card disposedCardWhichShouldBeAddedOnExtraStack = new Card(Suit.HEARTS, Rank.FIVE);
        expectedList.add(disposedCardWhichShouldBeAddedOnExtraStack);
        CardStackImpl extraStack = this.stackMap.get(StackPosition.EXTRA_STACK);

        //when
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(5);
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnSixthStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBeAddedOnExtraStack);
        this.cardDisposer.flushDisposing();

        //then
        verify(extraStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingSixthCardAndCardIsSixThenNextCardShouldBeAddedOnExtraStackAndSecondOneOnSeventhStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeDisposedOnSixthStack = new Card(Suit.HEARTS, Rank.SIX);
        Card disposedCardWhichShouldBeAddedOnExtraStack = new Card(Suit.HEARTS, Rank.FIVE);
        Card disposedCardWhichShouldBeAddedOnSeventhStack = new Card(Suit.HEARTS, Rank.TWO);
        expectedList.add(disposedCardWhichShouldBeAddedOnSeventhStack);
        CardStackImpl seventhStack = this.stackMap.get(StackPosition.SEVEN);

        //when
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(5);
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnSixthStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBeAddedOnExtraStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBeAddedOnSeventhStack);
        this.cardDisposer.flushDisposing();

        //then
        verify(seventhStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingCardTwelfthCardNextOneShouldBePutOnExtraStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeDisposedOnTwelfthStack = new Card(Suit.HEARTS, Rank.FIVE);
        Card disposedCardWhichShouldBePutOnExtraStack = new Card(Suit.DIAMONDS, Rank.ACE);
        expectedList.add(disposedCardWhichShouldBePutOnExtraStack);
        CardStackImpl extraStack = this.stackMap.get(StackPosition.EXTRA_STACK);

        //when
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(11);
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnTwelfthStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStack);
        this.cardDisposer.flushDisposing();

        //then
        verify(extraStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingCardTwelfthCardAndItIsQueenThenTwoCardsShouldBePutOnExtraStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeDisposedOnTwelfthStack = new Card(Suit.HEARTS, Rank.QUEEN);
        Card disposedCardWhichShouldBePutOnExtraStack = new Card(Suit.DIAMONDS, Rank.ACE);
        Card disposedCardWhichShouldBePutOnExtraStackToo = new Card(Suit.CLUBS, Rank.TWO);
        expectedList.add(disposedCardWhichShouldBePutOnExtraStack);
        expectedList.add(disposedCardWhichShouldBePutOnExtraStackToo);
        CardStackImpl extraStack = this.stackMap.get(StackPosition.EXTRA_STACK);

        //when
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(11);
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnTwelfthStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStackToo);
        this.cardDisposer.flushDisposing();

        //then
        verify(extraStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingCardTwelfthCardAndItIsNotQueenThenNextOneShouldBePutOnExtraStackAndSecondOneOnAceStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeOnAceStack = new Card(Suit.CLUBS, Rank.QUEEN);
        Card cardWhichShouldBeDisposedOnTwelfthStack = new Card(Suit.HEARTS, Rank.ACE);
        Card disposedCardWhichShouldBePutOnExtraStack = new Card(Suit.DIAMONDS, Rank.TWO);
        Card disposedCardWhichShouldBePutOnAceStack = new Card(Suit.CLUBS, Rank.TEN);
        expectedList.add(cardWhichShouldBeOnAceStack);
        expectedList.add(disposedCardWhichShouldBePutOnAceStack);
        CardStackImpl aceStack = this.stackMap.get(StackPosition.ACE);

        //when
        this.cardDisposer.disposeCard(cardWhichShouldBeOnAceStack);
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(10);
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnTwelfthStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnAceStack);
        this.cardDisposer.flushDisposing();

        //then
        verify(aceStack, times(1)).setUpNewStack(expectedList);
    }

    @Test
    public void whenDisposingCardTwelfthCardAndItIsQueenThenTwoNextShouldBePutOnExtraStackAndThirdOneOnAceStack() {
        //given
        List<Card> expectedList = new LinkedList<>();
        Card cardWhichShouldBeDisposedOnAceStack = new Card(Suit.CLUBS, Rank.QUEEN);
        Card twelfthDisposedCard = new Card(Suit.HEARTS, Rank.QUEEN);
        Card disposedCardWhichShouldBePutOnExtraStack = new Card(Suit.DIAMONDS, Rank.ACE);
        Card disposedCardWhichShouldBePutOnExtraStackToo = new Card(Suit.CLUBS, Rank.TWO);
        Card disposedCardWhichShouldBePutOnAceStack = new Card(Suit.CLUBS, Rank.THREE);
        expectedList.add(cardWhichShouldBeDisposedOnAceStack);
        expectedList.add(disposedCardWhichShouldBePutOnAceStack);
        CardStackImpl aceStack = this.stackMap.get(StackPosition.ACE);

        //when
        this.cardDisposer.disposeCard(cardWhichShouldBeDisposedOnAceStack);
        disposeNumberOfCardsNoCardWillBePutOnExtraStack(10);
        this.cardDisposer.disposeCard(twelfthDisposedCard);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStack);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnExtraStackToo);
        this.cardDisposer.disposeCard(disposedCardWhichShouldBePutOnAceStack);
        this.cardDisposer.flushDisposing();

        //then
        verify(aceStack, times(1)).setUpNewStack(expectedList);
    }

    private void disposeNumberOfCardsNoCardWillBePutOnExtraStack(int numberOfCards) {
        Rank[] ranks = Rank.values();
        for (int i = 0; i < numberOfCards; i++) {
            Rank rankDifferentThanStackOnWhichCardWillBePut = ranks[(i + 2) % ranks.length];
            this.cardDisposer.disposeCard(new Card(Suit.SPADES, rankDifferentThanStackOnWhichCardWillBePut));
        }
    }
}
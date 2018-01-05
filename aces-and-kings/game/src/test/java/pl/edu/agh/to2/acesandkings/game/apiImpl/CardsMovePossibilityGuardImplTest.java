package pl.edu.agh.to2.acesandkings.game.apiImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.game.GameModule;
import pl.edu.agh.to2.acesandkings.game.api.CardsMovePossibilityGuard;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardsMovePossibilityGuardImplTest {
    private CardStackRepositoryImpl cardStackRepository;
    private CardsMovePossibilityGuard cardsMovePossibilityGuard;

    @Before
    public void setUp() {
        Injector injector = Guice.createInjector(new GameModule());
        this.cardStackRepository = injector.getInstance(CardStackRepositoryImpl.class);
        this.cardStackRepository.setCardStackList(initializeCardStacks());
        this.cardsMovePossibilityGuard = new CardsMovePossibilityGuardImpl(this.cardStackRepository);
    }

    private List<CardStackImpl> initializeCardStacks() {
        List<CardStackImpl> cardStacks = new ArrayList<>();
        for (StackPosition position : StackPosition.values()) {
            cardStacks.add(new CardStackImpl(position));
        }
        return cardStacks;
    }

    @Test
    public void whenMovingCardFromHandToMiddleStackAndStackIsNotEmptyThenItShouldNotBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.FIVE);
        Card cardOnMiddleStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.EIGHT;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnMiddleStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenMovingCardFromHandToMiddleStackAndStackIsEmptyThenItShouldBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.FIVE);
        StackPosition middleStackPosition = StackPosition.EIGHT;

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertTrue(isAllowed);
    }

    @Test
    public void whenMovingCardFromHandToAceBorderStackAndStackSuitIsTheSameAsCardAndLastCardIsOneRankLowerThenItShouldBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.FIVE);
        Card cardOnBorderStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.CLUBS_ACE;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnBorderStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertTrue(isAllowed);
    }


    @Test
    public void whenMovingCardFromHandToAceBorderStackAndStackSuitIsTheSameAsCardAndLastCardIsNotOneRankLowerThenItShouldNotBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.THREE);
        Card cardOnBorderStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.CLUBS_ACE;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnBorderStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenMovingCardFromHandToKingBorderStackAndStackSuitIsTheSameAsCardAndLastCardIsOneRankLowerThenItShouldNotBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.FIVE);
        Card cardOnBorderStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnBorderStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertFalse(isAllowed);
    }


    @Test
    public void whenMovingCardFromHandToKingBorderStackAndStackSuitIsTheSameAsCardAndLastCardIsNotOneRankLowerThenItShouldBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.CLUBS, Rank.THREE);
        Card cardOnBorderStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnBorderStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertTrue(isAllowed);
    }

    @Test
    public void whenMovingCardFromHandToBorderStackAndStackSuitIsNotTheSameAsCardThenItShouldNotBeAllowed() {
        //given
        Card cardFromHand = new Card(Suit.HEARTS, Rank.THREE);
        Card cardOnBorderStack = new Card(Suit.CLUBS, Rank.FOUR);
        StackPosition middleStackPosition = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(middleStackPosition, Collections.singletonList(cardOnBorderStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(cardFromHand, middleStackPosition);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenActivatingStackFiveWithKingOnExtraStackThenItShouldBeAllowed() {
        //given
        Card cardOnExtraStack = new Card(Suit.CLUBS, Rank.KING);
        StackPosition stackToActivate = StackPosition.FIVE;
        this.cardStackRepository.setUpNewCardOrder(StackPosition.EXTRA_STACK, Collections.singletonList(cardOnExtraStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isActivateCardStackAllowed(stackToActivate);

        //then
        assertTrue(isAllowed);
    }

    @Test
    public void whenActivatingBorderStackWithKingOnExtraStackThenItShouldNotBeAllowed() {
        //given
        Card cardOnExtraStack = new Card(Suit.CLUBS, Rank.KING);
        StackPosition stackToActivate = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(StackPosition.EXTRA_STACK, Collections.singletonList(cardOnExtraStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isActivateCardStackAllowed(stackToActivate);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenActivatingStackFiveWithSixOnExtraStackThenItShouldNotBeAllowed() {
        //given
        Card cardOnExtraStack = new Card(Suit.CLUBS, Rank.SIX);
        StackPosition stackToActivate = StackPosition.FIVE;
        this.cardStackRepository.setUpNewCardOrder(StackPosition.EXTRA_STACK, Collections.singletonList(cardOnExtraStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isActivateCardStackAllowed(stackToActivate);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenActivatingStackFiveWithFiveOnExtraStackThenItShouldBeAllowed() {
        //given
        Card cardOnExtraStack = new Card(Suit.CLUBS, Rank.FIVE);
        StackPosition stackToActivate = StackPosition.FIVE;
        this.cardStackRepository.setUpNewCardOrder(StackPosition.EXTRA_STACK, Collections.singletonList(cardOnExtraStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isActivateCardStackAllowed(stackToActivate);

        //then
        assertTrue(isAllowed);
    }

    @Test
    public void whenMovingFromOneBorderStackToOtherAndThoseStacksAreInValidStateThenItShouldBeAllowed() {
        //given
        Card cardOnAceStack = new Card(Suit.CLUBS, Rank.FIVE);
        Card cardOnKingStack = new Card(Suit.CLUBS, Rank.SIX);
        StackPosition stackAce = StackPosition.CLUBS_ACE;
        StackPosition stackKing = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(stackAce, Collections.singletonList(cardOnAceStack));
        this.cardStackRepository.setUpNewCardOrder(stackKing, Collections.singletonList(cardOnKingStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(stackAce, stackKing);

        //then
        assertTrue(isAllowed);
    }

    @Test
    public void whenMovingFromOneBorderStackToOtherAndThoseStacksHaveDifferentSuitThenItShouldNotBeAllowed() {
        //given
        Card cardOnAceStack = new Card(Suit.HEARTS, Rank.FIVE);
        Card cardOnKingStack = new Card(Suit.CLUBS, Rank.SIX);
        StackPosition stackAce = StackPosition.HEART_ACE;
        StackPosition stackKing = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(stackAce, Collections.singletonList(cardOnAceStack));
        this.cardStackRepository.setUpNewCardOrder(stackKing, Collections.singletonList(cardOnKingStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(stackAce, stackKing);

        //then
        assertFalse(isAllowed);
    }

    @Test
    public void whenMovingFromOneBorderStackToOtherAndThoseStacksAreNotInValidStateThenItShouldNotBeAllowed() {
        //given
        Card cardOnAceStack = new Card(Suit.CLUBS, Rank.FIVE);
        Card cardOnKingStack = new Card(Suit.CLUBS, Rank.SEVEN);
        StackPosition stackAce = StackPosition.CLUBS_ACE;
        StackPosition stackKing = StackPosition.CLUBS_KING;
        this.cardStackRepository.setUpNewCardOrder(stackAce, Collections.singletonList(cardOnAceStack));
        this.cardStackRepository.setUpNewCardOrder(stackKing, Collections.singletonList(cardOnKingStack));

        //when
        boolean isAllowed = this.cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(stackAce, stackKing);

        //then
        assertFalse(isAllowed);
    }

}
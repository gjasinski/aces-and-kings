package pl.edu.agh.to2.acesandkings.game.apiImpl;

import org.junit.Test;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.Suit;
import pl.edu.agh.to2.acesandkings.game.apiImpl.CardValidator;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class CardValidatorTest {
    private List<Card> generateFullDeckOfCards() {
        List<Card> cards = new LinkedList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
                cards.add(new Card(suit, rank));
            }
        }
        return cards;
    }

    @Test
    public void whenTwoFullDecsOfCardAreValidatedThenTheyAreValid() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();

        //when
        boolean result = cardValidator.validate(cards);

        //then
        assertTrue(result);
    }

    @Test
    public void whenTwoDecsOfCardWithoutBorderCardsAreValidatedThenTheyAreValid() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();
        removeBorderCards(cards);

        //when
        boolean result = cardValidator.validate(cards);

        //then
        assertTrue(result);
    }

    private void removeBorderCards(List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.remove(new Card(suit, Rank.ACE));
            cards.remove(new Card(suit, Rank.KING));
        }
    }

    @Test
    public void whenTwoDecsOfCardWithoutOneCardCameThenTheyAreNotValid() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();
        cards.remove(0);

        //when
        boolean result = cardValidator.validate(cards);

        //then
        assertFalse(result);
    }

    @Test
    public void afterNormalisingTwoFullDecsOfCardsThenTheyShouldBeValid() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();

        //when
        cardValidator.normaliseCardList(cards);

        //then
        assertTrue(cardValidator.validate(cards));
    }

    @Test
    public void afterNormalisingTwoFullDecsOfCardsThenTheyShouldContainOnlyOneAceHeart() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();
        int expectedNumberOfAceHeartsCards = 1;

        //when
        cardValidator.normaliseCardList(cards);

        //then
        assertEquals(expectedNumberOfAceHeartsCards, countAceHearts(cards, Rank.ACE, Suit.HEARTS));
    }

    @Test
    public void afterNormalisingTwoFullDecsOfCardsThenTheyShouldContainOnlyOneKingSpade() {
        //given
        CardValidator cardValidator = new CardValidator();
        List<Card> cards = generateFullDeckOfCards();
        int expectedNumberOfKingSpadeCards = 1;

        //when
        cardValidator.normaliseCardList(cards);

        //then
        assertEquals(expectedNumberOfKingSpadeCards, countAceHearts(cards, Rank.KING, Suit.SPADES));
    }

    private int countAceHearts(List<Card> cards, Rank rank, Suit suit) {
        int aceHearts = 0;
        for (Card card : cards) {
            if (card.getRank() == rank && card.getSuit() == suit) {
                aceHearts++;
            }
        }
        return aceHearts;
    }
}
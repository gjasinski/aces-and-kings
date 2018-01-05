package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.Suit;

import java.util.List;

class CardValidator {
    private static final int TWO_ENTIRE_PACKS_OF_CARDS = 104;
    private static final int TWO_PACKS_OF_CARDS_WITHOUT_BORDER_CARDS = 96;

    boolean validate(List<Card> cards) {
        return cards.size() == TWO_ENTIRE_PACKS_OF_CARDS || cards.size() == TWO_PACKS_OF_CARDS_WITHOUT_BORDER_CARDS;
    }

    void normaliseCardList(List<Card> cards) {
        if (cards.size() == TWO_ENTIRE_PACKS_OF_CARDS) {
            removeBorderCards(cards);
        }
    }

    private void removeBorderCards(List<Card> cards) {
        for (Suit suit : Suit.values()) {
            cards.remove(new Card(suit, Rank.ACE));
            cards.remove(new Card(suit, Rank.KING));
        }
    }
}

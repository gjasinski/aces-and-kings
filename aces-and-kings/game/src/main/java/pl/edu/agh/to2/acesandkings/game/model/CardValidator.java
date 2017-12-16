package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.Suit;

import java.util.List;

class CardValidator {
    private static final int ENTIRE_PACK_OF_CARDS = 104;
    private static final int PACK_OF_CARDS_WITHOUT_BORDER_CARDS = 96;

    boolean validate(List<Card> cards) {
         return cards.size() == ENTIRE_PACK_OF_CARDS || cards.size() == PACK_OF_CARDS_WITHOUT_BORDER_CARDS;
    }

    void normaliseCardList(List<Card> cards){
        if(cards.size() == ENTIRE_PACK_OF_CARDS){
            removeBorderCards(cards);
        }
    }

    private void removeBorderCards(List<Card> cards) {
        Card cardToRemove;
        for (Suit suit : Suit.values()) {
            cardToRemove = new Card(suit, Rank.ACE);
            cards.remove(cardToRemove);
            cardToRemove = new Card(suit, Rank.KING);
            cards.remove(cardToRemove);
        }
    }
}

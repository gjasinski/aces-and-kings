package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

import java.util.List;

public interface CardsInHandManipulator {
    boolean moveCardFromHandToStack(Card card, StackPosition stackPosition);

    void setUpNewOrder(List<Card> cards);
}

package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

import java.util.Optional;

public interface CardStackManager {
    void activateCardStack(StackPosition position, Card card);

    void deactivateCardStack();

    Optional<Card> getCardFromExtraStack();
}

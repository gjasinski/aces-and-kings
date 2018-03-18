package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

public interface CardsMovePossibilityGuard {
    boolean isMoveCardFromHandToStackAllowed(Card card, StackPosition stackPosition);

    boolean isActivateCardStackAllowed(StackPosition stackPosition);

    boolean isMoveCardFromOneBorderStackToOtherAllowed(StackPosition sourceStackPosition, StackPosition destinationStackPosition);

    boolean isMoveActiveCardToStackAllowed(StackPosition sourceStackPosition, StackPosition destinationFieldPosition);
}

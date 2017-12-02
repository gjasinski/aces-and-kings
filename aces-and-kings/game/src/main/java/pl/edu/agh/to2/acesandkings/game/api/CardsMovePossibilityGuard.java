package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.game.model.Card;
import pl.edu.agh.to2.acesandkings.game.model.StackPosition;

public interface CardsMovePossibilityGuard {
    boolean isMoveCardFromHandToStackAllowed(Card card, StackPosition stackPosition);

    boolean isActivateCardStackAllowed(StackPosition stackPosition);

    boolean isMoveCardFromOneBorderStackToOtherAllowed(StackPosition sourceStackPosition, StackPosition destinationStackPosition);

    boolean isMoveActiveCardToStackAllowed(StackPosition sourceStackPosition, StackPosition destinationFieldPosition);
}

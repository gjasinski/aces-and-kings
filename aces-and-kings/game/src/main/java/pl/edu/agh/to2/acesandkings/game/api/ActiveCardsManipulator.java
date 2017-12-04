package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.game.model.StackPosition;

public interface ActiveCardsManipulator {
    void moveActiveCardToStack(StackPosition sourceStackPosition, StackPosition destinationStackPosition);

    void moveCardFromOneBorderStackToOther(StackPosition sourceStackPosition, StackPosition destinationStackPosition);
}

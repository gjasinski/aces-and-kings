package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

public interface CardStackManager {
    void activateCardStack(StackPosition position);

    void deactivateCardStack();
}

package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.game.model.StackPosition;

public interface CardStackManager {
    void activateCardStack(StackPosition position);

    void disactivateCardStack();
}

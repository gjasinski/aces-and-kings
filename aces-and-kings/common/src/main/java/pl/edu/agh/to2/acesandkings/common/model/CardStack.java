package pl.edu.agh.to2.acesandkings.common.model;

import java.util.List;

public interface CardStack {

    List<Card> getStack();

    State getState();

    StackPosition getPosition();
}

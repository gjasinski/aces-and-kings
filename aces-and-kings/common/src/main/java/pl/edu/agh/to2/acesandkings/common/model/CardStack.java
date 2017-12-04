package pl.edu.agh.to2.acesandkings.common.model;

import javafx.collections.ObservableList;

import java.util.List;

public interface CardStack {

    List<Card> getStack();

    ObservableList<Card> getUnmodifableObservableStack();

    State getState();

    StackPosition getPosition();
}

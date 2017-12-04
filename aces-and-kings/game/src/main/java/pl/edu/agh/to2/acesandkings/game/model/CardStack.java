package pl.edu.agh.to2.acesandkings.game.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface CardStack {

    ObservableList<Card> getUnmodifableObservableStack();

    State getState();

    StackPosition getPosition();
}

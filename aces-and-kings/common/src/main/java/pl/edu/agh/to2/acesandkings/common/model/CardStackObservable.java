package pl.edu.agh.to2.acesandkings.common.model;

import javafx.collections.ObservableList;

import java.util.List;

public interface CardStackObservable {

    List<Card> getStack();

    ObservableList<Card> getUnmodifiableObservableStack();

    State getState();

    StackPosition getPosition();
}

package pl.edu.agh.to2.acesandkings.common.model;

import javafx.collections.ObservableList;


public interface CardStackObservable extends CardStack {

    ObservableList<Card> getUnmodifiableObservableStack();
}

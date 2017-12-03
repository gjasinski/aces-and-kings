package pl.edu.agh.to2.acesandkings.game.model;

import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class CardStackDTO {
    private State state;
    private ObservableList<Card> stack;
    private StackPosition position;

    public List<Card> getStack() {
        List<Card> cardList = new ArrayList<Card>();
        cardList.addAll(stack);
        return cardList;
    }

    public ObservableList<Card> getObservableStack() {
        return stack;
    }

    public State getState() {
        return state;
    }

    public StackPosition getPosition() {
        return position;
    }
}

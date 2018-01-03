package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.Board;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;


public class Change {
    private StackPosition previousStackPosition;
    private StackPosition nextStackPosition;
    private Card card;
    private int step;

    public Change(StackPosition previousStackPosition, StackPosition nextStackPosition, Card card, int step) {
        this.previousStackPosition = previousStackPosition;
        this.nextStackPosition = nextStackPosition;
        this.card = card;
        this.step = step;
    }

    public StackPosition getPreviousStackPosition() {
        return previousStackPosition;
    }

    public StackPosition getNextStackPosition() {
        return nextStackPosition;
    }

    public Card getCard() {
        return card;
    }

    public int getStep(){
        return step;
    }

    public Board applyTo(Board board){
        //TODO: ACTUALLY CHANGE BOARD
        return board;
    }
}

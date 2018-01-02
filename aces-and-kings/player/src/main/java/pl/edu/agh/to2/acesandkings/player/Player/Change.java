package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;


public class Change {
    private StackPosition previousStackPosition;
    private StackPosition nextStackPosition;
    private Card card;

    public Change(StackPosition previousStackPosition, StackPosition nextStackPosition, Card card) {
        this.previousStackPosition = previousStackPosition;
        this.nextStackPosition = nextStackPosition;
        this.card = card;
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
}

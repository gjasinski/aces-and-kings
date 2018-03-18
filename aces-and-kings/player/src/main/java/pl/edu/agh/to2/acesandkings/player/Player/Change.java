package pl.edu.agh.to2.acesandkings.player.Player;

import java.util.LinkedList;
import java.util.List;
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
        List<CardStack> stacks = new LinkedList<>();
        for(CardStack stack: board.getStacks()){
            if(stack.getPosition().equals(previousStackPosition) || stack.getPosition().equals(nextStackPosition)){
                stacks.add(new CardStackModel(stack, card));
            } else {
                stacks.add(stack);
            }
        }
        return new Board(stacks, board.getId());
    }

    @Override
    public String toString(){
        return previousStackPosition + " - " + card + " -> " + nextStackPosition;
    }
}

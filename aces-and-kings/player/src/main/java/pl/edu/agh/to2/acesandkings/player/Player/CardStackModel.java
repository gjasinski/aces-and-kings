package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;

import java.util.ArrayList;
import java.util.List;


public class CardStackModel implements CardStack{
    private List<Card> cards;
    private State state;
    private StackPosition stackPosition;

    public CardStackModel(List<Card> cards, State state, StackPosition stackPosition){
        this.cards = cards;
        this.state = state;
        this.stackPosition = stackPosition;
    }

    public CardStackModel(State state, StackPosition stackPosition) {
        this.state = state;
        this.stackPosition = stackPosition;
    }

    @Override
    public List<Card> getStack() {
        return cards;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public StackPosition getPosition() {
        return stackPosition;
    }

    public void addCard(Card newCard){
        cards.add(newCard);
    }

    public void removeCard(Card newCard){
        cards.remove(newCard);
    }
}

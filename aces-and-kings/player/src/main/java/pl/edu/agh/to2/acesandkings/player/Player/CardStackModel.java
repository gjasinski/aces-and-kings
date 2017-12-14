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
    private int cardStackId;
    private StackPosition stackPosition;
//    int

    public CardStackModel(ArrayList<Card> cards, boolean state, int cardStackId){
        cards = cards;
        state = state;
        cardStackId = cardStackId;
    }

    @Override
    public List<Card> getStack() {
        return null;
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

package pl.edu.agh.to2.acesandkings.player.Player;

import pl.edu.agh.to2.acesandkings.common.model.Card;


public class Change {
    private CardStackModel previousStack;
    private CardStackModel nextStack;
    private Card card;

    public Change(CardStackModel previousStack, CardStackModel nextStack, Card card){
        this.previousStack = previousStack;
        this.nextStack= nextStack;
        this.card = card;
    }

    public CardStackModel getPreviousStack(){
        return previousStack;
    }

    public CardStackModel getNextStack(){
        return nextStack;
    }
}

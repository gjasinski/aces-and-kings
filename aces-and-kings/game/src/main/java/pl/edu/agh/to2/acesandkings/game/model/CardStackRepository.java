package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CardStackRepository {
    private List<CardStackImpl> cardStackList;

    public void setCardStackList(List<CardStackImpl> cardStackList) {
        this.cardStackList = cardStackList;
    }

    public void putCardOnStack(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.putCardOnStack(card);
    }

    public void setUpNewCardOrder(StackPosition position, List<Card> cardsList) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.setUpNewStack(cardsList);
    }

    public boolean removeCardFromStack(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.removeCardFromStack(card);
    }

    public Optional<Card> removeCardFromStack(StackPosition position) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.removeCardFromStack();
    }

    public void changeStackState(StackPosition position, State newState) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.changeStackState(newState);
    }

    public boolean isRemoveCardFromStackAllowed(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.isRemoveCardFromStackAllowed(card);
    }

    public boolean isPutCardOnStackAllowed(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.isPutCardOnStackAllowed(card);
    }

    private CardStackImpl getStackFromPosition(StackPosition position) {
        CardStackImpl searchedCardStack = null;
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                searchedCardStack = cardStack;
        return searchedCardStack;
    }

    public Optional<Card> getLastCardFromStack(StackPosition position){
        for(CardStackImpl cardStack: cardStackList){
            if(cardStack.getPosition() == position){
                return cardStack.getLastCard();
            }
        }
        return Optional.empty();
    }

    public List<CardStackImpl> getCardStackList() {
        return cardStackList;
    }

    public StackPosition findActiveCardStack() {
        for (CardStackImpl cardStack : cardStackList)
            if(cardStack.getState().equals(State.ACTIVE))
                return cardStack.getPosition();
        return null;
    }

    public void moveCardsFromStackToStack(StackPosition source, StackPosition destination) {
        CardStackImpl fromStack = getStackFromPosition(source);
        CardStackImpl toStack = getStackFromPosition(destination);
        List<Card> cards = fromStack.getStack();
        fromStack.setUpNewStack(Collections.emptyList());
        toStack.setUpNewStack(cards);
    }
}

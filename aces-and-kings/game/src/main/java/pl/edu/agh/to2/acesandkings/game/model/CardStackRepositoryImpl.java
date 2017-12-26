package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;

import java.util.List;
import java.util.Optional;

public class CardStackRepositoryImpl implements CardStackRepository {
    private List<CardStackImpl> cardStackList;

    void setCardStackList(List<CardStackImpl> cardStackList) {
        this.cardStackList = cardStackList;
    }

    @Override
    public void putCardOnStack(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.putCardOnStack(card);
    }

    @Override
    public void setUpNewCardOrder(StackPosition position, List<Card> cardsList) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.setUpNewStack(cardsList);
    }

    @Override
    public boolean removeCardFromStack(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.removeCardFromStack(card);
    }

    @Override
    public Optional<Card> removeCardFromStack(StackPosition position) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.removeCardFromStack();
    }

    @Override
    public void changeStackState(StackPosition position, State newState) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.changeStackState(newState);
    }

    @Override
    public boolean isRemoveCardFromStackAllowed(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.isRemoveCardFromStackAllowed(card);
    }

    @Override
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

    private Card getLastCard(List<Card> stack) {
        return stack.get(stack.size() - 1);
    }

    List<CardStackImpl> getCardStackList() {
        return cardStackList;
    }

    public StackPosition findActiveCardStack() {
        for (CardStackImpl cardStack : cardStackList)
            if(cardStack.getState().equals(State.ACTIVE))
                return cardStack.getPosition();
        return null;
    }
}

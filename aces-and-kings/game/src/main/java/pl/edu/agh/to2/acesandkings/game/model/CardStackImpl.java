package pl.edu.agh.to2.acesandkings.game.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class CardStackImpl implements CardStack {
    private State state;
    private ObservableList<Card> stack;
    private StackPosition position;

    public CardStackImpl(StackPosition position) {
        this.state = State.INACTIVE;
        this.stack = FXCollections.observableArrayList();
        this.position = position;
    }

    @Override
    public void putCardOnStack(Card card) {
        // empty stack
        if (!getLastCard().isPresent())
            stack.add(card);
        else {
            Card lastCard = getLastCard().get();
            if (card.getSuit().equals(lastCard.getSuit())) {
                if (isPositionKing() && card.getRank().ordinal() == lastCard.getRank().ordinal() - 1)
                    stack.add(card);
                else if (isPositionAce() && card.getRank().ordinal() == lastCard.getRank().ordinal() + 1)
                    stack.add(card);
            }
        }
    }

    @Override
    public void setUpNewStack(List<Card> cardsList) {
        stack.addAll(cardsList);
    }

    @Override
    public boolean removeCardFromStack(Card card) {
        return (state.equals(State.ACTIVE) || stack.get(stack.size() - 1).equals(card)) && stack.remove(card);
    }

    @Override
    public Optional<Card> removeCardFromStack() {
        Card card = null;
        if (!stack.isEmpty())
            card = stack.remove(stack.size() - 1);
        return Optional.ofNullable(card);
    }

    @Override
    public void changeStackState() {
        if (state == State.ACTIVE)
            state = State.INACTIVE;
        else
            state = State.ACTIVE;
    }

    private boolean isPositionKing() {
        return position.equals(StackPosition.CLUBS_KING) || position.equals(StackPosition.DIAMONDS_KING) ||
                position.equals(StackPosition.HEARTH_KING) || position.equals(StackPosition.SPADES_KING);
    }

    private boolean isPositionAce() {
        return position.equals(StackPosition.CLUBS_ACE) || position.equals(StackPosition.DIAMONDS_ACE) ||
                position.equals(StackPosition.HEARTH_ACE) || position.equals(StackPosition.SPADES_ACE);
    }

    private Optional<Card> getLastCard() {
        Card card = null;
        if (!stack.isEmpty())
            card = stack.get(stack.size() - 1);
        return Optional.ofNullable(card);
    }

    public ObservableList<Card> getStack() {
        return stack;
    }

    public State getState() {
        return state;
    }

    public StackPosition getPosition() {
        return position;
    }
}

package pl.edu.agh.to2.acesandkings.game.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.acesandkings.common.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardStackImpl implements CardStack, CardStackObservable {
    private State state;
    private ObservableList<Card> stack;
    private StackPosition position;

    CardStackImpl(StackPosition position) {
        this.state = State.INACTIVE;
        this.stack = FXCollections.observableArrayList();
        this.position = position;
    }

    void putCardOnStack(Card card) {
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

    void setUpNewStack(List<Card> cardsList) {
        stack.addAll(cardsList);
    }

    boolean removeCardFromStack(Card card) {
        return (state.equals(State.ACTIVE) || stack.get(stack.size() - 1).equals(card)) && stack.remove(card);
    }

    Optional<Card> removeCardFromStack() {
        Card card = null;
        if (!stack.isEmpty())
            card = stack.remove(stack.size() - 1);
        return Optional.ofNullable(card);
    }

    void changeStackState() {
        if (state == State.ACTIVE)
            state = State.INACTIVE;
        else
            state = State.ACTIVE;
    }

    private boolean isPositionKing() {
        return position.equals(StackPosition.CLUBS_KING) || position.equals(StackPosition.DIAMONDS_KING) ||
                position.equals(StackPosition.HEART_KING) || position.equals(StackPosition.SPADES_KING);
    }

    private boolean isPositionAce() {
        return position.equals(StackPosition.CLUBS_ACE) || position.equals(StackPosition.DIAMONDS_ACE) ||
                position.equals(StackPosition.HEART_ACE) || position.equals(StackPosition.SPADES_ACE);
    }

    private Optional<Card> getLastCard() {
        Card card = null;
        if (!stack.isEmpty())
            card = stack.get(stack.size() - 1);
        return Optional.ofNullable(card);
    }

    @Override
    public List<Card> getStack() {
        return new ArrayList<>(this.stack);
    }

    @Override
    public ObservableList<Card> getUnmodifiableObservableStack() {
        return FXCollections.unmodifiableObservableList(stack);
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public StackPosition getPosition() {
        return position;
    }
}

package pl.edu.agh.to2.acesandkings.game.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.acesandkings.common.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardStackImpl implements CardStackObservable {
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
        stack.clear();
        stack.addAll(cardsList);
    }

    boolean removeCardFromStack(Card card) {
        return isRemoveCardFromStackAllowed(card) && stack.remove(card);
    }

    Optional<Card> removeCardFromStack() {
        Optional<Card> card = getLastCard();
        if (card.isPresent() && isRemoveCardFromStackAllowed(card.get()))
            stack.remove(card.get());
        return card;
    }

    void changeStackState(State newState) {
        this.state = newState;
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

    boolean isRemoveCardFromStackAllowed(Card card) {
        boolean result = false;
        if (stack.contains(card)) {
            if (state.equals(State.ACTIVE))
                result = true;
            else {
                Optional<Card> lastCard = getLastCard();
                if (lastCard.isPresent() && card.equals(lastCard.get()))
                    result = (position.isPositionAce() && !card.getRank().equals(Rank.ACE)) ||
                            (position.isPositionKing() && !card.getRank().equals(Rank.KING)) ||
                            position.isMiddleStackPosition();
            }
        }
        return result;
    }

    boolean isPutCardOnStackAllowed(Card card) {
        boolean result = false;
        Optional<Card> lastCard = getLastCard();
        if (!lastCard.isPresent())
            result = true;
        else {
            if (card.getSuit().equals(lastCard.get().getSuit())) {
                if (position.isPositionKing() && card.getRank().ordinal() == lastCard.get().getRank().ordinal() - 1)
                    result = true;
                else if (position.isPositionAce() && card.getRank().ordinal() == lastCard.get().getRank().ordinal() + 1)
                    result = true;
            }
        }
        return result;
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

package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;

import java.util.List;
import java.util.Optional;

public class CardStackRepositoryImpl implements CardStackRepository {
    private List<CardStackImpl> cardStackList;

    @Override
    public void putCardOnStack(StackPosition position, Card card) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                cardStack.putCardOnStack(card);
    }

    @Override
    public void setUpNewCardOrder(StackPosition position, List<Card> cardsList) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                cardStack.setUpNewStack(cardsList);
    }

    @Override
    public boolean removeCardFromStack(StackPosition position, Card card) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                return cardStack.removeCardFromStack(card);
        return false;
    }

    @Override
    public Optional<Card> removeCardFromStack(StackPosition position) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                return cardStack.removeCardFromStack();
        return Optional.empty();
    }

    @Override
    public void changeStackState(StackPosition position) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                cardStack.changeStackState();
    }

    @Override
    public boolean isRemoveCardFromStackAllowed(StackPosition position, Card card) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position))
                if (cardStack.getStack().contains(card))
                    if (cardStack.getState().equals(State.ACTIVE) || cardStack.getStack().get(cardStack.getStack().size() - 1) == card)
                        return true;
        return false;
    }

    @Override
    public boolean isPutCardOnStackAllowed(StackPosition position, Card card) {
        // TODO
        return false;
    }
}

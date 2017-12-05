package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;

import java.util.List;
import java.util.Optional;

public class CardStackRepositoryImpl implements CardStackRepository {
    private List<CardStackImpl> cardStackList;

    public CardStackRepositoryImpl(List<CardStackImpl> cardStackList) {
        this.cardStackList = cardStackList;
    }

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
                if (cardStack.getStack().contains(card)) {
                    if (cardStack.getState().equals(State.ACTIVE))
                        return true;
                    else {
                        Card lastCard = getLastCard(cardStack.getStack());
                        if (lastCard.equals(card)) {
//                        check if it's not last card on border stack
                            if (isPositionAce(position) && !lastCard.getRank().equals(Rank.ACE))
                                return true;
                            if (isPositionKing(position) && !lastCard.getRank().equals(Rank.KING))
                                return true;
                        }
                    }
                }
        return false;
    }

    @Override
    public boolean isPutCardOnStackAllowed(StackPosition position, Card card) {
        for (CardStackImpl cardStack : cardStackList)
            if (cardStack.getPosition().equals(position)) {
                if (cardStack.getStack().isEmpty())
                    return true;
                else {
                    Card lastCard = getLastCard(cardStack.getStack());
                    if (card.getSuit().equals(lastCard.getSuit())) {
                        if (isPositionKing(cardStack.getPosition()) && card.getRank().ordinal() == lastCard.getRank().ordinal() - 1)
                            return true;
                        else if (isPositionAce(cardStack.getPosition()) && card.getRank().ordinal() == lastCard.getRank().ordinal() + 1)
                            return true;
                    }
                }
            }
        return false;
    }

    private boolean isPositionKing(StackPosition position) {
        return position.equals(StackPosition.CLUBS_KING) || position.equals(StackPosition.DIAMONDS_KING) ||
                position.equals(StackPosition.HEART_KING) || position.equals(StackPosition.SPADES_KING);
    }

    private boolean isPositionAce(StackPosition position) {
        return position.equals(StackPosition.CLUBS_ACE) || position.equals(StackPosition.DIAMONDS_ACE) ||
                position.equals(StackPosition.HEART_ACE) || position.equals(StackPosition.SPADES_ACE);
    }

    private Card getLastCard(List<Card> stack) {
        return stack.get(stack.size() - 1);
    }

    public List<CardStackImpl> getCardStackList() {
        return cardStackList;
    }
}

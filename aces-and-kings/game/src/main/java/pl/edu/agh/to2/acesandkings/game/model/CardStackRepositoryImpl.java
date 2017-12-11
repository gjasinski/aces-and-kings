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
    public void changeStackState(StackPosition position) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        searchedCardStack.changeStackState();
    }

    @Override
    public boolean isRemoveCardFromStackAllowed(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        return searchedCardStack.isRemoveCardFromStackAllowed(card);
    }

    @Override
    public boolean isPutCardOnStackAllowed(StackPosition position, Card card) {
        CardStackImpl searchedCardStack = getStackFromPosition(position);
        if (searchedCardStack.getStack().isEmpty())
            return true;
        else {
            Card lastCard = getLastCard(searchedCardStack.getStack());
            if (card.getSuit().equals(lastCard.getSuit())) {
                if (searchedCardStack.getPosition().isPositionKing() && card.getRank().ordinal() == lastCard.getRank().ordinal() - 1)
                    return true;
                else if (searchedCardStack.getPosition().isPositionAce() && card.getRank().ordinal() == lastCard.getRank().ordinal() + 1)
                    return true;
            }
        }
        return false;
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

    public List<CardStackImpl> getCardStackList() {
        return cardStackList;
    }
}

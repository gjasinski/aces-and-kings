package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class CardDisposer {
    private HashMap<StackPosition, CardStackImpl> cardStackImplMap = new HashMap<>();
    private HashMap<StackPosition, List<Card>> positionToList = new HashMap<>();
    private List<StackPosition> middleStackPositions = StackPosition.getMiddlePositions();
    private boolean putNextCardOnExtraStack = false;
    private boolean putNextCardOnExtraStackPositionCounter = false;
    private int stackPositionCounter = 0;

    CardDisposer(List<CardStackImpl> stacks) {
        stacks.forEach(stack -> {
            cardStackImplMap.put(stack.getPosition(), stack);
            positionToList.put(stack.getPosition(), new LinkedList<>());
        });
        disposeCardsOnBorderStacks();
    }

    private void disposeCardsOnBorderStacks() {
        for (StackPosition position : StackPosition.getBorderPositions()) {
            Card borderCard = new Card(position.getSuit(), position.getRank());
            putCardOnStack(position, borderCard);
        }
    }

    void disposeCard(Card card) {
        if (putNextCardOnExtraStack || putNextCardOnExtraStackPositionCounter) {
            putCardOnExtraStack(card);
        } else {
            disposeCardOnMiddleStacks(card);
            incrementStackPositionCounter();
        }
    }

    void flushDisposing() {
        this.positionToList.forEach(this::flushCardStack);
    }

    private void flushCardStack(StackPosition position, List<Card> cards) {
        this.cardStackImplMap.get(position).setUpNewStack(cards);
    }

    private void putCardOnExtraStack(Card card) {
        if (putNextCardOnExtraStack) {
            putNextCardOnExtraStack = false;
        } else {
            putNextCardOnExtraStackPositionCounter = false;
        }
        putCardOnStack(StackPosition.EXTRA_STACK, card);
    }

    private void incrementStackPositionCounter() {
        stackPositionCounter++;
        if (stackPositionCounter == StackPosition.getNumberOfMiddleStacks()) {
            stackPositionCounter = 0;
            putNextCardOnExtraStackPositionCounter = true;
        }
    }

    private void disposeCardOnMiddleStacks(Card card) {
        if (isCardRankExactAsStackPositionRank(card)) {
            putNextCardOnExtraStack = true;
        }
        StackPosition stackPosition = middleStackPositions.get(stackPositionCounter);
        putCardOnStack(stackPosition, card);
    }

    private boolean isCardRankExactAsStackPositionRank(Card card) {
        Rank cardRank = card.getRank();
        Rank currentStackPositionRank = middleStackPositions.get(stackPositionCounter).getRank();
        return currentStackPositionRank == cardRank;
    }

    private void putCardOnStack(StackPosition stackPosition, Card card) {
        this.positionToList.get(stackPosition).add(card);
    }
}

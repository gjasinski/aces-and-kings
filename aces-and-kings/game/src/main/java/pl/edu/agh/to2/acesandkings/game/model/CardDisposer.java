package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class CardDisposer {
    private HashMap<StackPosition, List<Card>> stackMap = new HashMap<>();
    private List<CardStackObservable> disposedCards;
    private List<StackPosition> middleStackPositions = StackPosition.getMiddlePositions();
    private boolean putNextCardOnExtraStack = false;
    private int putCardOnStack = 0;
    CardDisposer(){
        initializeStackMap();
    }

    private void initializeStackMap() {
        StackPosition.getMiddlePositions()
                .forEach(position -> this.stackMap.put(position, new LinkedList<>()));
        this.stackMap.put(StackPosition.EXTRA_STACK, new LinkedList<>());
    }


    void disposeCard(Card card) {
        if(putNextCardOnExtraStack){
            putCardOnStack(StackPosition.EXTRA_STACK, card);
            putNextCardOnExtraStack = false;
        }
        else {
            disposeCardOnMiddleStacks(card);
        }

    }

    private void disposeCardOnMiddleStacks(Card card) {
        if(isCardRankExactAsStackPositionRank(card)){
            putNextCardOnExtraStack = true;
        }
        //card.getRank();
                //todo StackPosition get card by rank!

    }

    private boolean isCardRankExactAsStackPositionRank(Card card) {
        return false;
    }

    List<CardStackObservable> getDisposedCards() {
        return disposedCards;
    }

    void putCardOnStack(StackPosition stackPosition, Card card){
        this.stackMap.get(stackPosition).add(card);
    }
}

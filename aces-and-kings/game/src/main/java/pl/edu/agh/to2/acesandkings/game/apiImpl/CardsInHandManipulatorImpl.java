package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.CardsInHandManipulator;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import javax.inject.Inject;
import java.util.List;

public class CardsInHandManipulatorImpl implements CardsInHandManipulator {
    private CardStackRepository cardStackRepository;
    private StackPosition activeCardStackPosition;

    @Inject
    public CardsInHandManipulatorImpl(CardStackRepository cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public boolean moveCardFromHandToStack(Card card, StackPosition stackPosition) {
        if (cardStackRepository.isPutCardOnStackAllowed(stackPosition, card)) {
            cardStackRepository.removeCardFromStack(StackPosition.HAND_STACK, card);
            cardStackRepository.putCardOnStack(stackPosition, card);
            return true;
        }
        return false;
    }

    @Override
    public void setUpNewOrder(List<Card> cards) {
        activeCardStackPosition = cardStackRepository.findActiveCardStack();
        cardStackRepository.setUpNewCardOrder(activeCardStackPosition, cards);
    }
}

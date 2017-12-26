package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.CardsInHandManipulator;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import java.util.List;

public class CardsInHandManipulatorImpl implements CardsInHandManipulator {
    private CardStackRepositoryImpl cardStackRepository;
    private StackPosition activeCardStackPosition;

    @Override
    public boolean moveCardFromHandToStack(Card card, StackPosition stackPosition) {
//        TODO functions isRemoveCardFromStackAllowed and isPutCardOnStackAllowed are calls two times - solution: let putCardOnStack return boolean not void
        activeCardStackPosition = cardStackRepository.findActiveCardStack();
        if (cardStackRepository.isRemoveCardFromStackAllowed(activeCardStackPosition, card) && cardStackRepository.isPutCardOnStackAllowed(stackPosition, card)) {
            cardStackRepository.removeCardFromStack(activeCardStackPosition, card);
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

package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.game.api.CardStackManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import javax.inject.Inject;
import java.util.Optional;

public class CardStackManagerImpl implements CardStackManager {
    private CardStackRepositoryImpl cardStackRepository;
    private StackPosition activeStack = null;

    @Inject
    public CardStackManagerImpl(CardStackRepositoryImpl cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public void activateCardStack(StackPosition position, Card card) {
        cardStackRepository.putCardOnStack(position, card);
        cardStackRepository.changeStackState(position, State.ACTIVE);
        cardStackRepository.moveCardsFromStackToStack(position, StackPosition.HAND_STACK);
        this.activeStack = position;
    }

    @Override
    public void deactivateCardStack() {
//        TODO keep info about ACTIVE state in CardStackRepositoryImpl and rid off method findActiveCardStack
        //StackPosition activeStack = cardStackRepository.findActiveCardStack();
        //cardStackRepository.changeStackState(activeStack, State.INACTIVE);
        cardStackRepository.changeStackState(activeStack, State.INACTIVE);
        cardStackRepository.moveCardsFromStackToStack(StackPosition.HAND_STACK, activeStack);
        activeStack=null;
    }

    @Override
    public Optional<Card> getCardFromExtraStack() {
        return cardStackRepository.removeCardFromStack(StackPosition.EXTRA_STACK);
    }
}

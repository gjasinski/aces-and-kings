package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.game.api.CardStackManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

public class CardStackManagerImpl implements CardStackManager {
    private CardStackRepositoryImpl cardStackRepository;

    CardStackManagerImpl(CardStackRepositoryImpl cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public void activateCardStack(StackPosition position) {
        cardStackRepository.changeStackState(position, State.ACTIVE);
    }

    @Override
    public void deactivateCardStack() {
//        TODO keep info about ACTIVE state in CardStackRepositoryImpl and rid off method findActiveCardStack
        StackPosition stackPosition = cardStackRepository.findActiveCardStack();
        cardStackRepository.changeStackState(stackPosition, State.INACTIVE);
    }
}

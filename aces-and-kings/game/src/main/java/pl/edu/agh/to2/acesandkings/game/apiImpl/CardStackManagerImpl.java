package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.game.api.CardStackManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

public class CardStackManagerImpl implements CardStackManager {
    private CardStackRepositoryImpl cardStackRepository;

    @Override
    public void activateCardStack(StackPosition position) {
        cardStackRepository.changeStackState(position, State.ACTIVE);
    }

    @Override
    public void deactivateCardStack() {
        StackPosition stackPosition = cardStackRepository.findActiveCardStack();
        cardStackRepository.changeStackState(stackPosition, State.INACTIVE);
    }
}

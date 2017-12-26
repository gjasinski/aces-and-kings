package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.game.api.CardStackManager;

public class CardStackManagerImpl implements CardStackManager {
    private CardStackRepository cardStackRepository;

    @Override
    public void activateCardStack(StackPosition position) {
        cardStackRepository.changeStackState(position, State.ACTIVE);
    }

    @Override
    public void deactivateCardStack(StackPosition position) {
        cardStackRepository.changeStackState(position, State.INACTIVE);
    }
}

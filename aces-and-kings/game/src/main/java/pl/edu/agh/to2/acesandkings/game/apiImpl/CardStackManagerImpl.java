package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.common.model.State;
import pl.edu.agh.to2.acesandkings.game.api.CardStackManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

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

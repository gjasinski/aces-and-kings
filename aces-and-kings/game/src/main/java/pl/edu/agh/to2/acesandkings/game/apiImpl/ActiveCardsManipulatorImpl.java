package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.ActiveCardsManipulator;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import java.util.Optional;

public class ActiveCardsManipulatorImpl implements ActiveCardsManipulator {
    private CardStackRepositoryImpl cardStackRepository;

    ActiveCardsManipulatorImpl(CardStackRepositoryImpl cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public void moveActiveCardToStack(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        moveCard(sourceStackPosition, destinationStackPosition);
    }

    @Override
    public void moveCardFromOneBorderStackToOther(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        moveCard(sourceStackPosition, destinationStackPosition);
    }

    private void moveCard(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        Optional<Card> card = cardStackRepository.removeCardFromStack(sourceStackPosition);
        card.ifPresent(card1 -> cardStackRepository.putCardOnStack(destinationStackPosition, card1));
    }
}

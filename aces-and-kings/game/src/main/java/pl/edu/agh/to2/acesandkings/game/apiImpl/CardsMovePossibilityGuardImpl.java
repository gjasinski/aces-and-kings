package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.CardsMovePossibilityGuard;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;

import javax.inject.Inject;
import java.util.Optional;

public class CardsMovePossibilityGuardImpl implements CardsMovePossibilityGuard {
    private CardStackRepositoryImpl cardStackRepository;

    @Inject
    public CardsMovePossibilityGuardImpl(CardStackRepositoryImpl cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public boolean isMoveCardFromHandToStackAllowed(Card card, StackPosition stackPosition) {
        return cardStackRepository.isPutCardOnStackAllowed(stackPosition, card);
    }

    @Override
    public boolean isActivateCardStackAllowed(StackPosition stackPosition) {
        Optional<Card> lastCardOnExtraStack = cardStackRepository.getLastCardFromStack(StackPosition.EXTRA_STACK);
        if(lastCardOnExtraStack.isPresent()){
            return validateIfCanIActivateStack(stackPosition, lastCardOnExtraStack.get());
        }
        else {
            return false;
        }
    }

    private boolean validateIfCanIActivateStack(StackPosition stackPosition, Card card) {
        return card.getRank() == Rank.KING || card.getRank() == stackPosition.getRank();
    }

    @Override
    public boolean isMoveCardFromOneBorderStackToOtherAllowed(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        Optional<Card> cardToMove = cardStackRepository.getLastCardFromStack(sourceStackPosition);
        if(cardToMove.isPresent()){
            return cardStackRepository.isPutCardOnStackAllowed(destinationStackPosition, cardToMove.get());
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isMoveActiveCardToStackAllowed(StackPosition sourceStackPosition, StackPosition destinationFieldPosition) {
        return isMoveCardFromOneBorderStackToOtherAllowed(sourceStackPosition, destinationFieldPosition);
    }
}

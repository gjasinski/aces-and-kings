package pl.edu.agh.to2.acesandkings.game.apiImpl;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.CardsMovePossibilityGuard;
import pl.edu.agh.to2.acesandkings.game.model.CardStackImpl;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepository;

import javax.inject.Inject;
import java.util.Optional;

public class CardsMovePossibilityGuardImpl implements CardsMovePossibilityGuard {
    private CardStackRepository cardStackRepository;

    @Inject
    public CardsMovePossibilityGuardImpl(CardStackRepository cardStackRepository) {
        this.cardStackRepository = cardStackRepository;
    }

    @Override
    public boolean isMoveCardFromHandToStackAllowed(Card card, StackPosition stackPosition) {
        return cardStackRepository.isPutCardOnStackAllowed(stackPosition, card);
    }

    @Override
    public boolean isActivateCardStackAllowed(StackPosition stackPosition) {
        Optional<Card> lastCardOnExtraStack = cardStackRepository.getLastCardFromStack(StackPosition.EXTRA_STACK);
        return stackPosition.isMiddleStackPosition() && lastCardOnExtraStack.isPresent() &&
                validateIfCanIActivateStack(stackPosition, lastCardOnExtraStack.get()) && isHandStackEmpty();
    }

    private boolean isHandStackEmpty() {
        Optional<CardStackImpl> handStack = cardStackRepository.getCardStackList().stream().filter(v -> v.getPosition() == StackPosition.HAND_STACK).findAny();
        if(!handStack.isPresent()){
            throw new IllegalArgumentException();
        }
        return handStack.get().getStack().size() == 0;
    }

    private boolean validateIfCanIActivateStack(StackPosition stackPosition, Card card) {
        return card.getRank() == Rank.KING || card.getRank() == stackPosition.getRank();
    }

    @Override
    public boolean isMoveCardFromOneBorderStackToOtherAllowed(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        Optional<Card> cardToMove = cardStackRepository.getLastCardFromStack(sourceStackPosition);
        return cardToMove.isPresent() && sourceStackPosition.isBorderPosition() && destinationStackPosition.isBorderPosition()
                && cardStackRepository.isPutCardOnStackAllowed(destinationStackPosition, cardToMove.get());
    }

    @Override
    public boolean isMoveActiveCardToStackAllowed(StackPosition sourceStackPosition, StackPosition destinationStackPosition) {
        Optional<Card> cardToMove = cardStackRepository.getLastCardFromStack(sourceStackPosition);
        return cardToMove.isPresent() && sourceStackPosition.isMiddleStackPosition()
                && cardStackRepository.isPutCardOnStackAllowed(destinationStackPosition, cardToMove.get());
    }
}

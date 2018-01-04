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
        String odp = stackPosition.isMiddleStackPosition() ? "IsMiddleStPos":"Isn'tMiddlStPos";
        System.out.println(odp);
        odp = lastCardOnExtraStack.isPresent() ? "Present": "Not present";
        System.out.println(odp);
        odp = validateIfCanIActivateStack(stackPosition, lastCardOnExtraStack.get()) ? "can act":"cant act";
        System.out.println(odp);
        return stackPosition.isMiddleStackPosition() && lastCardOnExtraStack.isPresent() &&
                validateIfCanIActivateStack(stackPosition, lastCardOnExtraStack.get());
    }

    private boolean validateIfCanIActivateStack(StackPosition stackPosition, Card card) {
        System.out.println(card.getRank().toString()+" "+stackPosition.getRank().toString());
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

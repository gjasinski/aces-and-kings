package pl.edu.agh.to2.acesandkings.game.model;

import java.util.List;
import java.util.Optional;

public interface CardStackRepository {
    void putCardOnStack(StackPosition position, Card card);

    void setUpNewCardOrder(StackPosition position, List<Card> cardsList);

    boolean removeCardFromStack(StackPosition position, Card card);

    Optional<Card> removeCardFromStack(StackPosition position);

    void changeStackState(StackPosition position);

    boolean isRemoveCardFromStackAllowed(StackPosition position, Card card);

    boolean isPutCardOnStackAllowed(StackPosition position, Card card);
}

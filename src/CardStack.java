import java.util.List;
import java.util.Optional;

public interface CardStack {
    void putCardOnStack(Card card);

    void setUpNewStack(List<Card> cardsList);

    boolean removeCardFromStack(Card card);

    Optional<Card> removeCardFromStack();

    void changeStackState();
}

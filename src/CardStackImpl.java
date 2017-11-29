import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardStackImpl implements CardStack {
    private State state;
    private List<Card> stack;
    private StackPosition position;

    public CardStackImpl(StackPosition position) {
        this.state = State.INACTIVE;
        this.stack = new ArrayList<>();
        this.position = position;
    }

    public List<Card> getStack() {
        return stack;
    }

    @Override
    public void putCardOnStack(Card card) {
        stack.add(card);
    }

    @Override
    public void setUpNewStack(List<Card> cardsList) {
        stack = cardsList;
    }

    @Override
    public boolean removeCardFromStack(Card card) {
        return stack.remove(card);
    }

    @Override
    public Optional<Card> removeCardFromStack() {
        return Optional.ofNullable(stack.remove(stack.size() - 1));
    }

    @Override
    public void changeStackState() {
        if (state == State.ACITVE)
            state = State.INACTIVE;
        else
            state = State.ACITVE;
    }
}

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
        Card card = null;
        if (!stack.isEmpty())
            card = stack.remove(stack.size() - 1);
        return Optional.ofNullable(card);
    }

    @Override
    public void changeStackState() {
        if (state == State.ACTIVE)
            state = State.INACTIVE;
        else
            state = State.ACTIVE;
    }

    public List<Card> getStack() {
        return stack;
    }

    public State getState() {
        return state;
    }

    public StackPosition getPosition() {
        return position;
    }
}

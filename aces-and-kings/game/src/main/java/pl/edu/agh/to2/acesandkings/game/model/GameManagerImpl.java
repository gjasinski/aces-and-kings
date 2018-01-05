package pl.edu.agh.to2.acesandkings.game.model;

import com.google.inject.Inject;
import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameManagerImpl implements GameManager {
    private CardDisposer cardDisposer;
    private CardValidator cardValidator;
    private CardStackRepositoryImpl cardStackRepository;
    private GamePlayer gamePlayer;

    @Inject
    public GameManagerImpl(CardStackRepositoryImpl cardStackRepository, GamePlayer gamePlayer) {
        List<CardStackImpl> cardStacks = initializeCardStacks();
        this.cardStackRepository = cardStackRepository;
        this.cardStackRepository.setCardStackList(cardStacks);
        this.cardValidator = new CardValidator();
        this.gamePlayer = gamePlayer;
    }

    @Override
    public List<CardStackObservable> initializeNewGame() {
        disposeCards();
        initializeGameInPlayer();
        return this.cardStackRepository.getCardStackList()
                .stream()
                .map(stack -> (CardStackObservable) stack)
                .collect(Collectors.toList());
    }

    private void disposeCards() {
        this.cardDisposer = new CardDisposer(this.cardStackRepository.getCardStackList());
        List<Card> cards = shuffleCards();
        if (!cardValidator.validate(cards)) {
            throw new IllegalArgumentException("CardDisposer - size of list of card should be equal 104 or 96, but is: "
                    + cards.size());
        }
        cardValidator.normaliseCardList(cards);
        cards.forEach(card -> cardDisposer.disposeCard(card));
        cardDisposer.flushDisposing();
    }

    private void initializeGameInPlayer() {
        List<CardStack> cardStacks = this.cardStackRepository.getCardStackList().stream().
                map(stack -> (CardStack) stack).
                collect(Collectors.toList());
        gamePlayer.startGame(cardStacks);
    }

    @Override
    public List<CardStackObservable> initializeSavedGame() {
        Board board = gamePlayer.restoreGame(0, -1);
        List<CardStack> cardStacks = board.getStacks();
        cardStacks.forEach(stack -> {
            addCardsToStack(stack);
            setStackState(stack);
        });
        return this.cardStackRepository.getCardStackList()
                .stream()
                .map(stack -> (CardStackObservable) stack)
                .collect(Collectors.toList());
    }

    private List<CardStackImpl> initializeCardStacks() {
        List<CardStackImpl> cardStacks = new ArrayList<>();
        for (StackPosition position : StackPosition.values()) {
            cardStacks.add(new CardStackImpl(position));
        }
        return cardStacks;
    }


    private void addCardsToStack(CardStack stack) {
        this.cardStackRepository.setUpNewCardOrder(stack.getPosition(), stack.getStack());
    }

    private void setStackState(CardStack stack) {
        this.cardStackRepository.changeStackState(stack.getPosition(), stack.getState());
        if (stack.getState() == State.ACTIVE) {
            this.cardStackRepository.moveCardsFromStackToStack(stack.getPosition(), StackPosition.HAND_STACK);
        }
    }

    private List<Card> shuffleCards() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }
}

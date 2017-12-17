package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.*;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class GameManagerImpl implements GameManager {
    private CardDisposer cardDisposer;
    private CardValidator cardValidator = new CardValidator();
    private CardStackRepositoryImpl cardStackRepository = new CardStackRepositoryImpl();//todo guice

    public GameManagerImpl() {
        List<CardStackImpl> cardStacks = initializeCardStacks();
        this.cardStackRepository.setCardStackList(cardStacks);
        this.cardDisposer = new CardDisposer(cardStacks);
        this.cardValidator = new CardValidator();
    }

    @Override
    public List<CardStackObservable> initializeNewGame() {
        List<Card> cards = shuffleCards(); //todo replace with final method
        if (!cardValidator.validate(cards)) {
            throw new IllegalArgumentException("CardDisposer - size of list of card should be equal 104 or 96, but is: "
                    + cards.size());
        }
        cardValidator.normaliseCardList(cards);
        cards.forEach(card -> cardDisposer.disposeCard(card));
        cardDisposer.flushDisposing();
        return this.cardStackRepository.getCardStackList()
                .stream()
                .map(stack -> (CardStackObservable) stack)
                .collect(Collectors.toList());
    }

    @Override
    public List<CardStackObservable> initializeSavedGame() {
        List<CardStack> cardStacks = new LinkedList<>(); //todo replace with final method
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

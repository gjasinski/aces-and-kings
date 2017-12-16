package pl.edu.agh.to2.acesandkings.game.model;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;

import java.util.List;

import static org.mockito.Mockito.mock;

public class GameManagerImpl implements GameManager{
    private CardDisposer cardDisposer = new CardDisposer();
    private CardValidator cardValidator = new CardValidator();

    GameManagerImpl(){
        //CardStackRepository cardStackRepository = new CardStackRepositoryImpl();
    }
    @Override
    public List<CardStackObservable> initializeNewGame() {
        List<Card> cards = mock(List.class);
        if(!cardValidator.validate(cards)){
            throw new IllegalArgumentException("CardDisposer - list of card should be equal 104 or 96, but is: " + cards.size());
        }
        cards.forEach(card -> cardDisposer.disposeCard(card));
        return cardDisposer.getDisposedCards();
    }

    @Override
    public List<CardStackObservable> initializeSavedGame() {
        return null;
    }
}

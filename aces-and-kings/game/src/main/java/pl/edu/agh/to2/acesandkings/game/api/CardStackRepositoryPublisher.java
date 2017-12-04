package pl.edu.agh.to2.acesandkings.game.api;

import io.reactivex.Observable;
import pl.edu.agh.to2.acesandkings.game.model.CardStack;

import java.util.List;

public interface CardStackRepositoryPublisher {
    Observable<List<CardStack>> getRepositoryObserver();

}

package pl.edu.agh.to2.acesandkings.game.api;

import io.reactivex.Observable;
import pl.edu.agh.to2.acesandkings.game.model.CardStackDTO;

import java.util.List;

public interface CardStackRepositoryPublisher {
    Observable<List<CardStackDTO>> getRepositoryObserver();

}

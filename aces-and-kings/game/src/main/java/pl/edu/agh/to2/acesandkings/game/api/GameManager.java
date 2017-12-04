package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;

import java.util.List;

public interface GameManager {
    List<CardStackObservable> initializeNewGame();

    List<CardStackObservable> initializeSavedGame();

}

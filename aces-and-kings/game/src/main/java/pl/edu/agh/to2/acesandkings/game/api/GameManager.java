package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.common.model.CardStack;

import java.util.List;

public interface GameManager {
    List<CardStack> initializeNewGame();

    List<CardStack> initializeSavedGame();

}

package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.game.model.CardStackDTO;

import java.util.List;

public interface GameManager {
    List<CardStackDTO> initializeNewGame();

    List<CardStackDTO> initializeSavedGame();

}

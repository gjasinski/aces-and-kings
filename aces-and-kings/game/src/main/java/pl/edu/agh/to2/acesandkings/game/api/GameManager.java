package pl.edu.agh.to2.acesandkings.game.api;

import pl.edu.agh.to2.acesandkings.game.model.ObservableCardStackDTO;

import java.util.List;

public interface GameManager {
    List<ObservableCardStackDTO> initializeNewGame();

    List<ObservableCardStackDTO> initializeSavedGame();

}

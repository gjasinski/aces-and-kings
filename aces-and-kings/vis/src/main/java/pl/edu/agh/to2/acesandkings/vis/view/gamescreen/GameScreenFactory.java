package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import pl.edu.agh.to2.acesandkings.game.model.CardStackDTO;

import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenFactory {
    public GameScreenView createGameScreen(final List<CardStackDTO> cardStackDTOS) {
        return new GameScreenView();
    }
}

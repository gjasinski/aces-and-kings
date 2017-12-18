package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenFactory {
    public GameScreenView createGameScreen(final List<CardStackObservable> cardStackObservables, Stage primaryStage) {
        Map<StackPosition, CardStackObservable> map = new HashMap<>();
        for(CardStackObservable cso : cardStackObservables){
            map.put(cso.getPosition(), cso);
        }
        BoardView boardView = new BoardView(map, primaryStage);
        return new GameScreenView(boardView);
    }
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.CardResizer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenFactory {
    public GameScreenView createGameScreen(final List<CardStackObservable> cardStackObservables, Stage primaryStage) {
        final Map<StackPosition, CardStackObservable> map = new HashMap<>();
        for(CardStackObservable cso : cardStackObservables){
            map.put(cso.getPosition(), cso);
        }
        final GameScreenView gameScreenView = new GameScreenView(primaryStage);
        final BoardView boardView = new BoardView(map, new CardResizer());
        gameScreenView.setBoard(boardView);
        gameScreenView.setUndoButton(new UndoButtonView());
        gameScreenView.setRedoButton(new RedoButtonView());
        gameScreenView.setMenuButton(new MenuButtonView());
        gameScreenView.setDeactCSButton(new DeactCSButtonView());
        gameScreenView.setEnlargeCardsButton(new EnlargeCardsButtonView(boardView));
        gameScreenView.setShrinkCardsButton(new ShrinkCardsButtonView(boardView));
        maximizeWindow(primaryStage);
        return gameScreenView;
    }

    private void maximizeWindow(final Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();

        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }
}

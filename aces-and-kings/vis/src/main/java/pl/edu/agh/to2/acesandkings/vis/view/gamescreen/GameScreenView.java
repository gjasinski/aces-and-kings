package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.ScreenView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenView extends ScreenView implements GameControllable {
    private final Stage stage;
    private final BorderPane borderPane = new BorderPane();
    private final StackPane undoButtonPlace = new StackPane();
    private final StackPane redoButtonPlace = new StackPane();
    private final StackPane menuButtonPlace = new StackPane();
    private final List<GameControllable> controllables = new ArrayList<>();
    private BoardView boardView;

    private static final String BACKGROUND_STYLE = "-fx-background-color: #4C664C";

    public GameScreenView(final Stage stage) {
        this.stage = stage;
        setUpButtonRow();
        setUpRightColumn();
        borderPane.setPrefSize(950, 740);
        stage.setScene(new Scene(borderPane));
        borderPane.setStyle(BACKGROUND_STYLE);
    }

    private void setUpButtonRow() {
        final HBox buttonRow = new HBox();
        buttonRow.setPadding(new Insets(20, 20, 20, 20));
        buttonRow.setSpacing(10);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);
        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        buttonRow.getChildren().addAll(spacer, undoButtonPlace, redoButtonPlace);
        borderPane.setBottom(buttonRow);
    }

    private void setUpRightColumn() {
        final VBox rightColumn = new VBox();
        rightColumn.setPadding(new Insets(20, 20, 20, 20));
        rightColumn.getChildren().add(menuButtonPlace);
        borderPane.setRight(rightColumn);
    }

    public void setBoard(final BoardView boardView) {
        borderPane.setCenter(boardView.getNode());
        this.boardView = boardView;
        controllables.add(boardView);
    }

    public void setUndoButton(final UndoButtonView button) {
        undoButtonPlace.getChildren().clear();
        undoButtonPlace.getChildren().add(button.getNode());
        controllables.add(button);
    }

    public void setRedoButton(final RedoButtonView button) {
        redoButtonPlace.getChildren().clear();
        redoButtonPlace.getChildren().add(button.getNode());
        controllables.add(button);
    }

    public void setMenuButton(final MenuButtonView button) {
        menuButtonPlace.getChildren().clear();
        menuButtonPlace.getChildren().add(button.getNode());
        controllables.add(button);
    }

    public void show() {
        boardView.draw();
        stage.show();
    }

    public void connectController(final GameController controller) {
        controllables.forEach(controllable -> controllable.connectController(controller));
    }
}

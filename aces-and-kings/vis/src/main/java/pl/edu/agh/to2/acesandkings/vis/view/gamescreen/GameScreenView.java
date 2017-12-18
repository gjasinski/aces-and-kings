package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.vis.view.ScreenView;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenView extends ScreenView {
    private final Stage stage;
    private final BorderPane borderPane = new BorderPane();
    private final StackPane undoButtonPlace = new StackPane();
    private final StackPane redoButtonPlace = new StackPane();
    private final StackPane menuButtonPlace = new StackPane();

    private static final String BACKGROUND_STYLE = "-fx-background-color: #009900";

    public GameScreenView(final Stage stage) {
        this.stage = stage;
        setUpButtonRow();
        setUpRightColumn();
        stage.setScene(new Scene(borderPane));
    }

    private void setUpButtonRow() {
        final HBox buttonRow = new HBox();
        buttonRow.setPadding(new Insets(20, 20, 20, 20));
        buttonRow.setSpacing(10);
        buttonRow.setStyle(BACKGROUND_STYLE);
        buttonRow.setAlignment(Pos.CENTER_RIGHT);
        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        buttonRow.getChildren().addAll(spacer, undoButtonPlace, redoButtonPlace);
        borderPane.setBottom(buttonRow);
    }

    private void setUpRightColumn() {
        final VBox rightColumn = new VBox();
        rightColumn.setPadding(new Insets(20, 20, 20, 20));
        rightColumn.setStyle(BACKGROUND_STYLE);
        rightColumn.getChildren().add(menuButtonPlace);
        borderPane.setRight(rightColumn);
    }

    public void setBoard(final BoardView boardView) {
        borderPane.setCenter(boardView.getNode());
    }

    public void setUndoButton(final UndoButtonView button) {
        undoButtonPlace.getChildren().clear();
        undoButtonPlace.getChildren().add(button.getNode());
    }

    public void setRedoButton(final RedoButtonView button) {
        redoButtonPlace.getChildren().clear();
        redoButtonPlace.getChildren().add(button.getNode());
    }

    public void setMenuButton(final MenuButtonView button) {
        menuButtonPlace.getChildren().clear();
        menuButtonPlace.getChildren().add(button.getNode());
    }
    public static class TemporaryMainToRemove extends Application {
        @Override
        public void start(final Stage primaryStage) throws Exception {
            final GameScreenView gameScreenView = new GameScreenView(primaryStage);
            gameScreenView.setUndoButton(new UndoButtonView());
            gameScreenView.setRedoButton(new RedoButtonView());
            gameScreenView.setMenuButton((new MenuButtonView()));
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}

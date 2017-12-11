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

    public GameScreenView(final Stage stage) {
        this.stage = stage;
        setUpButtonRow();
        stage.setScene(new Scene(borderPane));
    }

    private void setUpButtonRow() {
        final HBox buttonRow = new HBox();
        buttonRow.setPadding(new Insets(20, 20, 20, 20));
        buttonRow.setSpacing(10);
        buttonRow.setStyle("-fx-background-color: #009900");
        buttonRow.setAlignment(Pos.CENTER_RIGHT);
        final Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        buttonRow.getChildren().addAll(spacer, undoButtonPlace, redoButtonPlace);
        borderPane.setBottom(buttonRow);
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


    public static class TemporaryMainToRemove extends Application {
        @Override
        public void start(final Stage primaryStage) throws Exception {
            final GameScreenView gameScreenView = new GameScreenView(primaryStage);
            final UndoButtonView undoButton = new UndoButtonView();
            final RedoButtonView redoButton = new RedoButtonView();
            gameScreenView.setUndoButton(undoButton);
            gameScreenView.setRedoButton(redoButton);
            primaryStage.show();
        }

        public static void main(String[] args) {
            launch(args);
        }
    }
}

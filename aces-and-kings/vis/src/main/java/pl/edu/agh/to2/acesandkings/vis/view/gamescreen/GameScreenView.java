package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import pl.edu.agh.to2.acesandkings.vis.view.ScreenView;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class GameScreenView extends ScreenView {
    private BoardView board;
    private final UndoButtonView undoButton = null;
    private final RedoButtonView redoButton = null;

    public GameScreenView(BoardView board){
        this.board = board;
    }

    public void show(){
        this.board.draw();
    }
    /*Button undoButton = new Button();
        undoButton.setText("Undo");
        undoButton.setOnAction(e -> {
            gameController.handleUndoAction();
        });
        Button redoButton = new Button();
        redoButton.setText("Start new game");
        redoButton.setOnAction(e -> {
            gameController.handleRedoAction();
        });
        GridPane gamePane = new GridPane();
        gamePane.getChildren().addAll(undoButton, redoButton);
        Scene gameScene = new Scene(gamePane, 600, 400);
        primaryStage.setScene(gameScene);*/
   // private final UndoButtonView undoButton = null;
   // private final RedoButtonView redoButton = null;
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.ButtonView;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class RedoButtonView extends ButtonView implements GameControllable {
    private final Button button = new Button("Redo");

    @Override
    public void connectController(final GameController controller) {
        button.setOnMouseClicked(event -> controller.handleRedoAction());
    }

    public Node getNode() {
        return button;
    }

}

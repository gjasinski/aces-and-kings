package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.ButtonView;

public class DeactCSButtonView extends ButtonView implements GameControllable {
    private final Button button = new Button("Put back hand stack");

    @Override
    public void connectController(final GameController controller) {
        button.setOnAction(event -> controller.handleDisactivateCardStackAction());
    }

    public Node getNode() {
        return button;
    }

}

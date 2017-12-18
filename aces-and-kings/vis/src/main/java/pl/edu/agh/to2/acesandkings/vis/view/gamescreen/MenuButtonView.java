package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.ButtonView;

/**
 * Created by Paweł Grochola on 11.12.2017.
 */
public class MenuButtonView extends ButtonView implements GameControllable {
    private final Button button = new Button("Menu");

    @Override
    public void connectController(final GameController controller) {
        button.setOnMouseClicked(event -> controller.handleMenuAction());
    }

    public Node getNode() {
        return button;
    }
}

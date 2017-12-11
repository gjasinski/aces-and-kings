package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pl.edu.agh.to2.acesandkings.vis.view.ButtonView;

/**
 * Created by Paweł Grochola on 11.12.2017.
 */
public class MenuButtonView extends ButtonView {
    private final Button button = new Button("Menu");

    public Node getNode() {
        return button;
    }
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Node;
import javafx.scene.control.Button;
import pl.edu.agh.to2.acesandkings.vis.view.ButtonView;

public class EnlargeCardsButtonView extends ButtonView {
    private final Button button = new Button("+");

    public EnlargeCardsButtonView(final BoardView boardView) {
        button.setOnMouseClicked(event -> boardView.enlargeCards());
    }

    public Node getNode() {
        return button;
    }

}

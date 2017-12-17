package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;

import java.util.List;

public class ExtraCardStackView extends CardStackView {
    public ExtraCardStackView(List<Card> cardList) {
        super(cardList);
    }
    @Override
    public List<ImageView> draw(int x, int y) {
        return super.draw(x, y);
    }
}

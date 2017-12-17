package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class BorderCardStackView extends CardStackView {
    public BorderCardStackView(List<Card> cardList) {
        super(cardList);
    }

    public List<ImageView> draw(int x, int y) {
        List<ImageView> cardViewList = new LinkedList<>();
        int i = y;

        CardView cardView = new CardView(cardList.get(cardList.size()-1));
        cardViewList.add(cardView.draw(x, i));
        return cardViewList;
    }
}

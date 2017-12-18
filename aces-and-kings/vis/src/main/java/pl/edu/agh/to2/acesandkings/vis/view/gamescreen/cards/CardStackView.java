package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class CardStackView {
    private final int space = 10;

    List<Card> cardList;

    public CardStackView(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<ImageView> draw(int x, int y) {
        List<ImageView> cardViewList = new LinkedList<>();
        int i = y;

        for (Card card : cardList) {
            CardView cardView = new CardView(card);
            cardViewList.add(cardView.draw(x, i));
            i += space;
        }

        return cardViewList;
    }
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class HandCardStackView extends CardStackView {
    public HandCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }


    private final int space = 80;

    public List<ImageView> draw(int x, int y) {
        List<ImageView> cardViewList = new LinkedList<>();
        int i = x;

        for (Card card : cardList) {
            CardView cardView = new CardView(card);
            cardViewList.add(cardView.draw(i, y));
            i += space;
        }

        return cardViewList;
    }
}

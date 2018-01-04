package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

import java.util.List;

public class ExtraCardStackView extends CardStackView {
    public ExtraCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }
//    @Override
//    public List<ImageView> draw(int x, int y) {
//        return super.draw(x, y);
//    }
}

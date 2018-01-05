package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
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
public class CardStackView{
    private final int space = 10;
    private int x;
    private int y;
    private int top_card_y;

    StackPosition stackPosition;
    ObservableList<Card> cardList;
    BoardView board;

    public CardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        this.cardList = cardList;
        this.stackPosition = stackPosition;
        this.board = board;
        cardList.addListener((ListChangeListener<? super Card>) e->this.draw(x, y));

    }


    public List<ImageView> draw(int x, int y) {
        this.x = x;
        this.y = y;
        List<ImageView> cardViewList = new LinkedList<>();
        int i = y;
        this.top_card_y = i;

        for (Card card : cardList) {
            CardView cardView = new CardView(card);
            cardView.getImageView().setOnMouseMoved(e -> System.out.println(this.stackPosition.toString()));
            cardView.getImageView().setOnMousePressed(e->this.board.setSourceStack(this.stackPosition));
            cardView.getImageView().setOnMouseReleased(e -> this.board.setDestStack(this.stackPosition));
            cardViewList.add(cardView.draw(x, i));
            i += space;
            this.top_card_y = i;
        }

        return cardViewList;
    }

}

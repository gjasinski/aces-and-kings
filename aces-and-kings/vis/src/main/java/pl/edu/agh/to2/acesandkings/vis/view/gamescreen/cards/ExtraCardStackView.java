package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

import java.util.LinkedList;
import java.util.List;

public class ExtraCardStackView extends CardStackView {
    private final int space = 10;
    public ExtraCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }

    @Override
    protected void addCardStackListener(){
        cardList.addListener((ListChangeListener.Change<? extends Card> e) -> {
            while(e.next()) {
                if (e.wasRemoved()) {
                    System.out.println(e.getRemovedSize());
                    System.out.println("Change event in " + this.stackPosition.toString() + "!");
                    this.clear();
//                    this.draw(this.x, this.y);
                    this.board.drawExtraCardStack();
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
                else if(e.wasAdded()) {
                    System.out.println(e.getAddedSize());
                    System.out.println("Change event in " + this.stackPosition.toString() + "!");
                    this.clear();
//                    this.draw(this.x, this.y);
                    this.board.drawExtraCardStack();
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        });
    }
//    @Override
//    public List<ImageView> draw(int x, int y) {
//        return super.draw(x, y);
//    }
//    @Override
//    public List<ImageView> draw(int x, int y) {
//        this.x = x;
//        this.y = y;
//        List<ImageView> cardViewList = new LinkedList<>();
//        int i = y;
//        this.top_card_y = i;
//
//        for (Card card : cardList) {
//            CardView cardView = new CardView(card);
//            cardView.getImageView().setOnMouseMoved(e -> System.out.println(this.stackPosition.toString()));
//            cardView.getImageView().setOnMousePressed(e->this.board.setSourceStack(this.stackPosition));
//            cardView.getImageView().setOnMouseReleased(e -> this.board.setDestStack(this.stackPosition));
//            cardViewList.add(cardView.draw(x, i));
//            i += space;
//            this.top_card_y = i;
//        }
//
//        return cardViewList;
//    }

}

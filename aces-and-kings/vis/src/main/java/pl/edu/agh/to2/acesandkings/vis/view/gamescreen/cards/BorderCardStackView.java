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
public class BorderCardStackView extends CardStackView {
    public BorderCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
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
                    this.board.drawBorderCardStack(this.stackPosition, this.x, this.y);
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
                    this.board.drawBorderCardStack(this.stackPosition, this.x, this.y);
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        });
    }

//    public List<ImageView> draw(int x, int y) {
//        List<ImageView> cardViewList = new LinkedList<>();
//        int i = y;
//
//        CardView cardView = new CardView(cardList.get(cardList.size()-1));
//        cardViewList.add(cardView.draw(x, i));
//        return cardViewList;
//    }
}

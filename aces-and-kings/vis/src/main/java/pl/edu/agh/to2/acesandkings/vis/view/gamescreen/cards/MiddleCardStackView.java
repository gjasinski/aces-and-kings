package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class MiddleCardStackView extends CardStackView {
    public MiddleCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }

    protected void addCardStackListener(){
        cardList.addListener((ListChangeListener.Change<? extends Card> e) -> {
            while(e.next()) {
                if (e.wasRemoved()) {
                    System.out.println(e.getRemovedSize());
                    System.out.println("Change event in " + this.stackPosition.toString() + "!");
                    this.clear();
//                    this.draw(this.x, this.y);
                    this.board.drawMiddleCardStack(this.stackPosition, this.x, this.y);
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
                    this.board.drawMiddleCardStack(this.stackPosition, this.x, this.y);
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
}

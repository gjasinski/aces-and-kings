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
public class HandCardStackView extends CardStackView {
    public HandCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }


    private final int space = 80;
    private ListChangeListener<Card> cardStackListener;

    @Override
    public List<ImageView> draw(int x, int y) {
        List<ImageView> cardViewList = new LinkedList<>();
        int i = x;

        for (Card card : cardList) {
            CardView cardView = new CardView(card);
            addEventHandlersToCV(cardView);
            cardViewList.add(cardView.draw(i, y));
            cardViews.add(cardView);
            i += space;
        }

        return cardViewList;
    }

    @Override
    protected void addEventHandlersToCV(CardView cardView){
        cardView.getImageView().setOnMousePressed(e->this.board.handleMoveCardFromHS(cardView.getCard()));
        cardView.getImageView().setOnDragDetected(e-> {cardView.getImageView().startFullDrag(); cardView.getImageView().setOnMouseReleased(e2 ->{});} );
        cardView.getImageView().setOnMouseDragReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});
        cardView.getImageView().setOnMouseReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});

    }

    @Override
    protected void addCardStackListener(){
        this.cardStackListener = new ListChangeListener<Card>(){
            @Override
            public void onChanged(Change<? extends Card> e) {
                while(e.next()) {
                    if (e.wasRemoved()||e.wasAdded()) {
                        clear();
                        board.drawHandCardStack();
                        System.out.println(e.getAddedSize());
                        System.out.println("Change! "+stackPosition.toString());
                        try {
                            this.finalize();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                }
            }
        };
        cardList.addListener(cardStackListener);
    }

    protected void clear(){
        for(CardView cardView: cardViews){
            cardView.finalize();
        }
        this.cardViews.clear();
        this.cardList.removeListener(cardStackListener);
    }
}

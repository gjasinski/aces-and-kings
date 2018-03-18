package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class HandCardStackView extends CardStackView {
    public HandCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board, final CardResizer cardResizer) {
        super(cardList, stackPosition, board, cardResizer);
    }

    private static final int horizontalSpace = 80;
    private ListChangeListener<Card> cardStackListener;

    @Override
    public void draw() {
        int x = 0;
        final int y = 0;
        for(final Card card : cardList) {
            final CardView cardView = new CardView(card);
            cardResizer.updateCardSize(cardView);
            cardView.draw(x,y);
            cardViews.add(cardView);
            addEventHandlersToCV(cardView);
            group.getChildren().add(cardView.getImageView());
            x += horizontalSpace;
        }
    }

    @Override
    public void redraw() {
        int x = 0;
        final int y = 0;
        for(final CardView cardView : cardViews) {
            cardView.draw(x,y);
            cardResizer.updateCardSize(cardView);
            x += horizontalSpace;
        }
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

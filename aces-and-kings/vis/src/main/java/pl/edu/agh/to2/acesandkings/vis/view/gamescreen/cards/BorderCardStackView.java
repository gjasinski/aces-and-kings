package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class BorderCardStackView extends CardStackView {
    public BorderCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        super(cardList, stackPosition, board);
    }

    @Override
    public void draw() {
        final int x = 0;
        final int y = 0;
        for(final Card card : cardList) {
            final CardView cardView = new CardView(card);
            cardViews.add(cardView);
            addEventHandlersToCV(cardView);
            cardView.draw(x,y);
            group.getChildren().add(cardView.getImageView());
        }
    }

    @Override
    protected void addCardStackListener(){
        this.cardStackListener = new ListChangeListener<Card>(){
            @Override
            public void onChanged(Change<? extends Card> e) {
                while(e.next()) {
                    if (e.wasRemoved()||e.wasAdded()) {
                        clear();
                        board.drawBorderCardStack(stackPosition);

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
}

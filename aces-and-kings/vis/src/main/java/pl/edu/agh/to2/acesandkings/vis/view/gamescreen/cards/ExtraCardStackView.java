package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

public class ExtraCardStackView extends CardStackView {
    public ExtraCardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board, final CardResizer cardResizer) {
        super(cardList, stackPosition, board, cardResizer);
    }

    @Override
    protected void addCardStackListener(){
        this.cardStackListener = new ListChangeListener<Card>(){
            @Override
            public void onChanged(Change<? extends Card> e) {
                while(e.next()) {
                    if (e.wasRemoved()||e.wasAdded()) {
                        clear();
                        board.drawExtraCardStack();

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

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class CardStackView{
    private static final int dy = 10;
    protected ListChangeListener<Card> cardStackListener;
    protected final Group group = new Group();

    protected final StackPosition stackPosition;
    public ObservableList<Card> cardList;
    public BoardView board;
    protected List<CardView> cardViews;


    public CardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        this.cardList = cardList;
        this.stackPosition = stackPosition;
        this.board = board;
        this.cardViews = new ArrayList<>();
        this.addCardStackListener();
    }

    public void draw() {
        final int x = 0;
        int y = 0;
        for(final Card card : cardList) {
            final CardView cardView = new CardView(card);
            cardViews.add(cardView);
            addEventHandlersToCV(cardView);
            cardView.draw(x,y);
            group.getChildren().add(cardView.getImageView());
            y += dy;
        }
    }

    protected void addEventHandlersToCV(CardView cardView){
        cardView.getImageView().setOnMousePressed(e->this.board.setSourceStack(this.stackPosition));
        cardView.getImageView().setOnDragDetected(e-> {cardView.getImageView().startFullDrag(); cardView.getImageView().setOnMouseReleased(e2 ->{});} );
        cardView.getImageView().setOnMouseDragReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});
        cardView.getImageView().setOnMouseReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});

    }

    protected void addCardStackListener(){
    }

    protected void clear(){
        for(CardView cardView: cardViews){
            cardView.finalize();
        }
        this.cardViews.clear();
        this.cardList.removeListener(cardStackListener);
    }

    public Node getGroup() {
        return group;
    }

    public StackPosition getStackPosition() {
        return stackPosition;
    }
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.BoardView;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class CardStackView{
    protected final int space = 10;
    protected int x;
    protected int y;
    protected int top_card_y;

    public StackPosition stackPosition;
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

    public List<ImageView> draw(int x, int y) {
        this.x = x;
        this.y = y;
        List<ImageView> cardViewList = new LinkedList<>();
        int i = y;
        this.top_card_y = i;
        this.cardViews = new ArrayList<>();

        for (Card card : cardList) {
            CardView cardView = new CardView(card);
            cardViewList.add(cardView.draw(x, i));
            addEventHandlersToCV(cardView);
            cardViews.add(cardView);
            i += space;
            this.top_card_y = i;
        }

        return cardViewList;
    }

    protected void addEventHandlersToCV(CardView cardView){
        cardView.getImageView().setOnMousePressed(e->this.board.setSourceStack(this.stackPosition));
        cardView.getImageView().setOnDragDetected(e-> {cardView.getImageView().startFullDrag(); cardView.getImageView().setOnMouseReleased(e2 ->{});} );
        cardView.getImageView().setOnMouseDragReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});
        cardView.getImageView().setOnMouseReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});

    }

    protected void addCardStackListener(){
        cardList.addListener((ListChangeListener.Change<? extends Card> e) -> {
            while(e.next()) {
                if (e.wasRemoved()) {
                    System.out.println(e.getRemovedSize());
                    System.out.println("Change event in " + this.stackPosition.toString() + "!");
                    this.clear();
//                    this.draw(this.x, this.y);
                    this.board.draw();
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
                    this.board.draw();
                    try {
                        this.finalize();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }
            }
        });
    }

    protected void clear(){
        for(CardView cardView: cardViews){
            cardView.finalize();
        }
        this.cardViews.clear();
    }

    public ObservableList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ObservableList<Card> cardList) {
        this.cardList = cardList;
    }
}

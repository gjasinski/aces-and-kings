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
    private final int space = 10;
    private int x;
    private int y;
    private int top_card_y;

    StackPosition stackPosition;
    ObservableList<Card> cardList;
    BoardView board;
    protected List<CardView> cardViews;


    public CardStackView(ObservableList<Card> cardList, StackPosition stackPosition, BoardView board) {
        this.cardList = cardList;
        this.stackPosition = stackPosition;
        this.board = board;
        this.cardViews = new ArrayList<>();
        cardList.addListener((ListChangeListener<? super Card>) e -> {
            System.out.println("Change event!");
            this.clear();
            //this.draw(this.x, this.y);
        });

//        cardList.addListener((ListChangeListener<? super Card>) e->this.draw(x, y));

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
            cardView.getImageView().setOnDragDetected(e-> {cardView.getImageView().startFullDrag(); cardView.getImageView().setOnMouseReleased(e2 ->{});} );
            cardView.getImageView().setOnMouseDragReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});
            cardView.getImageView().setOnMouseReleased(e ->{System.out.println(this.stackPosition); this.board.setDestStack(this.stackPosition);});
            cardViewList.add(cardView.draw(x, i));
            cardViews.add(cardView);
            i += space;
            this.top_card_y = i;
        }

//        this.imageViews = cardViewList;
        return cardViewList;
    }

    private void clear(){
//        final ClassLoader classLoader = getClass().getClassLoader();
//        String path = classLoader.getResource("cards/boarderaser.png").getFile();
//        try {
//            path = URLDecoder.decode(path, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        File file = new File(path);
//        BufferedImage imageb = null;
//
//        try {
//            imageb = ImageIO.read(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Image image = SwingFXUtils.toFXImage(imageb, null);
//        for(int y = this.y; y<=this.top_card_y; y+=this.space){
//            ImageView imageView = new ImageView();
//            imageView.setFitHeight(60);
//            imageView.setFitWidth(60);
//            imageView.setImage(image);
//            imageView.setX(this.x);
//            imageView.setY(y);
//            imageView.setPreserveRatio(true);
//            System.out.println("ocb");
//        }
        for(CardView cardView: cardViews){
//            cardView.getImageView().setX(570);
//            cardView.getImageView().setY(100);
            cardView.getImageView().setVisible(false);
        }
    }

    public ObservableList<Card> getCardList() {
        return cardList;
    }

    public void setCardList(ObservableList<Card> cardList) {
        this.cardList = cardList;
    }
}

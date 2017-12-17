package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.Suit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardView {
    private Card card;

    public CardView(Card card) {
        this.card = card;
    }

    public ImageView draw(int x, int y)  {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(resolvePath()).getFile());
        BufferedImage imageb = null;

        try {
            imageb = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = SwingFXUtils.toFXImage(imageb, null);
        ImageView img = new ImageView();
        img.setImage(image);
        img.setX(x);
        img.setY(y);

        return img;
    }

    private String resolvePath(){
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return "cards/"+rank.toString()+suit.toString() + ".png";
    }
}

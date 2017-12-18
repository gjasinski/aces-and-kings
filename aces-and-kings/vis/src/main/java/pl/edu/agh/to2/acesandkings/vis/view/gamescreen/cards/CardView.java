package pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.Rank;
import pl.edu.agh.to2.acesandkings.common.model.Suit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class CardView {
    private Card card;
    private ImageView img;

    public CardView(Card card) {
        this.card = card;
        final ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource(resolvePath()).getFile();
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        BufferedImage imageb = null;

        try {
            imageb = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = SwingFXUtils.toFXImage(imageb, null);
        img = new ImageView();

        img.setFitHeight(60);
        img.setFitWidth(60);
        img.setImage(image);
    }

    public ImageView draw(int x, int y)  {

        img.setX(x);
        img.setY(y);
        img.setPreserveRatio(true);
        return img;
    }

    private String resolvePath(){
        Rank rank = card.getRank();
        Suit suit = card.getSuit();
        return "cards/"+rank.toString()+suit.toString() + ".png";
    }
}

package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.common.model.CardStack;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.BorderCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.ExtraCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.HandCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.MiddleCardStackView;

import java.util.List;
import java.util.Map;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class BoardView {

    Group root = new Group();
    Color c = new Color(0.3, 0.4, 0.3, 1);
    Scene scene = new Scene(root, 700, 650, c);

    private Map<StackPosition, CardStack> cardStacks;
    private Stage primaryStage;

    public BoardView(Map<StackPosition, CardStack> cardStacks, Stage primaryStage) {
        this.cardStacks = cardStacks;
        this.primaryStage = primaryStage;
    }

    public void draw() {

        drawBorderCardStack();
        drawMiddleCardStack();
        drawHandCardStack();
        drawExtraCardStack();

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void addStack(List<ImageView> stacks, Group root) {
        for (ImageView imageView : stacks
                ) {
            imageView.setPickOnBounds(true);
            imageView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println("Clicked!");
            });
            root.getChildren().add(imageView);
        }
    }

    private void drawBorderCardStack() {
        int y = 10;
        int x = 10;
        for (int i = 14; i <= 20; i = i + 2) {
            StackPosition stackPosition = StackPosition.values()[i];
            BorderCardStackView borderCardStackView = new BorderCardStackView(cardStacks.get(stackPosition).getStack());
            addStack(borderCardStackView.draw(x, y), root);
            y += 145;
        }
        y = 10;
        x = 500;
        for (int i = 13; i <= 19; i = i + 2) {
            StackPosition stackPosition = StackPosition.values()[i];
            BorderCardStackView borderCardStackView = new BorderCardStackView(cardStacks.get(stackPosition).getStack());
            addStack(borderCardStackView.draw(x, y), root);
            y += 145;
        }
    }

    private void drawMiddleCardStack() {
        int y = 10;
        for (int i = 0; i < 4; i++) {
            int x = 130;
            for (int j = 1; j <= 3; j++) {
                StackPosition stackPosition = StackPosition.values()[j + 3 * i];
                MiddleCardStackView middleCardStackView = new MiddleCardStackView(cardStacks.get(stackPosition).getStack());
                addStack(middleCardStackView.draw(x, y), root);
                x = x + 130;
            }
            y += 145;
        }
    }

    private void drawHandCardStack() {
        int y =560;
        int x =50;

        StackPosition stackPosition = StackPosition.values()[21];
        HandCardStackView handCardStackView = new HandCardStackView(cardStacks.get(stackPosition).getStack());
        addStack(handCardStackView.draw(x, y), root);
    }

    private void drawExtraCardStack() {
        int y =400;
        int x =600;

        StackPosition stackPosition = StackPosition.values()[22];
        ExtraCardStackView extraCardStackView = new ExtraCardStackView(cardStacks.get(stackPosition).getStack());
        addStack(extraCardStackView.draw(x, y), root);
    }
}
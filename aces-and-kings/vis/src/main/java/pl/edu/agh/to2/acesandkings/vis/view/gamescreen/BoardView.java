package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.BorderCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.ExtraCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.HandCardStackView;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.MiddleCardStackView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class BoardView implements GameControllable {

    private final Group root = new Group();
    private GameController gameController;

    private Map<StackPosition, CardStackObservable> cardStacks;

    private StackPosition sourceStack = null;
    private StackPosition destStack = null;

    public BoardView(Map<StackPosition, CardStackObservable> cardStacks) {
        this.cardStacks = cardStacks;
    }

    public void draw() {
        drawBorderCardStack();
        drawMiddleCardStack();
        drawHandCardStack();
        drawExtraCardStack();
    }

    public Node getNode() {
        return root;
    }

    private void addStack(List<ImageView> stacks, Group root) {
        for (ImageView imageView : stacks) {
            imageView.setPickOnBounds(true);
            imageView.setOnMouseClicked((MouseEvent e) -> {
                System.out.println("Clicked!");
            });
            root.getChildren().add(imageView);
        }
    }

    private void drawBorderCardStack() {
        List<StackPosition> stackPositionsAce = Arrays.asList(StackPosition.SPADES_ACE, StackPosition.CLUBS_ACE, StackPosition.HEART_ACE, StackPosition.DIAMONDS_ACE);
        int y = 10;
        int x = 10;
        for (int i = 0; i <4; i ++) {
            BorderCardStackView borderCardStackView = new BorderCardStackView(cardStacks.get(stackPositionsAce.get(i)).getUnmodifiableObservableStack(), stackPositionsAce.get(i), this);
            addStack(borderCardStackView.draw(x, y), root);
            y += 145;
        }

        y = 10;
        x = 500;

        List<StackPosition> stackPositionsKing = Arrays.asList(StackPosition.SPADES_KING, StackPosition.CLUBS_KING, StackPosition.HEART_KING, StackPosition.DIAMONDS_KING);

        for (int i = 0; i < 4; i ++) {
            BorderCardStackView borderCardStackView = new BorderCardStackView(cardStacks.get(stackPositionsKing.get(i)).getUnmodifiableObservableStack(), stackPositionsKing.get(i), this);
            addStack(borderCardStackView.draw(x, y), root);
            y += 145;
        }
    }

    private void drawMiddleCardStack() {
        List<StackPosition> stackPositions = Arrays.asList(StackPosition.ACE,StackPosition.TWO, StackPosition.THREE, StackPosition.FOUR, StackPosition.FIVE,
                StackPosition.SIX, StackPosition.SEVEN, StackPosition.EIGHT, StackPosition.NINE, StackPosition.TEN, StackPosition.JACK, StackPosition.QUEEN);
        int y = 10;
        for (int i = 0; i < 4; i++) {
            int x = 130;
            for (int j = 0; j < 3; j++) {
                MiddleCardStackView middleCardStackView = new MiddleCardStackView(cardStacks.get(stackPositions.get(j+3*i)).getUnmodifiableObservableStack(), stackPositions.get(j+3*i), this);
                addStack(middleCardStackView.draw(x, y), root);
                x = x + 130;
            }
            y += 145;
        }
    }

    private void drawHandCardStack() {
        int y = 560;
        int x = 50;

        HandCardStackView handCardStackView = new HandCardStackView(cardStacks.get(StackPosition.HAND_STACK).getUnmodifiableObservableStack(), StackPosition.HAND_STACK, this);
        addStack(handCardStackView.draw(x, y), root);
    }

    private void drawExtraCardStack() {
        int y = 400;
        int x = 600;

        ExtraCardStackView extraCardStackView = new ExtraCardStackView(cardStacks.get(StackPosition.EXTRA_STACK).getUnmodifiableObservableStack(), StackPosition.EXTRA_STACK, this);
        addStack(extraCardStackView.draw(x, y), root);
    }

    @Override
    public void connectController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setSourceStack(StackPosition stackPosition){
        System.out.println("Set source stack: "+stackPosition.toString());
        this.sourceStack = stackPosition;
    }

    public void setDestStack(StackPosition stackPosition){
        if(this.sourceStack!=null && this.sourceStack == stackPosition) {
            System.out.println("Set dest stack: "+stackPosition.toString());
            this.destStack = stackPosition;
            //this.gameController.handleMoveCardAction(this.sourceStack, this.destStack);
            this.gameController.handleActivateCardsStackAction(this.sourceStack);
        }else if(this.sourceStack!=null){
            System.out.println("Set dest stack: "+stackPosition.toString());
            this.destStack = stackPosition;
            this.gameController.handleMoveCardAction(this.sourceStack, this.destStack);
        }
        this.sourceStack=null;
        this.destStack=null;
    }
    //implementation is to change during implementation
   // private final Map<StackPosition, CardStackView> cardStacks = new HashMap<>();
}


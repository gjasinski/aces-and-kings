package pl.edu.agh.to2.acesandkings.vis.view.gamescreen;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.vis.controller.GameControllable;
import pl.edu.agh.to2.acesandkings.vis.controller.GameController;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.cards.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paweł Grochola on 03.12.2017.
 */
public class BoardView implements GameControllable {

    private GameController gameController;
    private final GridPane grid = new GridPane();
    private static final Map<StackPosition, GridPosition> STACK_POSITIONS = new HashMap<>();
    private Map<StackPosition, CardStackObservable> cardStacks;

    private StackPosition sourceStack = null;

    private StackPosition destStack = null;
    private Card activeCard = null;

    private static class GridPosition {
        public final int col;
        public final int row;

        public GridPosition(final int col, final int row) {
            this.col = col;
            this.row = row;
        }
    }

    static {
        STACK_POSITIONS.put(StackPosition.SPADES_ACE, new GridPosition(0, 0));
        STACK_POSITIONS.put(StackPosition.CLUBS_ACE, new GridPosition(0, 1));
        STACK_POSITIONS.put(StackPosition.HEART_ACE, new GridPosition(0, 2));
        STACK_POSITIONS.put(StackPosition.DIAMONDS_ACE, new GridPosition(0, 3));

        STACK_POSITIONS.put(StackPosition.ACE, new GridPosition(1, 0));
        STACK_POSITIONS.put(StackPosition.TWO, new GridPosition(2, 0));
        STACK_POSITIONS.put(StackPosition.THREE, new GridPosition(3, 0));

        STACK_POSITIONS.put(StackPosition.FOUR, new GridPosition(1, 1));
        STACK_POSITIONS.put(StackPosition.FIVE, new GridPosition(2, 1));
        STACK_POSITIONS.put(StackPosition.SIX, new GridPosition(3, 1));

        STACK_POSITIONS.put(StackPosition.SEVEN, new GridPosition(1, 2));
        STACK_POSITIONS.put(StackPosition.EIGHT, new GridPosition(2, 2));
        STACK_POSITIONS.put(StackPosition.NINE, new GridPosition(3, 2));

        STACK_POSITIONS.put(StackPosition.TEN, new GridPosition(1, 3));
        STACK_POSITIONS.put(StackPosition.JACK, new GridPosition(2, 3));
        STACK_POSITIONS.put(StackPosition.QUEEN, new GridPosition(3, 3));

        STACK_POSITIONS.put(StackPosition.SPADES_KING, new GridPosition(4, 0));
        STACK_POSITIONS.put(StackPosition.CLUBS_KING, new GridPosition(4, 1));
        STACK_POSITIONS.put(StackPosition.HEART_KING, new GridPosition(4, 2));
        STACK_POSITIONS.put(StackPosition.DIAMONDS_KING, new GridPosition(4, 3));

        STACK_POSITIONS.put(StackPosition.EXTRA_STACK, new GridPosition(5, 2));
        STACK_POSITIONS.put(StackPosition.HAND_STACK, new GridPosition(0, 4));
    }

    public BoardView(Map<StackPosition, CardStackObservable> cardStacks) {
        this.cardStacks = cardStacks;
    }

    public void draw() {
        drawBorderCardStacks();
        drawMiddleCardStacks();
        drawHandCardStack();
        drawExtraCardStack();
    }

    public Node getNode() {
        return grid;
    }

    private void addStack(final CardStackView cardStackView) {
        final StackPosition stackPosition = cardStackView.getStackPosition();
        final GridPosition gridPosition = STACK_POSITIONS.get(stackPosition);
        final Node stackNode = cardStackView.getGroup();
        grid.add(cardStackView.getGroup(), gridPosition.col, gridPosition.row);
        GridPane.setMargin(stackNode, new Insets(10,50,10,50));
        GridPane.setHalignment(stackNode, HPos.LEFT);
        GridPane.setValignment(stackNode, VPos.TOP);
        if(stackPosition == StackPosition.HAND_STACK) {
            GridPane.setColumnSpan(stackNode, 6);
        }
        if(stackPosition == StackPosition.EXTRA_STACK) {
            GridPane.setRowSpan(stackNode, 2);
        }
    }

    private void drawBorderCardStacks() {
        final List<StackPosition> borderStackPositions = Arrays.asList(
                StackPosition.SPADES_ACE,StackPosition.CLUBS_ACE,StackPosition.HEART_ACE,StackPosition.DIAMONDS_ACE,
                StackPosition.SPADES_KING, StackPosition.CLUBS_KING, StackPosition.HEART_KING, StackPosition.DIAMONDS_KING);
        for (final StackPosition stackPosition : borderStackPositions) {
            final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
            final BorderCardStackView borderCardStackView = new BorderCardStackView(cardList, stackPosition, this);
            borderCardStackView.draw();
            addStack(borderCardStackView);
        }
    }

    public void drawBorderCardStack(StackPosition stackPosition) {
        final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
        final BorderCardStackView borderCardStackView = new BorderCardStackView(cardList, stackPosition, this);
        borderCardStackView.draw();
        addStack(borderCardStackView);
    }

    private void drawMiddleCardStacks() {
        final List<StackPosition> middleStackPositions = Arrays.asList(StackPosition.ACE, StackPosition.TWO, StackPosition.THREE, StackPosition.FOUR, StackPosition.FIVE,
                StackPosition.SIX, StackPosition.SEVEN, StackPosition.EIGHT, StackPosition.NINE, StackPosition.TEN, StackPosition.JACK, StackPosition.QUEEN);
        for (final StackPosition stackPosition : middleStackPositions) {
            final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
            final MiddleCardStackView middleCardStackView = new MiddleCardStackView(cardList, stackPosition, this);
            middleCardStackView.draw();
            addStack(middleCardStackView);
        }
    }

    public void drawMiddleCardStack(StackPosition stackPosition) {
        final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
        final MiddleCardStackView middleCardStackView = new MiddleCardStackView(cardList, stackPosition, this);
        middleCardStackView.draw();
        addStack(middleCardStackView);
    }

    public void drawHandCardStack() {
        final StackPosition stackPosition = StackPosition.HAND_STACK;
        final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
        final HandCardStackView handCardStackView = new HandCardStackView(cardList, stackPosition, this);
        handCardStackView.draw();
        addStack(handCardStackView);
    }

    public void drawExtraCardStack() {
        final StackPosition stackPosition = StackPosition.EXTRA_STACK;
        final ObservableList<Card> cardList = cardStacks.get(stackPosition).getUnmodifiableObservableStack();
        final ExtraCardStackView extraCardStackView = new ExtraCardStackView(cardList, stackPosition, this);
        extraCardStackView.draw();
        addStack(extraCardStackView);
    }

    @Override
    public void connectController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setSourceStack(StackPosition stackPosition){
        this.sourceStack = stackPosition;
    }

    public void setDestStack(StackPosition stackPosition){
        if(sourceStack!=null && sourceStack==StackPosition.HAND_STACK){
            gameController.handleMoveCardFromHSAction(activeCard, stackPosition);
        }else if(sourceStack!=null && sourceStack.isBorderPosition()){
            this.destStack = stackPosition;
            gameController.handleMoveFromBorderStack(sourceStack, destStack);
        }
        if(this.sourceStack!=null && this.sourceStack == stackPosition && stackPosition!=StackPosition.EXTRA_STACK) {
            this.destStack = stackPosition;
            //this.gameController.handleMoveCardAction(this.sourceStack, this.destStack);
            this.gameController.handleActivateCardsStackAction(this.sourceStack);
        }else if(this.sourceStack!=null){
            this.destStack = stackPosition;
            this.gameController.handleMoveCardAction(this.sourceStack, this.destStack);
        }
        this.sourceStack=null;
        this.destStack=null;
        this.activeCard=null;
    }

    public void handleMoveCardFromHS(Card card){
        this.sourceStack = StackPosition.HAND_STACK;
        this.activeCard = card;
    }
    //implementation is to change during implementation
   // private final Map<StackPosition, CardStackView> cardStacks = new HashMap<>();
}


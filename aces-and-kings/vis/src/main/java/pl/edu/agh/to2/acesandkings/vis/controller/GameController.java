package pl.edu.agh.to2.acesandkings.vis.controller;

import pl.edu.agh.to2.acesandkings.common.model.Card;
import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.*;

import javax.inject.Inject;

/**
 * Created by Julia on 2017-12-04.
 */
public class GameController {

    private AppController appController;

    //model
    private ActiveCardsManipulator activeCardManipulator;
    private CardsMovePossibilityGuard cardsMovePossibilityGuard;
    private CardsInHandManipulator cardsInHandManipulator;
    private CardStackManager cardStackManager;
    private GameActionManager gameActionManager;



    public void setAppController(AppController appController) {
        this.appController = appController;
    }

    public AppController getAppController() {
        return appController;
    }

    @Inject
    public void setActiveCardManipulator(ActiveCardsManipulator activeCardManipulator){
        System.out.println("Ala ma kota");
        this.activeCardManipulator = activeCardManipulator;
    }

    @Inject
    public void setCardsMovePossibilityGuard(CardsMovePossibilityGuard cardsMovePossibilityGuard){
        this.cardsMovePossibilityGuard = cardsMovePossibilityGuard;
    }

    @Inject
    public void setCardsInHandManipulator(CardsInHandManipulator cardsInHandManipulator){
        this.cardsInHandManipulator = cardsInHandManipulator;
    }

    @Inject
    public void setCardStackManager(CardStackManager cardStackManager){
        this.cardStackManager = cardStackManager;
    }

    @Inject
    public void setGameActionManager(GameActionManager gameActionManager){
        this.gameActionManager = gameActionManager;
    }

    public void handleUndoAction(){
        gameActionManager.undo();
    }

    public void handleRedoAction(){
        gameActionManager.redo();
    }

    public void handleMenuAction(){
        this.appController.showMenuViewDialog();
    }

    public void handleDeactivateCardStackAction(){
        cardStackManager.deactivateCardStack();
    }

    public void handleActivateCardsStackAction(StackPosition stackPosition){
        if(cardsMovePossibilityGuard.isActivateCardStackAllowed(stackPosition)){
            cardStackManager.activateCardStack(stackPosition, cardStackManager.getCardFromExtraStack().get());
        }
    }

    public void handleDisactivateCardStackAction(){
        cardStackManager.deactivateCardStack();
    }

    public void handleMoveCardAction(StackPosition sourceSp, StackPosition destSp){

       if(cardsMovePossibilityGuard.isMoveActiveCardToStackAllowed(sourceSp, destSp)){
        activeCardManipulator.moveActiveCardToStack(sourceSp, destSp);
        }
    }

    public void handleMoveFromBorderStack(StackPosition sourceSp, StackPosition destSp){
        if(cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(sourceSp, destSp)){
            activeCardManipulator.moveCardFromOneBorderStackToOther(sourceSp, destSp);
        }
    }

    public void handleMoveCardFromHSAction(Card card, StackPosition destPos){
        if(cardsMovePossibilityGuard.isMoveCardFromHandToStackAllowed(card, destPos)) {

            cardsInHandManipulator.moveCardFromHandToStack(card, destPos);
        }
    }
}

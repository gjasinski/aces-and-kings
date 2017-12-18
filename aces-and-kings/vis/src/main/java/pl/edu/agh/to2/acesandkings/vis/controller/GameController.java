package pl.edu.agh.to2.acesandkings.vis.controller;

import pl.edu.agh.to2.acesandkings.common.model.StackPosition;
import pl.edu.agh.to2.acesandkings.game.api.*;

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

    public void setActiveCardManipulator(ActiveCardsManipulator activeCardManipulator){
        this.activeCardManipulator = activeCardManipulator;
    }

    public void setCardsMovePossibilityGuard(CardsMovePossibilityGuard cardsMovePossibilityGuard){
        this.cardsMovePossibilityGuard = cardsMovePossibilityGuard;
    }

    public void setCardsInHandManipulator(CardsInHandManipulator cardsInHandManipulator){
        this.cardsInHandManipulator = cardsInHandManipulator;
    }

    public void setCardStackManager(CardStackManager cardStackManager){
        this.cardStackManager = cardStackManager;
    }

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

    //podniesienie karty otwierającej stos
    //odbywa się tylko w widoku(?) logicznie stosy się nie zmieniają
//    public void handlePickUpKeyCardAction(){
//        if(cardsMovePossibilityGuard.isCardStackActive(StackPosition.EXTRA_STACK)){
//            activeCardManipulator.moveActiveCardToStack(StackPosition.EXTRA_STACK, StackPosition.HAND);
//        }
//    }

//    public void handleActivateCardsStackAction(StackPosition stackPosition){
//        if(cardsMovePossibilityGuard. isCardStackActive(StackPosition.EXTRA_STACK)
//        && cardsMovePossibilityGuard.isActivateCardStackAllowed(stackPosition)){
//            cardStackManager.activateCardStack(stackPosition);
//        }
//    }

    //kiedy stwierdzimy, że już nie mamy ruch - dezaktywujemy aktywny stos i możemy pobrać nową kartę z extra stosu
    public void handleDisactivateCardStackAction(){
        cardStackManager.disactivateCardStack();
    }

    public void handleMoveCardAction(StackPosition sourceSp, StackPosition destSp){
        System.out.println("Move action!");
        if(cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(sourceSp, destSp)){
            System.out.println("It's possible!");
            activeCardManipulator.moveCardFromOneBorderStackToOther(sourceSp, destSp);
        }
    }



}

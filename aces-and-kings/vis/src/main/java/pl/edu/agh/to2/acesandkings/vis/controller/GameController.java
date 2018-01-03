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

    //podniesienie karty otwierającej stos
    //odbywa się tylko w widoku(?) logicznie stosy się nie zmieniają
//    public void handlePickUpKeyCardAction(){
//        if(cardsMovePossibilityGuard.isCardStackActive(StackPosition.EXTRA_STACK)){
//            activeCardManipulator.moveActiveCardToStack(StackPosition.EXTRA_STACK, StackPosition.HAND);
//        }
//    }

    public void handleActivateCardsStackAction(StackPosition stackPosition){
        System.out.println("Attempt to activate card stack");
        if(cardsMovePossibilityGuard.isActivateCardStackAllowed(stackPosition) && cardStackManager.getCardFromExtraStack().isPresent()){
            this.cardStackManager.activateCardStack(stackPosition,cardStackManager.getCardFromExtraStack().get());
            System.out.println("Card stack activated");
        }
    }

    //kiedy stwierdzimy, że już nie mamy ruch - dezaktywujemy aktywny stos i możemy pobrać nową kartę z extra stosu
    public void handleDisactivateCardStackAction(){
        cardStackManager.deactivateCardStack();
    }

    public void handleMoveCardAction(StackPosition sourceSp, StackPosition destSp){
        System.out.println("Move action!");
        if(cardsMovePossibilityGuard.isMoveCardFromOneBorderStackToOtherAllowed(sourceSp, destSp)){
            System.out.println("It's possible!");
            activeCardManipulator.moveCardFromOneBorderStackToOther(sourceSp, destSp);
        }
        else{
            System.out.println("You can't!");
        }
    }



}

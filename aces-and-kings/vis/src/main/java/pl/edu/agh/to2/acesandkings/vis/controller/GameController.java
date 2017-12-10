package pl.edu.agh.to2.acesandkings.vis.controller;

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

    public void handleUserAction(){}


}

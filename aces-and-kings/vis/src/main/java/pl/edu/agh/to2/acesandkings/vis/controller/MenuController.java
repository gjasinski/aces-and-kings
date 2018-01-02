package pl.edu.agh.to2.acesandkings.vis.controller;

import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;

import java.io.IOException;

/**
 * Created by Julia on 2017-12-04.
 */
public class MenuController {

    private AppController appController;

    public void setAppController(AppController appController) {
        this.appController = appController;
    }
    public void handleStartAction(){
        this.appController.initializeNewGame();
    }
    public void handleContinueAction(){
        this.appController.initializeSavedGame();
    }
    public void handleExitAction(){
            appController.getPrimaryStage().close();
    }
}

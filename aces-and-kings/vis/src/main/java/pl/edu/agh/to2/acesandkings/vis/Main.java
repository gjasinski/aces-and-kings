package pl.edu.agh.to2.acesandkings.vis;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;
import pl.edu.agh.to2.acesandkings.game.model.GameManagerImpl;
import pl.edu.agh.to2.acesandkings.vis.controller.AppController;

/**
 * Created by Julia on 2017-12-10.
 */
public class Main extends Application {

    private Stage primaryStage;

    private AppController appController;

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Aces & Kings");
        GameManager gameManager = new GameManagerImpl();

        this.appController = new AppController(primaryStage);
        this.appController.setGameManager(gameManager);
        this.appController.showMenuViewDialog();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
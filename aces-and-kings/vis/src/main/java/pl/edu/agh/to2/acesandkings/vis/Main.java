package pl.edu.agh.to2.acesandkings.vis;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.game.GameModule;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;
import pl.edu.agh.to2.acesandkings.game.model.CardStackRepositoryImpl;
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
        Injector injector = Guice.createInjector(new GameModule());
        GameManagerImpl gameManager = injector.getInstance(GameManagerImpl.class);

        this.appController = new AppController(primaryStage, injector);
        this.appController.setGameManager(gameManager);
        this.appController.showMenuViewDialog();

    }

    public static void main(String[] args) {
        launch(args);
    }


}
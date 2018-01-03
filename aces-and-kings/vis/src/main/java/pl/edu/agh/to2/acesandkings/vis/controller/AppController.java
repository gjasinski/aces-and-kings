package pl.edu.agh.to2.acesandkings.vis.controller;


import com.google.inject.Injector;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.edu.agh.to2.acesandkings.common.model.CardStackObservable;
import pl.edu.agh.to2.acesandkings.game.api.GameManager;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.GameScreenFactory;
import pl.edu.agh.to2.acesandkings.vis.view.gamescreen.GameScreenView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Julia on 2017-12-04.
 */
public class AppController {

    private Stage primaryStage;
    private GameManager gameManager;
    private Injector injector;

    public AppController(Stage primaryStage, Injector injector){
        this.primaryStage = primaryStage;
        this.injector = injector;
    }

    public Stage getPrimaryStage(){
        return this.primaryStage;
    }

    public void setGameManager(GameManager gameManager){ this.gameManager = gameManager; }

    public void showMenuViewDialog(){
        final ClassLoader classLoader = getClass().getClassLoader();
        String path = classLoader.getResource("Others/aceskings.png").getFile();
        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File file = new File(path);
        BufferedImage imageb = null;

        try {
            imageb = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image image = SwingFXUtils.toFXImage(imageb, null);
        ImageView header = new ImageView();
        header.setImage(image);
        MenuController menuController = new MenuController();
        menuController.setAppController(this);
        GridPane menuLayout = new GridPane();
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setHgap(10);
        menuLayout.setVgap(10);
        menuLayout.setPadding(new Insets(25, 25, 25, 25));
        VBox vBox = new VBox(10);


        Button startButton = new Button();
        startButton.setText("Start new game");
        startButton.setPrefSize(150, 45);
        startButton.setOnAction(e -> {
            menuController.handleStartAction();
        });
        Button continueButton = new Button();
        continueButton.setText("Load saved game");
        continueButton.setPrefSize(150, 45);
        continueButton.setOnAction(e -> {
            menuController.handleContinueAction();
        });
        Button exitButton = new Button();
        exitButton.setText("Exit game");
        exitButton.setPrefSize(150, 45);
        exitButton.setOnAction(e -> {
            menuController.handleExitAction();
        });
        vBox.getChildren().addAll(header, startButton, continueButton, exitButton);
        vBox.setAlignment(Pos.CENTER);
        menuLayout.add(vBox,0,0);
        menuLayout.setStyle("-fx-background-color: #ffffff;");
        Scene menuScene = new Scene(menuLayout, 600, 400);
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    public void showGameViewDialog(List<CardStackObservable> cardStacks){
        GameController gameController = injector.getInstance(GameController.class);
        gameController.setAppController(this);
        GameScreenFactory gameScreenFactory= new GameScreenFactory();
        GameScreenView gameScreenView = gameScreenFactory.createGameScreen(cardStacks, primaryStage);
        gameScreenView.connectController(gameController);
        gameScreenView.show();

    }

    public void initializeNewGame(){
       List<CardStackObservable> cardStacks  =  this.gameManager.initializeNewGame();
       showGameViewDialog(cardStacks);
    }
    public void initializeSavedGame(){
        List<CardStackObservable> cardStacks = this.gameManager.initializeSavedGame();
        showGameViewDialog(cardStacks);
    }
}


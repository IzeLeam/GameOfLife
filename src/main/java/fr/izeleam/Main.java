package fr.izeleam;

import fr.izeleam.gui.controllers.GameOfLifeController;
import fr.izeleam.managers.ControllerManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    GameOfLife gameOfLife = new GameOfLife(50, 50);
    GameOfLifeUI gameOfLifeUI = new GameOfLifeUI(gameOfLife);

  }

  @Override
  public void start(Stage primaryStage) {
    GameOfLife game = new GameOfLife(50, 50);
    ControllerManager.getInstance().show(new GameOfLifeController(game));
  }
}
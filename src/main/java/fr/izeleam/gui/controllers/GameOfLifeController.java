package fr.izeleam.gui.controllers;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.gui.Controller;
import fr.izeleam.gui.Frame;
import fr.izeleam.util.Observer;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class GameOfLifeController extends Controller implements Observer {

  private final GameOfLife game;
  private AnchorPane gamePane;

  public GameOfLifeController(GameOfLife game) {
    this.game = game;
    game.addObserver(this);
  }

  @Override
  protected Frame getFrame() {
    Frame f = new Frame();

    final ScrollPane scrollPane = new ScrollPane();
    scrollPane.setPrefWidth(f.getWidth());
    scrollPane.setPrefHeight(f.getHeight() - 100);

    final AnchorPane gamePane = new AnchorPane();
    gamePane.setId("gamePane");
    gamePane.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
    this.gamePane = gamePane;

    scrollPane.setContent(gamePane);

    f.add(scrollPane, 0, 100);

    update();

    return f;
  }

  @Override
  public void update() {
    for (int x = 0; x < game.getXMax(); x++) {
      for (int y = 0; y < game.getYMax(); y++) {
        final Cell cell = game.getCell(x, y);
        Rectangle c = new Rectangle(10, 10);
        c.setX(x * 10);
        c.setY(y * 10);
        c.setStyle("-fx-fill: " + (cell.isAlive() ? "black" : "white") + ";");

        this.gamePane.getChildren().add(c);
      }
    }
  }
}

package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Observer;

public class GameOfLifeTerminal implements Observer {

  private final GameOfLife game;

  public GameOfLifeTerminal(GameOfLife gameOfLife) {
    this.game = gameOfLife;
    gameOfLife.addObserver(this);
  }

  @Override
  public void update() {
    System.out.println("Generation nb." + game.getGeneration() + " | Living cells: " + game.getLivingCellsCount());
  }
}

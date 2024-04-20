package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Observer;

/**
 * Represents the terminal output of the game of life, implemented as an observer.
 */
public class GameOfLifeTerminal implements Observer {

  /**
   * The game of life.
   */
  private final GameOfLife game;

  /**
   * Constructor.
   *
   * @param gameOfLife The game of life.
   */
  public GameOfLifeTerminal(GameOfLife gameOfLife) {
    this.game = gameOfLife;
    gameOfLife.addObserver(this);
  }

  /**
   * Update the terminal output.
   */
  @Override
  public void update() {
    System.out.println("Generation nb." + game.getGeneration() + " | Living cells: " + game.getLivingCellsCount());
  }
}

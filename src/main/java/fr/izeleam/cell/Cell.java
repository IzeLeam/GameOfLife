package fr.izeleam.cell;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Visitor;
import fr.izeleam.cell.state.CellState;

/**
 * Represents a cell in the game of life.
 */
public class Cell {

  /**
   * The current state of the cell.
   */
  private CellState state;
  /**
   * The x position of the cell in the grid.
   */
  private final int x;
  /**
   * The y position of the cell in the grid.
   */
  private final int y;
  private final GameOfLife game;
  private int livingNeighboursCount = 0;

  /**
   * Constructor.
   *
   * @param state The initial state of the cell.
   * @param x The x position of the cell in the grid.
   * @param y The y position of the cell in the grid.
   */
  public Cell(final GameOfLife game, final CellState state, final int x, final int y) {
    this.game = game;
    this.state = state;
    this.x = x;
    this.y = y;
  }

  /**
   * Change the state of the cell to alive.
   */
  public void live() {
    if (game.getCell(x, y).isAlive()) {
      return;
    }
    state = state.live();
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && i < game.getXMax() && j >= 0 && j < game.getYMax() && !(i == x && j == y)) {
          game.getCell(i, j).addLivingNeighboursCount();
        }
      }
    }
  }

  /**
   * Change the state of the cell to dead.
   */
  public void die() {
    if (!game.getCell(x, y).isAlive()) {
      return;
    }
    state = state.die();
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && i < game.getXMax() && j >= 0 && j < game.getYMax() && !(i == x && j == y)) {
          game.getCell(i, j).removeLivingNeighboursCount();
        }
      }
    }
  }

  /**
   * Check if the cell is alive.
   *
   * @return True if the cell is alive, false otherwise.
   */
  public boolean isAlive() {
    return state.isAlive();
  }

  /**
   * Accept a visitor.
   *
   * @param v The visitor to accept.
   */
  public void accept(final Visitor v) {
    state.accept(v, this);
  }

  public void addLivingNeighboursCount() {
    Math.min(8, livingNeighboursCount++);
  }

  public void removeLivingNeighboursCount() {
    Math.max(0, livingNeighboursCount--);
  }

  /**
   * Get the count of living neighbours of the cell.
   */
  public void initLivingNeighboursCount() {
    int count = 0;
    for (int i = x - 1; i <= x + 1; i++) {
      for (int j = y - 1; j <= y + 1; j++) {
        if (i >= 0 && i < game.getXMax() && j >= 0 && j < game.getYMax() && !(i == x && j == y)) {
          if (game.getCell(i, j).isAlive()) {
            count++;
          }
        }
      }
    }
    this.livingNeighboursCount = count;
  }

  public int getLivingNeighboursCount() {
    return livingNeighboursCount;
  }
}
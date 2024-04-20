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

  /**
   * Constructor.
   *
   * @param state The initial state of the cell.
   * @param x The x position of the cell in the grid.
   * @param y The y position of the cell in the grid.
   */
  public Cell(final CellState state, final int x, final int y) {
    this.state = state;
    this.x = x;
    this.y = y;
  }

  /**
   * Change the state of the cell to alive.
   */
  public void live() {
    state = state.live();
  }

  /**
   * Change the state of the cell to dead.
   */
  public void die() {
    state = state.die();
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

  /**
   * Get the count of living neighbours of the cell.
   *
   * @param game The game of life.
   * @return The count of living neighbours of the cell.
   */
  public int getLivingNeighboursCount(final GameOfLife game) {
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
    return count;
  }
}
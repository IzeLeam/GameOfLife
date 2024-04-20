package fr.izeleam.cell.state;

import fr.izeleam.util.Visitor;
import fr.izeleam.cell.Cell;

/**
 * Represents a living cell.
 */
public class LivingCell implements CellState {

  /**
   * Instance of the living cell.
   */
  private static final LivingCell instance = new LivingCell();

  /**
   * Get the instance of the living cell.
   *
   * @return The instance of the living cell.
   */
  public static LivingCell getInstance() {
    return instance;
  }

  /**
   * Private constructor.
   */
  private LivingCell() {
  }

  /**
   * Change the state of the cell to alive.
   *
   * @return The new state of the cell.
   */
  @Override
  public CellState live() {
    return this;
  }

  /**
   * Change the state of the cell to dead.
   *
   * @return The new state of the cell.
   */
  @Override
  public CellState die() {
    return DeadCell.getInstance();
  }

  /**
   * Check if the cell is alive.
   *
   * @return True, the cell is alive.
   */
  @Override
  public boolean isAlive() {
    return true;
  }

  /**
   * Accept a visitor.
   *
   * @param v The visitor to accept.
   * @param cell The cell to accept the visitor.
   */
  @Override
  public void accept(final Visitor v, final Cell cell) {
    v.visitLivingCell(cell);
  }
}
package fr.izeleam.cell.state;

import fr.izeleam.util.Visitor;
import fr.izeleam.cell.Cell;

/**
 * Represents a dead cell.
 */
public class DeadCell implements CellState {

  /**
   * Instance of the dead cell.
   */
  private final static DeadCell instance = new DeadCell();

  /**
   * Get the instance of the dead cell.
   *
   * @return The instance of the dead cell.
   */
  public static DeadCell getInstance() {
    return instance;
  }

  /**
   * Private constructor.
   */
  private DeadCell() {
  }

  /**
   * Change the state of the cell to alive.
   *
   * @return The new state of the cell.
   */
  @Override
  public CellState live() {
    return LivingCell.getInstance();
  }

  /**
   * Change the state of the cell to dead.
   *
   * @return The new state of the cell.
   */
  @Override
  public CellState die() {
    return this;
  }

  /**
   * Check if the cell is alive.
   *
   * @return False, the cell is dead.
   */
  @Override
  public boolean isAlive() {
    return false;
  }

  /**
   * Accept a visitor.
   *
   * @param v The visitor to accept.
   * @param cell The cell to accept the visitor.
   */
  @Override
  public void accept(final Visitor v, final Cell cell) {
    v.visitDeadCell(cell);
  }
}

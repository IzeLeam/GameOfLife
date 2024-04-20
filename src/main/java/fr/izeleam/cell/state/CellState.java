package fr.izeleam.cell.state;

import fr.izeleam.util.Visitor;
import fr.izeleam.cell.Cell;

/**
 * Interface to represent the state of a cell, alive or dead, implementing the state pattern.
 */
public interface CellState {

  /**
   * Change the state of the cell to alive.
   *
   * @return The new state of the cell.
   */
  CellState live();

  /**
   * Change the state of the cell to dead.
   *
   * @return The new state of the cell.
   */
  CellState die();

  /**
   * Check if the cell is alive.
   *
   * @return True if the cell is alive, false otherwise.
   */
  boolean isAlive();

  /**
   * Accept a visitor.
   *
   * @param v The visitor to accept.
   * @param cell The cell to accept the visitor.
   */
  void accept(final Visitor v, final Cell cell);
}

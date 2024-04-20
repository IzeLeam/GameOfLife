package fr.izeleam.util;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;

/**
 * Represents a visitor object, visitor pattern.
 */
public abstract class Visitor {

  /**
   * The game of life.
   */
  protected final GameOfLife gameOfLife;

  /**
   * Constructor.
   *
   * @param gameOfLife The game of life.
   */
  public Visitor(final GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
  }

  /**
   * Visit a living cell.
   *
   * @param cell The cell to visit.
   */
  public abstract void visitLivingCell(final Cell cell);

  /**
   * Visit a dead cell.
   *
   * @param cell The cell to visit.
   */
  public abstract void visitDeadCell(final Cell cell);
}
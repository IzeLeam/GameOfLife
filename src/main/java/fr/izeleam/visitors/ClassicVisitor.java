package fr.izeleam.visitors;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.DieCommand;
import fr.izeleam.cell.command.LiveCommand;
import fr.izeleam.util.Visitor;

/**
 * Represents the classic visitor, also called the Conway's visitor.
 */
public class ClassicVisitor extends Visitor {

  /**
   * Constructor.
   *
   * @param gameOfLife The game of life.
   */
  public ClassicVisitor(final GameOfLife gameOfLife) {
    super(gameOfLife);
  }

  /**
   * Visit a living cell.
   * If a living cell has less than 2 or more than 3 living neighbours, it dies.
   *
   * @param cell The cell to visit.
   */
  @Override
  public void visitLivingCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours < 2 || livingNeighbours > 3) {
      this.gameOfLife.addCommand(new DieCommand(cell));
    }
  }

  /**
   * Visit a dead cell.
   * If a dead cell has exactly 3 living neighbours, it becomes alive.
   *
   * @param cell The cell to visit.
   */
  @Override
  public void visitDeadCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours == 3) {
      this.gameOfLife.addCommand(new LiveCommand(cell));
    }
  }
}
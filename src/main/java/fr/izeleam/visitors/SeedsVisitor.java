package fr.izeleam.visitors;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.DieCommand;
import fr.izeleam.cell.command.LiveCommand;
import fr.izeleam.util.Visitor;

public class SeedsVisitor extends Visitor {

  public SeedsVisitor(final GameOfLife gameOfLife) {
    super(gameOfLife);
  }

  @Override
  public void visitLivingCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours != 2 && livingNeighbours != 3) {
      this.gameOfLife.addCommand(new DieCommand(cell));
    }
  }

  @Override
  public void visitDeadCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours == 2) {
      this.gameOfLife.addCommand(new LiveCommand(cell));
    }
  }
}
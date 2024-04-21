package fr.izeleam.visitors;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.DieCommand;
import fr.izeleam.cell.command.LiveCommand;
import fr.izeleam.util.Visitor;

public class ReplicatorVisitor extends Visitor {

  public ReplicatorVisitor(final GameOfLife gameOfLife) {
    super(gameOfLife);
  }

  @Override
  public void visitLivingCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours != 1 && livingNeighbours != 3 && livingNeighbours != 5) {
      this.gameOfLife.addCommand(new DieCommand(cell));
    }
  }

  @Override
  public void visitDeadCell(Cell cell) {
    int livingNeighbours = cell.getLivingNeighboursCount();
    if (livingNeighbours == 1 || livingNeighbours == 3 || livingNeighbours == 5) {
      this.gameOfLife.addCommand(new LiveCommand(cell));
    }
  }
}
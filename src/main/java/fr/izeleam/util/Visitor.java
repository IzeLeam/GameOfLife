package fr.izeleam.util;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;

public abstract class Visitor {

  protected final GameOfLife gameOfLife;

  public Visitor(final GameOfLife gameOfLife) {
    this.gameOfLife = gameOfLife;
  }

  public abstract void visitLivingCell(final Cell cell);

  public abstract void visitDeadCell(final Cell cell);
}
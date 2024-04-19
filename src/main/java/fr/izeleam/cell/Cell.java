package fr.izeleam.cell;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Visitor;
import fr.izeleam.cell.state.CellState;

public class Cell {

  private CellState state;
  private final int x;
  private final int y;

  public Cell(final CellState state, final int x, final int y) {
    this.state = state;
    this.x = x;
    this.y = y;
  }

  public void live() {
    state = state.live();
  }

  public void die() {
    state = state.die();
  }

  public void invert() {
    state = state.invert();
  }

  public boolean isAlive() {
    return state.isAlive();
  }

  public void accept(final Visitor v) {
    state.accept(v, this);
  }

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
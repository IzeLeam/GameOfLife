package fr.izeleam.cell.state;

import fr.izeleam.util.Visitor;
import fr.izeleam.cell.Cell;

public interface CellState {

  CellState live();
  CellState die();
  boolean isAlive();
  void accept(final Visitor v, final Cell cell);
  default CellState invert() {
    return this.isAlive() ? this.die() : this.live();
  }
}

package fr.izeleam.cell.state;

public class DeadCell implements CellState {

  private final static DeadCell instance = new DeadCell();

  public static DeadCell getInstance() {
    return instance;
  }

  private DeadCell() {
  }

  @Override
  public CellState live() {
    return LivingCell.getInstance();
  }

  @Override
  public CellState die() {
    return this;
  }

  @Override
  public boolean isAlive() {
    return false;
  }
}

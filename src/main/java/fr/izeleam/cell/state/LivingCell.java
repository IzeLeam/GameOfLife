package fr.izeleam.cell.state;

public class LivingCell implements CellState {

  private static final LivingCell instance = new LivingCell();

  public static LivingCell getInstance() {
    return instance;
  }

  private LivingCell() {
  }

  @Override
  public CellState live() {
    return this;
  }

  @Override
  public CellState die() {
    return DeadCell.getInstance();
  }

  @Override
  public boolean isAlive() {
    return true;
  }
}
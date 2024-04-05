package fr.izeleam;

import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.Command;
import fr.izeleam.cell.state.DeadCell;
import fr.izeleam.cell.state.LivingCell;
import fr.izeleam.util.Observable;
import fr.izeleam.util.Observer;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife implements Observable {

  private final Cell[][] grid;
  private final int xMax;
  private final int yMax;
  private final List<Observer> observers = new ArrayList<>();
  private final List<Command> commands = new ArrayList<>();

  public GameOfLife(final int xMax, final int yMax) {
    this.xMax = xMax;
    this.yMax = yMax;
    grid = new Cell[xMax][yMax];
    initGrid();
  }

  private void initGrid() {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        final double rand = Math.random();
        if (rand < 0.5) {
          grid[x][y] = new Cell(LivingCell.getInstance(), x, y);
        } else {
          grid[x][y] = new Cell(DeadCell.getInstance(), x, y);
        }
      }
    }
  }

  public Cell getCell(final int x, final int y) {
    return grid[x][y];
  }

  public int getXMax() {
    return xMax;
  }

  public int getYMax() {
    return yMax;
  }

  @Override
  public void addObserver(final Observer observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(final Observer observer) {
    this.observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    observers.forEach(Observer::update);
  }

  public void addCommand(final Command command) {
    this.commands.add(command);
  }

  public void executeCommands() {
    commands.forEach(Command::execute);
  }
}
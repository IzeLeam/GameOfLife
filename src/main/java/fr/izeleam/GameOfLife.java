package fr.izeleam;

import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.Command;
import fr.izeleam.cell.state.DeadCell;
import fr.izeleam.cell.state.LivingCell;
import fr.izeleam.util.Observable;
import fr.izeleam.util.Observer;
import fr.izeleam.visitors.ClassicVisitor;
import fr.izeleam.util.Visitor;
import java.util.ArrayList;
import java.util.List;

public class GameOfLife implements Observable {

  private Cell[][] grid;
  private final int xMax;
  private final int yMax;
  private int generation = 0;
  private Visitor visitor;
  private final List<Observer> observers = new ArrayList<>();
  private final List<Command> commands = new ArrayList<>();
  private final List<Cell[][]> history = new ArrayList<>();
  private int speed = 1000;
  private Thread thread;

  public GameOfLife(final int xMax, final int yMax) {
    this.xMax = xMax;
    this.yMax = yMax;
    grid = new Cell[xMax][yMax];
    this.visitor = new ClassicVisitor(this);
    initGrid(0.5);
  }

  public void initGrid(final double density) {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        final double rand = Math.random();
        if (rand < density) {
          grid[x][y] = new Cell(LivingCell.getInstance(), x, y);
        } else {
          grid[x][y] = new Cell(DeadCell.getInstance(), x, y);
        }
      }
    }
  }

  public void reset() {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        grid[x][y] = new Cell(DeadCell.getInstance(), x, y);
      }
    }
    generation = 0;
    notifyObservers();
  }

  public Cell getCell(final int x, final int y) {
    if (x < 0 || x >= xMax || y < 0 || y >= yMax) {
      return null;
    }
    return grid[x][y];
  }

  public int getXMax() {
    return xMax;
  }

  public int getYMax() {
    return yMax;
  }

  public int getGeneration() {
    return generation;
  }

  public int getLivingCellsCount() {
    return (int) java.util.Arrays.stream(grid)
        .flatMap(java.util.Arrays::stream)
        .filter(Cell::isAlive)
        .count();
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
    List<Command> commands = new ArrayList<>(this.commands);
    commands.forEach(Command::execute);
    this.commands.clear();
  }

  public void distributeVisitor() {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        grid[x][y].accept(visitor);
      }
    }
  }

  public void setVisitor(final Visitor visitor) {
    this.visitor = visitor;
  }

  public void setSpeed(final int speed) {
    this.speed = speed;
  }

  public void nextGeneration() {
    history.add(grid.clone());
    distributeVisitor();
    executeCommands();
    notifyObservers();
    generation++;
  }

  public void previousGeneration() {
    if (history.size() > 1) {
      history.remove(history.size() - 1);
      grid = history.get(history.size() - 1);
      generation--;
      notifyObservers();
    }
  }

  private void loop() {
    while (isRunning()) {
      nextGeneration();
      try {
        Thread.sleep(speed);
      } catch (InterruptedException ignored) {
      }
    }
  }

  public void start() {
    if (this.thread == null) {
      this.thread = new Thread(this::loop);
      this.thread.start();
    }
  }

  public void stop() {
    if (this.thread != null) {
      this.thread.interrupt();
      this.thread = null;
    }
  }

  public boolean isRunning() {
    return this.thread != null;
  }
}
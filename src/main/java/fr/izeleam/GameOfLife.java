package fr.izeleam;

import fr.izeleam.cell.Cell;
import fr.izeleam.cell.command.Command;
import fr.izeleam.cell.state.DeadCell;
import fr.izeleam.cell.state.LivingCell;
import fr.izeleam.util.Observable;
import fr.izeleam.util.Observer;
import fr.izeleam.util.Visitor;
import fr.izeleam.visitors.ClassicVisitor;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the game of life.
 */
public class GameOfLife implements Observable {

  /**
   * The grid of cells.
   */
  private final Cell[][] grid;
  /**
   * The width of the grid.
   */
  private final int xMax;
  /**
   * The height of the grid.
   */
  private final int yMax;
  /**
   * The current generation.
   */
  private int generation = 0;
  /**
   * The current visitor.
   */
  private Visitor visitor;
  /**
   * The list of observers.
   */
  private final List<Observer> observers = new ArrayList<>();
  /**
   * The list of commands.
   */
  private final List<Command> commands;
  /**
   * The speed between each generation, in ms.
   */
  private int speed = 500;
  /**
   * The current thread of the game.
   */
  private Thread thread;

  /**
   * Constructor.
   *
   * @param xMax The width of the grid.
   * @param yMax The height of the grid.
   */
  public GameOfLife(final int xMax, final int yMax) {
    this.xMax = xMax;
    this.yMax = yMax;
    grid = new Cell[xMax][yMax];
    this.commands = new ArrayList<>();
    this.visitor = new ClassicVisitor(this);
    initGrid(0.5);
  }

  /**
   * Initialize the grid with a given density of living cells.
   * Also used to reset the grid
   *
   * @param density The density of living cells.
   */
  public void initGrid(final double density) {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        final double rand = Math.random();
        if (rand < density) {
          grid[x][y] = new Cell(this, LivingCell.getInstance(), x, y);
        } else {
          grid[x][y] = new Cell(this, DeadCell.getInstance(), x, y);
        }
      }
    }
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        grid[x][y].initLivingNeighboursCount();
      }
    }
  }

  /**
   * Get a cell at a given position.
   *
   * @param x The x position of the cell.
   * @param y The y position of the cell.
   * @return The cell at the given position, or null if the position is out of bounds.
   */
  public Cell getCell(final int x, final int y) {
    if (x < 0 || x >= xMax || y < 0 || y >= yMax) {
      return null;
    }
    return grid[x][y];
  }

  /**
   * Get the width of the grid.
   *
   * @return The width of the grid.
   */
  public int getXMax() {
    return xMax;
  }

  /**
   * Get the height of the grid.
   *
   * @return The height of the grid.
   */
  public int getYMax() {
    return yMax;
  }

  /**
   * Get the current generation.
   *
   * @return The current generation.
   */
  public int getGeneration() {
    return generation;
  }

  /**
   * Get the count of living cells.
   *
   * @return The count of living cells.
   */
  public int getLivingCellsCount() {
    return (int) java.util.Arrays.stream(grid)
        .flatMap(java.util.Arrays::stream)
        .filter(Cell::isAlive)
        .count();
  }

  /**
   * Add an observer to the game.
   */
  @Override
  public void addObserver(final Observer observer) {
    this.observers.add(observer);
  }

  /**
   * Remove an observer from the game.
   *
   * @param observer The observer to remove.
   */
  @Override
  public void removeObserver(final Observer observer) {
    this.observers.remove(observer);
  }

  /**
   * Notify the observers of the game to update.
   */
  @Override
  public void notifyObservers() {
    observers.forEach(Observer::update);
  }

  /**
   * Add a command for the next generation.
   *
   * @param command The command to add.
   */
  public void addCommand(final Command command) {
    this.commands.add(command);
  }

  /**
   * Execute all the commands for the next generation.
   */
  public void executeCommands() {
    List<Command> commands = new ArrayList<>(this.commands);
    commands.forEach(Command::execute);
    this.commands.clear();
  }

  /**
   * Distribute the visitor on the grid.
   */
  public void distributeVisitor() {
    for (int x = 0; x < xMax; x++) {
      for (int y = 0; y < yMax; y++) {
        grid[x][y].accept(visitor);
      }
    }
  }

  /**
   * Change the current visitor of the game.
   * Used to change the rules of the game.
   *
   * @param visitor The visitor to set.
   */
  public void setVisitor(final Visitor visitor) {
    this.visitor = visitor;
  }

  /**
   *  Set the speed between each generation.
   *
   * @param speed The speed to set.
   */
  public void setSpeed(final int speed) {
    this.speed = speed;
  }

  /**
   * Get the speed between each generation.
   *
   * @return The speed between each generation.
   */
  public int getSpeed() {
    return speed;
  }

  /**
   * Generate the next generation.
   */
  public void nextGeneration() {
    distributeVisitor();
    executeCommands();
    notifyObservers();
    generation++;
  }

  /**
   * The main loop of the game.
   */
  private void loop() {
    while (isRunning()) {
      nextGeneration();
      try {
        Thread.sleep(speed);
      } catch (InterruptedException ignored) {
      }
    }
  }

  /**
   * Start the game in a new thread.
   */
  public void start() {
    if (this.thread == null) {
      this.thread = new Thread(this::loop);
      this.thread.start();
    }
  }

  /**
   * Stop the game.
   */
  public void stop() {
    if (this.thread != null) {
      this.thread.interrupt();
      this.thread = null;
    }
  }

  /**
   * Check if the game is running.
   *
   * @return True if the game is running, false otherwise.
   */
  public boolean isRunning() {
    return this.thread != null;
  }
}
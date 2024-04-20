package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

/**
 * Command pattern to encapsulate the logic of a cell's state change.
 */
public abstract class Command {

  /**
   * The cell to apply the command to.
   */
  protected final Cell cell;

  /**
   * Constructor.
   *
   * @param cell The cell to apply the command to.
   */
  public Command(final Cell cell) {
    this.cell = cell;
  }

  /**
   * Execute the command.
   */
  public abstract void execute();
}

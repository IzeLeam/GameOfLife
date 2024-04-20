package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

/**
 * Command pattern to encapsulate the logic of a cell's state change to dead.
 */
public class DieCommand extends Command {

  /**
   * Constructor.
   *
   * @param cell The cell to apply the command to.
   */
  public DieCommand(final Cell cell) {
    super(cell);
  }

  /**
   * Execute the command, kill the cell.
   */
  @Override
  public void execute() {
    cell.die();
  }
}

package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

/**
 * Command pattern to encapsulate the logic of a cell's state change to live.
 */
public class LiveCommand extends Command {

  /**
   * Constructor.
   *
   * @param cell The cell to apply the command to.
   */
  public LiveCommand(final Cell cell) {
    super(cell);
  }

  /**
   * Execute the command, make the cell alive.
   */
  @Override
  public void execute() {
    cell.live();
  }
}

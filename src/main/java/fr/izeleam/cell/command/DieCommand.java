package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

public class DieCommand extends Command {

  public DieCommand(final Cell cell) {
    super(cell);
  }

  @Override
  public void execute() {
    cell.die();
  }
}

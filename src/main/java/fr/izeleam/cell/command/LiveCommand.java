package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

public class LiveCommand extends Command {

  public LiveCommand(final Cell cell) {
    super(cell);
  }

  @Override
  public void execute() {
    cell.live();
  }
}

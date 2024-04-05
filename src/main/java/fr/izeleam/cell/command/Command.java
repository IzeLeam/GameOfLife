package fr.izeleam.cell.command;

import fr.izeleam.cell.Cell;

public abstract class Command {

  protected final Cell cell;

  public Command(final Cell cell) {
    this.cell = cell;
  }

  public abstract void execute();
}

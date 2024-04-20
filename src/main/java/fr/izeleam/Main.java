package fr.izeleam;

import fr.izeleam.observers.GameOfLifeUI;

/**
 * Main class to run the game of life.
 */
public class Main {

  /**
   * Main method.
   *
   * @param args The arguments.
   */
  public static void main(String[] args) {
    GameOfLife game = new GameOfLife(500, 500);
    GameOfLifeUI ui = new GameOfLifeUI(game);

    SwingApp app = new SwingApp(ui, game);
  }
}
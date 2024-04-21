package fr.izeleam;

import fr.izeleam.observers.GameOfLifeUI;
import fr.izeleam.patterns.PatternManager;

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
    GameOfLife game = new GameOfLife(1500, 1000);
    GameOfLifeUI ui = new GameOfLifeUI(game);

    PatternManager.getInstance().loadPatterns();
    SwingApp app = new SwingApp(ui, game);
  }
}
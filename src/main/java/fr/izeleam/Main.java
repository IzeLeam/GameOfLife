package fr.izeleam;

import fr.izeleam.observers.GameOfLifeUI;

public class Main {

  public static void main(String[] args) {
    GameOfLife game = new GameOfLife(500, 500);
    GameOfLifeUI ui = new GameOfLifeUI(game);

    SwingApp app = new SwingApp(ui, game);
  }
}
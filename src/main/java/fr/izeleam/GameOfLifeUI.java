package fr.izeleam;

import fr.izeleam.cell.Cell;
import fr.izeleam.util.Observer;
import java.awt.Color;
import java.awt.Graphics;
import javafx.stage.Screen;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameOfLifeUI extends JFrame implements Observer {

  private final GameOfLife game;

  public GameOfLifeUI(final GameOfLife game) {
    super("Game of Life");
    this.game = game;

    this.setSize(game.getXMax() * 10, game.getYMax() * 10);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    game.addObserver(this);
  }

  @Override
  public void update() {
    this.repaint();
  }

  public void paint(Graphics g) {
    for (int x = 0; x < game.getXMax(); x++) {
      for (int y = 0; y < game.getYMax(); y++) {
        final Cell cell = game.getCell(x, y);
        g.setColor(cell.isAlive() ? Color.BLACK : Color.WHITE);
        g.fillRect(x * 10, y * 10, 10, 10);
      }
    }
  }
}

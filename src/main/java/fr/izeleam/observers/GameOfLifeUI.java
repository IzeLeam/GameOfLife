package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.util.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * Represents the graphical user interface of the game of life, implemented as an observer.
 * Used in a Swing application.
 */
public class GameOfLifeUI extends JPanel implements Observer {

  /**
   * The game of life.
   */
  private final GameOfLife game;
  /**
   * The size of a cell in pixels.
   */
  private int caseSize = 10;

  /**
   * Constructor.
   *
   * @param game The game of life.
   */
  public GameOfLifeUI(final GameOfLife game) {
    this.game = game;
    game.addObserver(this);

    this.setPreferredSize(new Dimension(game.getXMax() * caseSize, game.getYMax() * caseSize));
    this.revalidate();
  }

  /**
   * Get the size of a cell.
   *
   * @return The size of a cell in pixels.
   */
  public int getCaseSize() {
    return caseSize;
  }

  /**
   * Update the graphical interface.
   */
  @Override
  public void update() {
    this.repaint();
  }


  /**
   * Paint the graphical interface.
   *
   * @param g The graphics object.
   */
  public void paint(Graphics g) {
    super.paint(g);
    Graphics2D g2d = (Graphics2D) g;

    for (int i = 0; i < game.getXMax(); i++) {
      for (int j = 0; j < game.getYMax(); j++) {
        Cell cell = game.getCell(i, j);
        if (cell.isAlive()) {
          g2d.setColor(Color.BLACK);
        } else {
          g2d.setColor(Color.WHITE);
        }
        g2d.fillRect(i * caseSize, j * caseSize, caseSize, caseSize);
      }
    }
  }

  /**
   * Zoom in the graphical interface.
   */
  public void zoom() {
    if (caseSize >= 50) {
      return;
    }
    caseSize += 1;
    this.setPreferredSize(new Dimension(game.getXMax() * caseSize, game.getYMax() * caseSize));
    this.revalidate();
    this.repaint();
  }

  /**
   * Zoom out the graphical interface.
   */
  public void unzoom() {
    if (caseSize <= 1) {
      return;
    }
    caseSize -= 1;
    this.setPreferredSize(new Dimension(game.getXMax() * caseSize, game.getYMax() * caseSize));
    this.revalidate();
    this.repaint();
  }
}
package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.cell.Cell;
import fr.izeleam.util.Observer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GameOfLifeUI extends JPanel implements Observer {

  private final GameOfLife game;
  private int caseSize = 10;

  public GameOfLifeUI(final GameOfLife game) {
    this.game = game;
    game.addObserver(this);

    this.setPreferredSize(new Dimension(game.getXMax() * caseSize, game.getYMax() * caseSize));
    this.revalidate();
  }

  public int getCaseSize() {
    return caseSize;
  }

  @Override
  public void update() {
    this.repaint();
  }

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

  public void zoom() {
    if (caseSize >= 50) {
      return;
    }
    caseSize += 1;
    this.setPreferredSize(new Dimension(game.getXMax() * caseSize, game.getYMax() * caseSize));
    this.revalidate();
    this.repaint();
  }

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
package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Represents the information panel of the game of life, implemented as an observer.
 * Used in a Swing application.
 */
public class GameOfLifeInfos extends JPanel implements Observer {

  /**
   * The game of life.
   */
  private final GameOfLife game;
  /**
   * The label displaying the generation number.
   */
  private final JLabel generationField;
  /**
   * The label displaying the number of living cells.
   */
  private final JLabel livingCellsField;

  /**
   * Constructor.
   *
   * @param game The game of life.
   */
  public GameOfLifeInfos(final GameOfLife game) {
    this.game = game;
    game.addObserver(this);

    generationField = new JLabel();

    livingCellsField = new JLabel();

    this.add(generationField);
    this.add(livingCellsField);
    this.update();
  }

  /**
   * Update the information panel.
   */
  @Override
  public void update() {
    generationField.setText("Generation: " + game.getGeneration());
    livingCellsField.setText("Living cells: " + game.getLivingCellsCount());
  }
}
package fr.izeleam.observers;

import fr.izeleam.GameOfLife;
import fr.izeleam.util.Observer;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class GameOfLifeInfos extends JPanel implements Observer {

  private final GameOfLife game;
  private final JLabel generationField;
  private final JLabel livingCellsField;

  public GameOfLifeInfos(final GameOfLife game) {
    this.game = game;
    game.addObserver(this);

    generationField = new JLabel();

    livingCellsField = new JLabel();

    this.add(generationField);
    this.add(livingCellsField);
    this.update();
  }

  @Override
  public void update() {
    generationField.setText("Generation: " + game.getGeneration());
    livingCellsField.setText("Living cells: " + game.getLivingCellsCount());
  }
}
package fr.izeleam;

import fr.izeleam.observers.GameOfLifeInfos;
import fr.izeleam.observers.GameOfLifeUI;
import fr.izeleam.visitors.ClassicVisitor;
import fr.izeleam.visitors.DayNightVisitor;
import fr.izeleam.visitors.HighLifeVisitor;
import fr.izeleam.visitors.MazeVisitor;
import fr.izeleam.visitors.MoveVisitor;
import fr.izeleam.visitors.ReplicatorVisitor;
import fr.izeleam.visitors.SeedsVisitor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JViewport;
import javax.swing.border.TitledBorder;

/**
 * Represents the Swing application of the game of life.
 */
public class SwingApp extends JFrame {

  private final GameOfLife game;
  private JButton playButton;
  private Point origin;
  private int editionRadius = 1;
  private boolean inEdition = true;
  private String drawMode = null;
  private int opacity = 0;

  /**
   * Constructor.
   *
   * @param gamePanel The game panel.
   * @param game The game of life.
   */
  public SwingApp(final GameOfLifeUI gamePanel, final GameOfLife game) {
    super("Game of Life");
    this.game = game;

    BorderLayout layout = new BorderLayout();
    this.setLayout(layout);

    this.add(getToolBar(), BorderLayout.NORTH);

    gamePanel.setPreferredSize(gamePanel.getPreferredSize());
    JScrollPane gameScroll = new JScrollPane(gamePanel);
    gameScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    gameScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    gameScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    gameScroll.addMouseWheelListener(e -> {
      if (e.getWheelRotation() < 0) {
        gamePanel.zoom();
      } else {
        gamePanel.unzoom();
      }
    });

    gameScroll.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
          if (inEdition) {
            drawMode = "live";
            drawPoint(e.getX(), e.getY(), gameScroll, gamePanel);
          }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
          if (inEdition) {
            drawMode = "die";
            drawPoint(e.getX(), e.getY(), gameScroll, gamePanel);
          }
        } else if (e.getButton() == MouseEvent.BUTTON2) {
          origin = new Point(e.getPoint());
        }
      }

      @Override
      public void mouseReleased(MouseEvent e) {
        origin = null;
        drawMode = null;
      }
    });

    gameScroll.addMouseMotionListener(new MouseAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        if (origin != null) {
          JViewport viewPort = gameScroll.getViewport();
          int dx = origin.x - e.getX();
          int dy = origin.y - e.getY();

          Rectangle view = viewPort.getViewRect();
          view.x += dx;
          view.y += dy;

          gamePanel.scrollRectToVisible(view);
          origin.setLocation(e.getPoint());
        } else if (drawMode != null) {
          drawPoint(e.getX(), e.getY(), gameScroll, gamePanel);
        }
      }
    });

    this.add(gameScroll, BorderLayout.CENTER);
    this.add(new GameOfLifeInfos(game), BorderLayout.SOUTH);

    final JPanel westPanel = new JPanel();
    westPanel.setLayout(new GridLayout(3, 1));
    westPanel.add(getEditionToolBar());
    westPanel.add(new JPanel());
    westPanel.add(getControlsInformations());
    this.add(westPanel, BorderLayout.WEST);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setVisible(true);
  }

  /**
   * Creation of the top toolbar.
   *
   * @return The toolbar in a JPanel.
   */
  private JPanel getToolBar() {
    final JPanel toolBar = new JPanel();

    this.playButton = new JButton(game.isRunning() ? "Pause" : "Play");
    playButton.setToolTipText("Play/Pause the game");
    playButton.addActionListener(e -> {
      if (game.isRunning()) {
        pauseGame();
      } else {
        playGame();
      }
    });

    JButton nextButton = new JButton("Next");
    nextButton.setToolTipText("Next generation");
    nextButton.addActionListener(e -> game.nextGeneration());

    JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
    speedSlider.setToolTipText("Set the speed between each generation");
    speedSlider.setBorder(new TitledBorder("Speed :" + (speedSlider.getMaximum() - speedSlider.getValue()) + "ms"));
    speedSlider.addChangeListener(e -> {
      game.setSpeed(speedSlider.getMaximum() - speedSlider.getValue());
      speedSlider.setBorder(new TitledBorder("Speed :" + (speedSlider.getMaximum() - speedSlider.getValue()) + "ms"));
    });
    
    toolBar.add(playButton);
    toolBar.add(nextButton);
    toolBar.add(getVisitorsList());
    toolBar.add(speedSlider);

    return toolBar;
  }

  /**
   * Creation of the edition toolbar.
   *
   * @return The toolbar in a JPanel.
   */
  private JPanel getEditionToolBar() {
    final JPanel editionToolBar = new JPanel();
    editionToolBar.setBorder(new TitledBorder("Edition"));

    JSlider radiusSlider = new JSlider(JSlider.HORIZONTAL, 1, 25, 1);
    radiusSlider.setToolTipText("Set the radius of the edition");
    radiusSlider.setBorder(new TitledBorder("Radius :" + radiusSlider.getValue()));
    radiusSlider.addChangeListener(e -> {
      radiusSlider.setBorder(new TitledBorder("Radius :" + radiusSlider.getValue()));
      this.editionRadius = radiusSlider.getValue();
    });

    final JCheckBox editionMode = new JCheckBox("Edition Mode");
    editionMode.setToolTipText("Enable/Disable the edition mode");
    editionMode.setSelected(inEdition);
    editionMode.addActionListener(e -> this.inEdition = editionMode.isSelected());

    final JSlider opacitySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, opacity);
    opacitySlider.setBorder(new TitledBorder("Opacity :" + opacitySlider.getValue()));
    opacitySlider.setToolTipText("Set the opacity of the grid");
    opacitySlider.addChangeListener(e -> {
      opacity = opacitySlider.getValue();
      opacitySlider.setBorder(new TitledBorder("Opacity :" + opacitySlider.getValue()));
    });

    final JButton resetButton = new JButton("Reset");
    resetButton.setToolTipText("Reset the grid with the current opacity");
    resetButton.addActionListener(e -> {
      game.initGrid(opacity / 100.0);
      game.notifyObservers();
    });

    GridBagLayout layout = new GridBagLayout();
    editionToolBar.setLayout(layout);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    editionToolBar.add(editionMode, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    editionToolBar.add(radiusSlider, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    editionToolBar.add(opacitySlider, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    editionToolBar.add(resetButton, gbc);

    return editionToolBar;
  }

  /**
   * Creation of the controls information panel.
   *
   * @return The controls information panel in a JPanel.
   */
  private JPanel getControlsInformations() {
    final JPanel controlsInformations = new JPanel();
    controlsInformations.setBorder(new TitledBorder("Controls"));

    JLabel leftClick = new JLabel("Left click : Live");
    JLabel rightClick = new JLabel("Right click : Die");
    JLabel middleClick = new JLabel("Middle click : Move");
    JLabel mouseWheel = new JLabel("Mouse wheel : Zoom");
    JLabel warningMouseMessage = new JLabel("Use a mouse for a better experience");
    warningMouseMessage.setForeground(Color.RED);
    warningMouseMessage.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

    GridBagLayout layout = new GridBagLayout();
    controlsInformations.setLayout(layout);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    gbc.gridx = 0;
    gbc.gridy = 0;
    controlsInformations.add(leftClick, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    controlsInformations.add(rightClick, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    controlsInformations.add(middleClick, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    controlsInformations.add(mouseWheel, gbc);

    gbc.gridx = 0;
    gbc.gridy = 5;
    controlsInformations.add(warningMouseMessage, gbc);

    return controlsInformations;
  }

  /**
   * Get the list of visitors.
   *
   * @return The list of visitors in a JComboBox.
   */
  private JComboBox<String> getVisitorsList() {
    final JComboBox<String> visitorChoice = new JComboBox<>();
    visitorChoice.setBorder(new TitledBorder("Game mode"));

    visitorChoice.addItem("DayNight");
    visitorChoice.addItem("Conway");
    visitorChoice.addItem("HighLife");
    visitorChoice.addItem("Maze");
    visitorChoice.addItem("Move");
    visitorChoice.addItem("Replicator");
    visitorChoice.addItem("Seeds");

    visitorChoice.setSelectedIndex(1);

    visitorChoice.addActionListener(e -> {
      String visitorName = (String) visitorChoice.getSelectedItem();
      assert visitorName != null;
      game.setVisitor(switch (visitorName) {
        case "DayNight" -> new DayNightVisitor(game);
        case "Conway" -> new ClassicVisitor(game);
        case "HighLife" -> new HighLifeVisitor(game);
        case "Maze" -> new MazeVisitor(game);
        case "Move" -> new MoveVisitor(game);
        case "Replicator" -> new ReplicatorVisitor(game);
        case "Seeds" -> new SeedsVisitor(game);
        default -> throw new IllegalArgumentException("Unknown visitor: " + visitorName);
      });
    });

    return visitorChoice;
  }

  /**
   * Draw a point on the grid based on the mouse coordinates and the edition radius.
   *
   * @param ex The x coordinate.
   * @param ey The y coordinate.
   * @param gameScroll The scroll pane.
   * @param gamePanel The game panel.
   */
  private void drawPoint(int ex, int ey, JScrollPane gameScroll, GameOfLifeUI gamePanel) {
    int x = ex / gamePanel.getCaseSize();
    int y = ey / gamePanel.getCaseSize();

    JViewport viewPort = gameScroll.getViewport();
    int dx = viewPort.getViewPosition().x;
    int dy = viewPort.getViewPosition().y;

    for (int i = -editionRadius; i <= editionRadius; i++) {
      for (int j = -editionRadius; j <= editionRadius; j++) {
        if (x + i >= 0 && x + i < game.getXMax() && y + j >= 0 && y + j < game.getYMax()) {
          final float distance = (float) Math.sqrt(i * i + j * j);
          if (distance >= editionRadius) {
            continue;
          }
          if (drawMode.equals("live")) {
            game.getCell(x + i + dx / gamePanel.getCaseSize(), y + j + dy / gamePanel.getCaseSize()).live();
          } else {
            game.getCell(x + i + dx / gamePanel.getCaseSize(), y + j + dy / gamePanel.getCaseSize()).die();
          }
        }
      }
    }

    gamePanel.repaint();
  }

  /**
   * Pause the game.
   */
  private void pauseGame() {
    if (game.isRunning()) {
      game.stop();
      playButton.setText("Play");
    }
  }

  /**
   * Play the game.
   */
  private void playGame() {
    if (!game.isRunning()) {
      game.start();
      playButton.setText("Pause");
    }
  }
}
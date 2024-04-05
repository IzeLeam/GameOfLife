package fr.izeleam.managers;

import fr.izeleam.Main;
import fr.izeleam.gui.Controller;
import fr.izeleam.gui.OverController;
import fr.izeleam.gui.controllers.MenuController;
import fr.izeleam.managers.OSManager.OS;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Manager gérant les contrôleurs.
 * Classe singleton qui gère les différents contrôleurs de l'application.
 * 
 * @author POURCEAU Luca
 * @see Controller
 */
public class ControllerManager {

  /** Instance du manager. */
  private static final ControllerManager instance = new ControllerManager();
  /** Scène principale de l'application. */
  private final Stage stage;
  /** Liste des contrôleurs de type {@link OverController}. */
  private final List<OverController> overControllers = new ArrayList<>();
  /** Largeur de l'écran. */
  private final double screenWidth;
  /** Hauteur de l'écran. */
  private final double screenHeight;

  /**
   * Constructeur privé du manager.
   * Crée une nouvelle instance du manager.
   * Initialise la scène principale de l'application.
   */
  private ControllerManager() {
    this.stage = new Stage();
    this.stage.setTitle("Game of Life");

    URL icon = Main.class.getResource("assets/icon.png");
    if (icon != null) {
      this.stage.getIcons().add(new Image(icon.toString()));
    }

    this.stage.setResizable(false);
    this.stage.setScene(new Scene(new AnchorPane()));
    URL css = Main.class.getResource("styles/style.css");
    if (css != null) {
      this.stage.getScene().getStylesheets().add(css.toString());
    }

    OSManager osManager = OSManager.getInstance();
    boolean isWindows = osManager.getOS().equals(OS.WINDOWS);

    if (isWindows) {
      this.stage.setMaximized(true);
      this.stage.show();
      this.screenWidth = stage.getWidth();
      this.screenHeight = stage.getHeight();
    } else {
      final Screen screen = Screen.getPrimary();
      this.screenWidth = screen.getBounds().getWidth();
      this.screenHeight = screen.getBounds().getHeight();
    }

    this.stage.show();
  }

  /**
   * Retourne l'instance du manager.
   * 
   * @return l'instance du manager
   */
  public static ControllerManager getInstance() {
    return instance;
  }

  /**
   * Affiche le contrôleur passé en paramètre.
   * 
   * @param controller le contrôleur à afficher
   */
  public void show(Controller controller) {
    this.stage.getScene().setRoot(controller.getRoot());
    for (OverController overController : this.overControllers) {
      overController.getStage().close();
    }
  }

  /**
   * Affiche le contrôleur passé en paramètre.
   * il afficher au dessus de la fenêtre principale.
   * 
   * @param controller le contrôleur à afficher
   */
  public void showOver(OverController controller) {
    controller.getStage().show();
    this.overControllers.add(controller);
  }

  /**
   * Ferme le contrôleur passé en paramètre.
   * 
   * @param controller le contrôleur à fermer
   */
  public void close(OverController controller) {
    controller.getStage().close();
    this.overControllers.remove(controller);
  }

  /**
   * Retourne la scène principale de l'application.
   * 
   * @return la scène principale de l'application
   */
  public double getScreenHeight() {
    return screenHeight;
  }

  /**
   * Retourne la largeur de l'écran.
   * 
   * @return la largeur de l'écran
   */
  public double getScreenWidth() {
    return screenWidth;
  }
}
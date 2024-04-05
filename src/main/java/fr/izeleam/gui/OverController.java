package fr.izeleam.gui;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Contrôleur de fenêtre.
 * Classe abstraite permettant de gérer les contrôleurs de fenêtres.
 * 
 * @author POURCEAU Luca
 * @see Controller
 */
public abstract class OverController extends Controller {

  /** Fenêtre du contrôleur. */
  protected final Stage stage;

  /**
   * Constructeur de la fenêtre.
   * Crée une nouvelle instance de la fenêtre.
   */
  protected OverController() {
    super();

    this.stage = new Stage();
    this.stage.initStyle(StageStyle.UNDECORATED);
    this.stage.initModality(Modality.APPLICATION_MODAL);
    this.stage.setResizable(false);
    this.stage.setScene(new Scene(this.frame.getRoot()));
  }

  /**
   * Affiche la fenêtre.
   */
  public final void show() {
    this.controllerManager.showOver(this);
  }

  /**
   * Ferme la fenêtre.
   */
  public final void close() {
    this.controllerManager.close(this);
  }

  /**
   * Retourne le Stage de la fenêtre.
   * 
   * @return le Stage de la fenêtre.
   * @see Stage
   */
  public Stage getStage() {
    return stage;
  }

  /**
   * Méthode abstraite de frame.
   * permet de retourner la frame de la fenêtre.
   */
  protected abstract Frame getFrame();
}
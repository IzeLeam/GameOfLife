package fr.izeleam.gui;

import fr.izeleam.managers.ControllerManager;
import fr.izeleam.managers.InteractManager;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Parent;

/**
 * Classe abstraite qui représente un contrôleur graphique d'une interface plein écran simple.
 *
 * @author POURCEAU Luca
 */
public abstract class Controller {

  /**
   * L'instance du gestionnaire de contrôleurs pour des accès simplifiés.
   *
   * @see ControllerManager
   */
  protected final ControllerManager controllerManager = ControllerManager.getInstance();
  /**
   * La fenêtre graphique du contrôleur.
   *
   * @see Frame
   */
  protected Frame frame;

  /**
   * Constructeur d'un contrôleur.
   */
  protected Controller() {
    this.frame = getFrame();
    InteractManager.getInstance().registerAll(this);
  }

  /**
   * Méthode qui affiche le contrôleur.
   */
  public void show() {
    this.controllerManager.show(this);
  }

  /**
   * Méthode permettant de récupérer la source JavaFX de la fenêtre graphique.
   *
   * @return Parent la source JavaFX de la fenêtre graphique
   */
  public Parent getRoot() {
    return this.frame.getRoot();
  }

  /**
   * Méthode permettant de récupérer un item dans la fenêtre graphique grace à son identifiant.
   *
   * @param id l'identifiant de l'élément
   * @return Node l'élément recherché
   */
  public Node lookup(String id) {
    return this.frame.lookup(id);
  }

  /**
   * Méthode permettant de récupérer tous les items dans la fenêtre graphique grace à leur identifiant.
   *
   * @param id l'identifiant de l'élément
   * @return Set Node l'ensemble des éléments recherchés
   */
  public Set<Node> lookupAll(String id) {
    return this.frame.lookupAll(id);
  }

  /**
   * Méthode permettant de récupérer la scène graphique de la fenêtre, correspondant à l'initialisation de la partie graphique.
   *
   * @return Scene la scène graphique de la fenêtre
   */
  protected abstract Frame getFrame();
}
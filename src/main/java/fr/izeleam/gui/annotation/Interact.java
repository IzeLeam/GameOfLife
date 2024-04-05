package fr.izeleam.gui.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation permettant de définir une méthode d'interaction avec un élément de l'interface.
 *
 * @author POURCEAU Luca
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Interact {

  /**
   * L'identifiant de l'élément JavaFX.
   *
   * @return l'identifiant de l'élément JavaFX
   */
  String[] id() default {};

  /**
   * Le type d'interaction.
   *
   * @return le type d'interaction
   */
  InteractType type() default InteractType.ON_ACTION;

  /**
   * Enumération des types d'interactions possibles.
   */
  enum InteractType {
    /**
     * Interaction sur un clic.
     */
    ON_ACTION("setOnAction"),
    /**
     * Interaction sur un clic.
     */
    ON_MOUSE_CLICKED("setOnMouseClicked"),
    /**
     * Interaction sur un l'entrée de la souris.
     */
    ON_MOUSE_ENTERED("setOnMouseEntered"),
    /**
     * Interaction sur la sortie de la souris.
     */
    ON_MOUSE_EXITED("setOnMouseExited"),
    /**
     * Interaction sur la pression de la souris.
     */
    ON_MOUSE_PRESSED("setOnMousePressed"),
    /**
     * Interaction sur le relâchement de la souris.
     */
    ON_MOUSE_RELEASED("setOnMouseReleased"),
    /**
     * Interaction sur le déplacement cliqué de la souris.
     */
    ON_MOUSE_DRAGGED("setOnMouseDragged"),
    /**
     * Interaction sur la pression d'une touche.
     */
    ON_KEY_PRESSED("setOnKeyPressed"),
    /**
     * Interaction sur le relâchement d'une touche.
     */
    ON_KEY_RELEASED("setOnKeyReleased"),
    /**
     * Interaction sur la frappe d'une touche.
     */
    ON_KEY_TYPED("setOnKeyTyped"),
    /**
     * Interaction sur le déroulement avec la molette de la souris.
     */
    ON_SCROLL("setOnScroll"),
    /**
     * Interaction sur le début du déroulement avec la molette de la souris.
     */
    ON_SCROLL_STARTED("setOnScrollStarted"),
    /**
     * Interaction sur la fin du déroulement avec la molette de la souris.
     */
    ON_SCROLL_FINISHED("setOnScrollFinished");

    /**
     * Le nom de la méthode associée.
     */
    private final String methodName;

    /**
     * Constructeur.
     *
     * @param methodName le nom de la méthode associée
     */
    InteractType(String methodName) {
      this.methodName = methodName;
    }

    /**
     * Récupère le nom de la méthode associée.
     *
     * @return le nom de la méthode associée
     */
    public String getMethodName() {
      return methodName;
    }
  }
}
package fr.izeleam.gui;

import fr.izeleam.managers.ControllerManager;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * Util class for a better handling of a JavaFX source.
 *
 * @author POURCEAU Luca
 */
public class Frame {

  private final AnchorPane root;
  private final double width;
  private final double height;
  private final boolean maximized;

  public Frame() {
    this(true, 0, 0);
  }

  public Frame(double width, double height) {
    this(true, width, height);
  }

  private Frame(boolean maximized, double w, double h) {
    this.maximized = maximized;
    this.root = new AnchorPane();

    double width;
    double height;

    if (!maximized) {
      width = w;
      height = h;
      this.root.setPrefWidth(width);
      this.root.setPrefHeight(height);
      this.root.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
    } else {
      width = ControllerManager.getInstance().getScreenWidth();
      height = ControllerManager.getInstance().getScreenHeight();
    }
    this.width = width;
    this.height = height;
  }

  public Parent getRoot() {
    return this.root;
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }

  public void setWidth(double width) {
    if (maximized) {
      return;
    }
    root.setPrefWidth(width);
  }

  public void setHeight(double height) {
    if (maximized) {
      return;
    }
    root.setPrefHeight(height);
  }

  public void setSize(double width, double height) {
    if (maximized) {
      return;
    }
    root.setPrefWidth(width);
    root.setPrefHeight(height);
  }

  public void add(Node node) {
    this.root.getChildren().add(node);
  }

  public void add(Node node, double x, double y) {
    node.setLayoutX(x);
    node.setLayoutY(y);
    this.root.getChildren().add(node);
  }

  protected Node lookup(String id) {
    return this.lookup(id, this.root);
  }

  private Node lookup(String id, Parent parent) {
    if (parent.getId() != null && parent.getId().matches(id)) {
      return parent;
    }
    for (Node n : parent.getChildrenUnmodifiable()) {
      if (n.getId() != null && n.getId().matches(id)) {
        return n;
      }
      if (n instanceof Parent) {
        Node result = this.lookup(id, (Parent) n);
        if (result != null) {
          return result;
        }
      }
      if (n instanceof ScrollPane) {
        Node result = this.lookup(id, (Parent) ((ScrollPane) n).getContent());
        if (result != null) {
          return result;
        }
      }
    }
    return null;
  }

  public Set<Node> lookupAll(String id) {
    return lookupAll(id, this.root);
  }

  private Set<Node> lookupAll(String id, Parent parent) {
    Set<Node> result = new HashSet<>();
    if (parent.getId() != null && parent.getId().matches(id)) {
      result.add(parent);
    }
    for (Node n : parent.getChildrenUnmodifiable()) {
      if (n.getId() != null && n.getId().matches(id)) {
        result.add(n);
      }
      if (n instanceof Parent) {
        result.addAll(this.lookupAll(id, (Parent) n));
      }
      if (n instanceof ScrollPane) {
        result.addAll(this.lookupAll(id, (Parent) ((ScrollPane) n).getContent()));
      }
    }
    return result;
  }
}

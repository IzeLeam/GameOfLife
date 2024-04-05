package fr.izeleam.gui.controllers;

import fr.izeleam.gui.Frame;
import fr.izeleam.gui.OverController;
import java.util.function.Consumer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConfirmController<T> extends OverController {

  private final Consumer<T> onAccept;
  private final Consumer<T> onRefuse;
  private final String text;
  private final T data;

  public ConfirmController(String text, Consumer<T> yesAction, Consumer<T> onRefuse, T data) {
    super();
    this.text = text;
    this.onAccept = yesAction;
    this.onRefuse = onRefuse;
    this.data = data;
  }

  @Override
  protected Frame getFrame() {
    Frame f = new Frame(400, 200);
    f.getRoot().getStylesheets().add("fr/mcgcorp/fxml/styles/style.css");

    final Label message = new Label(text);
    message.setStyle("-fx-text-fill: white; -fx-font-size: 20;");
    message.setLayoutX(50);
    message.setLayoutY(50);

    final Button yes = new Button("Oui");
    yes.setPrefHeight(30);
    yes.setPrefWidth(100);
    yes.setLayoutX(50);
    yes.setLayoutY(100);
    yes.setOnAction(e -> {
      this.onAccept.accept(data);
    });

    final Button no = new Button("Non");
    no.setPrefHeight(30);
    no.setPrefWidth(100);
    no.setLayoutX(250);
    no.setLayoutY(100);
    no.setOnAction(e -> {
      this.onRefuse.accept(data);
      this.close();
    });

    f.add(message);
    f.add(yes);
    f.add(no);

    return f;
  }
}
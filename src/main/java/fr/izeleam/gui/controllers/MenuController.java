package fr.izeleam.gui.controllers;

import fr.izeleam.gui.Controller;
import fr.izeleam.gui.Frame;
import fr.izeleam.gui.annotation.Interact;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MenuController extends Controller {

  private static final MenuController instance = new MenuController();

  public static MenuController getInstance() {
    return instance;
  }

  @Override
  public Frame getFrame() {
    Frame f = new Frame();

    Button b = new Button("Play");
    b.setId("play");
    b.setPrefHeight(30);
    b.setPrefWidth(100);

    f.add(b, (f.getWidth() - b.getPrefWidth()) / 2, (f.getHeight() - b.getPrefHeight()) / 2);

    return f;
  }

  @Interact(id = "play")
  public void onPlay(ActionEvent event) {
  }
}
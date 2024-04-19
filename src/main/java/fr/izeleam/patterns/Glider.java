package fr.izeleam.patterns;

public class Glider extends Pattern {

  private static final Glider instance = new Glider();

  private Glider() {
  }

  public static Glider getInstance() {
    return instance;
  }

  public String getName() {
    return "Glider";
  }

  public int getWidth() {
    return 3;
  }

  public int getHeight() {
    return 3;
  }

  public String getSerialised() {
    return "010001111";
  }
}

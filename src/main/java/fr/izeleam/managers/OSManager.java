package fr.izeleam.managers;

public class OSManager {

  private static final OSManager instance = new OSManager();

  public static OSManager getInstance() {
    return instance;
  }

  private OSManager() {
    this.os = OS.getOS(System.getProperty("os.name").toLowerCase());
  }

  private final OS os;

  public OS getOS() {
    return os;
  }

  public enum OS {
    WINDOWS("win"),
    LINUX("nux"),
    MAC("mac");

    OS(String osName) {
    }

    private static OS getOS(String osName) {
      if (osName.contains("win")) {
        return WINDOWS;
      } else if (osName.contains("nux")) {
        return LINUX;
      } else if (osName.contains("mac")) {
        return MAC;
      } else {
        throw new IllegalArgumentException("OS not supported");
      }
    }
  }
}
package fr.izeleam.util;

/**
 * Interface to represent an observable object.
 */
public interface Observable {

  /**
   * Add an observer to the observable object.
   *
   * @param observer The observer to add.
   */
  void addObserver(Observer observer);

  /**
   * Remove an observer from the observable object.
   *
   * @param observer The observer to remove.
   */
  void removeObserver(Observer observer);

  /**
   * Notify the observers of the observable object to update themselves.
   */
  void notifyObservers();
}

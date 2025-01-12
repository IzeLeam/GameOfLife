package fr.izeleam.patterns;

import fr.izeleam.GameOfLife;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents a pattern in the game of life.
 */
public class Pattern {

  private final String name;
  private final int width;
  private final int height;
  private final String serialised;

  /**
   * Constructor.
   *
   * @param name The name of the pattern.
   * @param size The size of the pattern.
   * @param serialised The serialised pattern.
   */
  public Pattern(final String name, final String size, final String serialised) {
    this.name = name;
    String[] sizes = size.split("x");
    this.width = Integer.parseInt(sizes[0]);
    this.height = Integer.parseInt(sizes[1]);
    this.serialised = serialised;
    if (this.serialised.length() != this.width * this.height) {
      throw new IllegalArgumentException("Invalid pattern size for " + name + ". Expected " + this.width * this.height + " but got " + this.serialised.length()
      + " in : \n"
      + Arrays.stream(this.serialised.split("(?<=\\G.{" + this.width + "})"))
          .collect(Collectors.joining("\n")));
    }
  }

  /**
   * Get the name of the pattern.
   *
   * @return The name of the pattern.
   */
  public String getName() {
    return name;
  }

  /**
   * Get the width of the pattern.
   *
   * @return The width of the pattern.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Get the height of the pattern.
   *
   * @return The height of the pattern.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Get the serialised pattern.
   *
   * @return The serialised pattern.
   */
  public String getSerialised() {
    return serialised;
  }

  /**
   * Print the pattern on the game of life.
   *
   * @param game The game of life.
   * @param x The x position.
   * @param y The y position.
   * @param direction The direction of the pattern.
   */
  public void print(final GameOfLife game, final int x, final int y, final PatternDirection direction) {
    String[] serialised = getSerialised().split("");
    int[][] matrix = new int[getHeight()][getWidth()];
    int index = 0;
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        if (x + j < game.getXMax() && y + i < game.getYMax()) {
          matrix[i][j] = Integer.parseInt(serialised[index]);
        }
        index++;
      }
    }

    switch (direction) {
      case NORTH:
        break;
      case EAST:
        rotateMatrix(matrix);
        break;
      case SOUTH:
        rotateMatrix(matrix);
        rotateMatrix(matrix);
        break;
      case WEST:
        rotateMatrix(matrix);
        rotateMatrix(matrix);
        rotateMatrix(matrix);
        break;
    }

    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        if (x + j < game.getXMax() && y + i < game.getYMax()) {
          if (matrix[i][j] == 1) {
            game.getCell(x + j, y + i).live();
          }
        }
      }
    }
  }

  /**
   * Rotate the matrix.
   *
   * @param matrix The matrix.
   */
  private void rotateMatrix(int[][] matrix) {
    int N = matrix.length;
    for (int x = 0; x < N / 2; x++) {
      for (int y = x; y < N - x - 1; y++) {
        int temp = matrix[x][y];
        matrix[x][y] = matrix[y][N - 1 - x];
        matrix[y][N - 1 - x] = matrix[N - 1 - x][N - 1 - y];
        matrix[N - 1 - x][N - 1 - y] = matrix[N - 1 - y][x];
        matrix[N - 1 - y][x] = temp;
      }
    }
  }
}
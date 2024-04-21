package fr.izeleam.patterns;

import fr.izeleam.GameOfLife;

public class Pattern {

  private final String name;
  private final int width;
  private final int height;
  private final String serialised;

  public Pattern(final String name, final String size, final String serialised) {
    this.name = name;
    String[] sizes = size.split("x");
    this.width = Integer.parseInt(sizes[0]);
    this.height = Integer.parseInt(sizes[1]);
    this.serialised = serialised;
  }

  public String getName() {
    return name;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public String getSerialised() {
    return serialised;
  }

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
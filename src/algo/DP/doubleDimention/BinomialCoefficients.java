package algo.DP.doubleDimention;

public class BinomialCoefficients {
  public static int binomialCoefficients(int r, int c) {
    int[][] arr = new int[r][r];
    for (int i = 0; i < r; i++) {
      for (int j = 0; j <= i; j++) {
        if (j == 0 || j == i) {
          arr[i][j] = 1;
        } else {
          arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
        }
      }
    }
    return arr[r - 1][c - 1];
  }
}

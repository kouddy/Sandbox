package algo.DP.doubleDimention;

public class RodCutting {
  public static void test() {
    rodCuttingNMemory(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8);
    rodCuttingNSquareMemory(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8);
  }

  public static int rodCuttingNMemory(int[] p, int n) {
    int[] maxes = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      int max = 0;
      for (int j = 0; j < p.length; j++) {
        if (i - j - 1 >= 0) {
          max = Math.max(max, p[j] + maxes[i - j - 1]);
        }
      }
      maxes[i] = max;
    }
    return maxes[n];
  }

  public static int rodCuttingNSquareMemory(int[] p, int n) {
    int[][] maxes = new int[p.length][n + 1];
    for (int j = 0; j < p.length; j++) {
      for (int i = 0; i <= n; i++) {
        int includingJ = i - j - 1 >= 0 ? p[j] + maxes[j][i - j - 1] : 0;
        int excludingJ = j - 1 >= 0 ? maxes[j - 1][i] : 0;
        maxes[j][i] = Math.max(includingJ, excludingJ);
      }
    }
    return maxes[p.length - 1][n];
  }
}

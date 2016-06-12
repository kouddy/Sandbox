package algo.DP.doubleDimention;

public class ZeroOneKnapsack {
  public static void test() {
    zeroOneKnapsackProblem(new int[] { 1, 4, 5, 7 }, new int[] { 1, 3, 4, 5 }, 7);
    zeroOneKnapsackNMemory(new int[] { 1, 4, 5, 7 }, new int[] { 1, 3, 4, 5 }, 7);
  }

  public static int zeroOneKnapsackNMemory(int[] val, int[] wt, int n) {
    int[] maxes = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      int max = 0;
      for (int j = 0; j < val.length; j++) {
        if (i - wt[j] >= 0) {
          max = Math.max(max, val[j] + maxes[i - wt[j]]);
        }
      }
      maxes[i] = max;
    }
    return maxes[n];
  }

  public static int zeroOneKnapsackProblem(int[] val, int[] wt, int n) {
    int[][] maxVal = new int[val.length][n + 1];
    for (int i = 0; i < val.length; i++) {
      for (int j = 0; j <= n; j++) {
        if (j == 0) {
          maxVal[i][j] = 0;
        } else {
          int includingI = (j - wt[i] >= 0) ? val[i] + maxVal[i][j - wt[i]] : 0;
          int excludingI = i - 1 >= 0 ? maxVal[i - 1][j] : 0;
          maxVal[i][j] = Math.max(includingI, excludingI);
        }
      }
    }

    return maxVal[val.length - 1][n];
  }
}

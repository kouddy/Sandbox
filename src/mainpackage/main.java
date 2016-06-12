package mainpackage;

public class main {
  public static void main(String[] args) {
    System.out.println(longestCommonSeq("abcdaf", "acbcf"));
    System.out.println(minimumEdit("you should not", "thou shalt not"));
    zeroOneKnapsackProblem(new int[] { 1, 4, 5, 7 }, new int[] { 1, 3, 4, 5 }, 7);
    zeroOneKnapsackNMemory(new int[] { 1, 4, 5, 7 }, new int[] { 1, 3, 4, 5 }, 7);
    rodCuttingNMemory(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8);
    rodCuttingNSquareMemory(new int[] { 1, 5, 8, 9, 10, 17, 17, 20 }, 8);
    maxIncreasingSubseq(new int[] { 1, 101, 3, 4, 100 });
  }

  public static int minimumEdit(String text, String pattern) {
    text = " " + text;
    pattern = " " + pattern;
    int[][] edits = new int[pattern.length()][text.length()];
    for (int i = 0; i < pattern.length(); i++) {
      for (int j = 0; j < text.length(); j++) {
        if (i == 0) {
          edits[i][j] = j;
        } else if (j == 0) {
          edits[i][j] = i;
        } else {
          int min = edits[i - 1][j - 1];
          if (pattern.charAt(i) != text.charAt(j)) {
            min = edits[i - 1][j - 1] + 1;
            min = Math.min(edits[i - 1][j] + 1, min); // extra char in pattern to account
            min = Math.min(edits[i][j - 1] + 1, min); // extra char in text to account.
          }
          edits[i][j] = min;
        }
      }
    }
    return edits[pattern.length() - 1][text.length() - 1];
  }

  public static int longestCommonSeq(String text, String pattern) {
    int[][] max = new int[pattern.length()][text.length()];
    for (int i = 0; i < pattern.length(); i++) {
      for (int j = 0; j < text.length(); j++) {
        if (j == 0 || i == 0) {
          max[i][j] = pattern.charAt(0) == text.charAt(0) ? 1 : 0;
        } else {
          if (pattern.charAt(i) == text.charAt(j)) {
            max[i][j] = max[i - 1][j - 1] + 1;
          } else {
            max[i][j] = Math.max(max[i - 1][j], max[i][j - 1]);
          }
        }
      }
    }
    return max[pattern.length() - 1][text.length() - 1];
  }

  public static int coinChange(int value, int[] coins) {
    return -1;
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

  public static int maxIncreasingSubseq(int[] a) {
    int[] maxes = new int[a.length];
    for (int i = 0; i < a.length; i++) {
      int max = a[i];
      for (int j = 0; j < i; j++) {
        if (a[j] < a[i]) {
          max = Math.max(max, maxes[j] + a[i]);
        }
      }
      maxes[i] = max;
    }
    return maxes[a.length];
  }
}
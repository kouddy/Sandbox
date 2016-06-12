package algo.DP.doubleDimention;

public class LongestCommonSeq {
  public static void test() {
    System.out.println(longestCommonSeq("abcdaf", "acbcf"));
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
}

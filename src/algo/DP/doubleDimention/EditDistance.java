package algo.DP.doubleDimention;

public class EditDistance {
  public static void test() {
    System.out.println(editDistance("you should not", "thou shalt not"));
  }

  public static int editDistance(String text, String pattern) {
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
}

package algo.DP.doubleDimention;

public class LongestIncreasingSubseq {
  public static int longestIncreasingSubseq(int[] arr) {
    int[] s = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      int max = 1;
      for (int j = 1; j < i; j++) {
        if (arr[j] < arr[i] && s[j] + 1 > max) {
          max = s[j] + 1;
        }
      }
      s[i] = max;
    }
    return s[arr.length - 1];
  }

  public static void test() {
    System.out.println(longestIncreasingSubseq(new int[] { 0, 1, 2, 3, 0, 1, 4, -10, -9, 4, 5 }));
  }
}

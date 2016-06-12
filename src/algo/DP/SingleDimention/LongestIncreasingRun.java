package algo.DP.SingleDimention;

public class LongestIncreasingRun {
  public static int longestIncreasingRun(int[] a) {
    int pastMax = -1;
    int maxNow = 1;
    for (int i = 1; i < a.length; i++) {
      if (a[i - 1] < a[i]) {
        maxNow++;
      } else {
        if (maxNow > pastMax) {
          pastMax = maxNow;
        }
        maxNow = 1;
      }
    }
    return Math.max(pastMax, maxNow);
  }

  public static void test() {
    System.out.println(longestIncreasingRun(new int[] { 0, 1, 2, 3, 0, 1, 4, -10, -9, 4, 5 }));
  }
}

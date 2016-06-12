package algo.DP.SingleDimention;

public class LargestIncreasingSubArray {
  public static int largestSubArray(int[] a) {
    int pastMax = 0;
    int nowMax = 0;
    for (int i = 0; i < a.length; i++) {
      if (nowMax + a[i] < nowMax) {
        pastMax = Math.max(nowMax, pastMax);
      }
      nowMax = Math.max(nowMax + a[i], a[i]);
    }
    return Math.max(pastMax, nowMax);
  }

  public static void test() {
    System.out.println(largestSubArray(new int[] { -2, 1, -3, 4, -1, 2, 1, -3, 4 }));
  }
}

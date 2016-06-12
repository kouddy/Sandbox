package algo.DP.doubleDimention;

public class ZigZag {
  public static int zigZag(int[] arr) {
    int[] s = new int[arr.length];
    int[] diffs = new int[arr.length];
    diffs[0] = 0;
    for (int i = 1; i < arr.length; i++) {
      diffs[i] = arr[i] - arr[i - 1];
    }
    for (int i = 0; i < arr.length; i++) {
      int max = 1;
      for (int j = 1; j <= i; j++) {
        int diff = arr[i] - arr[j - 1];
        if ((j - 1 <= 0 || (diffs[j - 1] < 0 && 0 < diff) || (0 < diffs[j - 1] && diff < 0)) && s[j - 1] + 1 > max) {
          max = s[j - 1] + 1;
        }
      }
      s[i] = max;
    }
    return s[arr.length - 1];
  }

  public static void test() {
    System.out.println(zigZag(new int[] { 70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 }));
  }
}

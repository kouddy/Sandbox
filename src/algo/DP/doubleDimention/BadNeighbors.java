package algo.DP.doubleDimention;

import java.util.Arrays;

public class BadNeighbors {
  public static int badNeighbors(int[] arr) {
    int[] donations1 = new int[arr.length - 1];
    for (int i = 0; i < arr.length - 1; i++) {
      int max = arr[i];
      for (int j = 0; j < i; j++) {
        max = Math.max(max, j - 1 >= 0 ? donations1[j - 1] + arr[i] : 0);
      }
      donations1[i] = max;
    }
    int[] arr2 = Arrays.copyOfRange(arr, 1, arr.length);
    int[] donations2 = new int[arr.length - 1];
    for (int i = 0; i < arr2.length; i++) {
      int max = arr2[i];
      for (int j = 0; j < i; j++) {
        max = Math.max(max, j - 1 >= 0 ? donations2[j - 1] + arr2[i] : 0);
      }
      donations2[i] = max;
    }
    return Math.max(donations1[arr.length - 2], donations2[arr.length - 2]);
  }

  public static void test() {
    System.out.println(badNeighbors(new int[] { 94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61, 6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397, 52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57,
        86, 81, 72 }));
  }
}

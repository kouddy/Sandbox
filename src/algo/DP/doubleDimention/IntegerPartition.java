package algo.DP.doubleDimention;

import java.util.Arrays;
import java.util.stream.Collectors;

public class IntegerPartition {
  public static int[] integerPartitions(int[] arr, int numCuts) {
    int[] sums = new int[arr.length];
    sums[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      sums[i] = sums[i - 1] + arr[i];
    }
    int[][] cuts = new int[arr.length][numCuts + 1];
    for (int i = 0; i < arr.length; i++) {
      for (int k = 0; k <= numCuts; k++) {
        int minIndex = 0;
        if (i == 0 || k == 0) {
          minIndex = 0;
        } else {
          int minSum = Integer.MAX_VALUE;
          for (int j = i; j > 0; j--) {
            int curSum = Math.abs((sums[i] - sums[j - 1]) * k - sums[j - 1]);
            if (curSum < minSum) {
              minIndex = j;
              minSum = curSum;
            }
          }
        }
        cuts[i][k] = minIndex;
      }
    }

    int[] cutPositions = new int[numCuts + 1];
    for (int k = numCuts, i = arr.length - 1; k >= 0; i = cutPositions[k] - 1, k--) {
      cutPositions[k] = cuts[i][k];
    }
    return cutPositions;
  }

  public static String getPartitionedArrayString(int[] arr, int[] cutPositions) {
    int[][] partitions = new int[cutPositions.length][];
    for (int i = 0; i < cutPositions.length; i++) {
      if (i + 1 >= cutPositions.length) {
        partitions[i] = Arrays.copyOfRange(arr, cutPositions[i], arr.length);
      } else {
        partitions[i] = Arrays.copyOfRange(arr, cutPositions[i], cutPositions[i + 1]);
      }
    }
    return Arrays.stream(partitions).map(a -> Arrays.stream(a).mapToObj(v -> String.valueOf(v)).collect(Collectors.joining(",", "[", "]"))).collect(Collectors.joining(","));
  }

  public static void test() {
    System.out.println(IntegerPartition.getPartitionedArrayString(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, IntegerPartition.integerPartitions(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 2)));
  }
}

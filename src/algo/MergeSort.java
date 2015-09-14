package algo;

import java.util.Arrays;

public class MergeSort {
  public static int[] sortAscending(int[] array) {
    if (array.length != 1) {
      int r = array.length / 2;
      int[] ret1 = sortAscending(Arrays.copyOfRange(array, 0, r));
      int[] ret2 = sortAscending(Arrays.copyOfRange(array, r, array.length));
      return merge(ret1, ret2);
    } else {
      return array;
    }
  }

  /**
   * @description Merges sorted two integer arrays
   * @param a1
   * @param b1
   * @return Merged array
   */
  public static int[] merge(int[] a, int[] b) {
    int[] ret = new int[a.length + b.length];
    int ai = 0;
    int bi = 0;
    int reti = 0;

    while (ai < a.length && bi < b.length) {
      if (a[ai] < b[bi]) {
        ret[reti++] = a[ai++];
      } else {
        ret[reti++] = b[bi++];
      }
    }

    if (ai == a.length) {
      while (bi < b.length) {
        ret[reti++] = b[bi++];
      }
    } else {
      while (ai < a.length) {
        ret[reti++] = a[ai++];
      }
    }
    return ret;
  }
}

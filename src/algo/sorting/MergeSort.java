package algo.sorting;

import java.util.Arrays;

public class MergeSort {
  public static int[] sortAscending(int[] array) {
    if (array.length <= 1) {
      return array;
    }
    int mid = array.length / 2;
    return merge(sortAscending(Arrays.copyOfRange(array, 0, mid)),
        sortAscending(Arrays.copyOfRange(array, mid, array.length)));
  }

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

    while (bi < b.length) {
      ret[reti++] = b[bi++];
    }
    while (ai < a.length) {
      ret[reti++] = a[ai++];
    }
    return ret;
  }
}

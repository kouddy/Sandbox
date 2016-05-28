package algo.sorting;

import util.Util;

public class BubbleSort {
  public String test = "haha";

  public static int[] sortAscending(int array[]) {
    int len = array.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = len - 1; j >= i + 1; j--) {
        if (array[j] < array[j - 1]) {
          int tmp = array[j - 1];
          array[j - 1] = array[j];
          array[j] = tmp;
        }
      }
    }
    return array;
  }

  public static int[] sortDescending(int array[]) {
    int len = array.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = len - 1; j >= i + 1; j--) {
        if (array[j] > array[j - 1]) {
          int tmp = array[j - 1];
          array[j - 1] = array[j];
          array[j] = tmp;
        }
      }
    }
    return array;
  }

  /**
   * @description Used for permutation.
   * @param array
   * @param index
   * @return
   */
  public static int[] sortAscending(int array[], int index) {
    int len = array.length;
    for (int i = 0; i < len - 1; i++) {
      for (int j = len - 1; j >= i + 1; j--) {
        if (array[j] < array[j - 1]) {
          int tmp = array[j - 1];
          array[j - 1] = array[j];
          array[j] = tmp;
        }
      }
      Util.printIntArray(array);
    }
    return array;
  }
}

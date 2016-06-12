package algo.sorting;

public class InsertionSort {
  public static int[] sortAscending(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int elem = array[i];
      int j = i - 1;
      for (; j >= 0 && array[j] > elem; j--) {
        array[j + 1] = array[j];
      }
      array[j + 1] = elem;
    }
    return array;
  }
}
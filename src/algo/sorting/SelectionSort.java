package algo.sorting;

public class SelectionSort {
  public static int[] sortAscending(int[] array) {
    int len = array.length;
    for (int i = 0; i < len - 1; i++) {
      int min = array[i];
      int index = i;
      for (int j = i + 1; j < len; j++) {
        if (min > array[j]) {
          min = array[j];
          index = j;
        }
      }
      array[index] = array[i];
      array[i] = min;
    }
    return array;
  }
}

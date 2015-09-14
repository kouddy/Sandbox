package algo;

public class InsertionSort {
  public static int[] sortAscending(int[] array) {
    int len = array.length;

    for (int j = 1; j < len; j++) {
      int key = array[j];
      int i = j - 1;
      while ((i >= 0) && (array[i] > key)) {
        array[i + 1] = array[i];
        i = i - 1;
      }
      array[i + 1] = key;
    }
    return array;
  }

  public static int[] sortDescending(int[] array) {
    int len = array.length;

    for (int j = 1; j < len; j++) {
      int key = array[j];
      int i = j - 1;
      while ((i >= 0) && (array[i] < key)) {
        array[i + 1] = array[i];
        i = i - 1;
      }
      array[i + 1] = key;
    }
    return array;
  }
}

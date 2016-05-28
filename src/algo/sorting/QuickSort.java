package algo.sorting;

public class QuickSort {
  public static void sort(int[] a, int p, int r) {
    if (p < r) {
      int q = partition(a, p, r);
      sort(a, p, q - 1);
      sort(a, q + 1, r);
    }
  }

  public static int partition(int[] a, int p, int r) {
    int elem = a[r - 1];
    int i = p - 1;
    for (int j = p; j < r - 1; j++) {
      if (a[j] < elem) {
        i++;
        swap(a, i, j);
      }
    }
    swap(a, i + 1, r - 1);
    return i + 1;
  }

  public static void swap(int[] a, int i1, int i2) {
    int tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
}

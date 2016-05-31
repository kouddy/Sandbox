package algo.sorting;

public class QuickSort {
  public static int[] quickSort(int[] a) {
    sort(a, 0, a.length);
    return a;
  }

  public static void sort(int[] a, int p, int r) {
    if (p < r) {
      int q = partition(a, p, r);
      sort(a, p, q - 1);
      sort(a, q + 1, r);
    }
  }

  public static int partition(int[] a, int p, int r) {
    int l = p;
    int pivot = a[r - 1];
    for (int i = p; i < r - 1; i++) {
      if (a[i] <= pivot) {
        int tmp = a[i];
        a[i] = a[l];
        a[l] = tmp;
        l++;
      }
    }
    a[r - 1] = a[l];
    a[l] = pivot;
    return l;
  }
}

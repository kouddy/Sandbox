package algo.sorting;

public class QuickSort {
  public static int[] quickSort(int[] a) {
    sort(a, 0, a.length);
    return a;
  }

  public static void sort(int[] a, int low, int highExc) {
    if (low < highExc) {
      int q = partition(a, low, highExc);
      sort(a, low, q);
      sort(a, q + 1, highExc);
    }
  }

  public static int partition(int[] a, int low, int highExc) {
    int l = low;
    int pivot = a[highExc - 1];
    for (; low < highExc; low++) {
      if (a[low] <= pivot) {
        int tmp = a[low];
        a[low] = a[l];
        a[l] = tmp;
        l++;
      }
    }
    /* For last one, I'm swapping with myself, so no need to swap */
    return l - 1;
  }
}

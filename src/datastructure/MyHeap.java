package datastructure;

public class MyHeap {
  public int[] arr;
  int size;

  public MyHeap(int capacity) {
    arr = new int[capacity + 1];
  }

  public void heapify(int[] array) {
    arr = new int[array.length + 1];
    for (int elem : array) {
      this.insert(elem);
    }
  }

  public void insert(int elem) {
    arr[++size] = elem;
    for (int i = size, parent = size / 2; i > 0 && parent > 0; i = i / 2, parent = parent / 2) {
      /* This is similar to bubble sort */
      if (arr[i] > arr[parent]) {
        int tmp = arr[i];
        arr[i] = arr[parent];
        arr[parent] = tmp;
      }
    }
  }

  public int popMin() {
    int min = arr[1];
    arr[1] = arr[size];
    arr[size--] = 0;
    bubbleDown(1);
    return min;
  }

  private void bubbleDown(int rootIndex) {
    if (rootIndex * 2 > size) {
      return;
    }
    int left = arr[rootIndex * 2];
    int right = arr[rootIndex * 2 + 1];
    if (arr[rootIndex] < left || arr[rootIndex] < right) {
      int largerIndex = left > right ? rootIndex * 2 : rootIndex * 2 + 1;
      int tmp = arr[rootIndex];
      arr[rootIndex] = arr[largerIndex];
      arr[largerIndex] = tmp;
      bubbleDown(largerIndex);
    }
    return;
  }
}

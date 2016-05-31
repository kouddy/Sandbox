package algo.sorting;

import datastructure.Node;

public class SelectionSort {
  public static int[] sortAscending(int[] array) {
    for (int i = 0; i < array.length; i++) {
      int minIndex = i;
      for (int j = i; j < array.length; j++) {
        if (array[j] < array[minIndex]) {
          minIndex = j;
        }
      }
      int tmp = array[minIndex];
      array[minIndex] = array[i];
      array[i] = tmp;
    }
    return array;
  }

  public static Node sortListAscending(Node head) {
    for (Node i = head; i != null; i = i.next) {
      Node min = i;
      for (Node j = i; j != null; j = j.next) {
        if (j.value < min.value) {
          min = j;
        }
      }
      int tmp = min.value;
      min.value = i.value;
      i.value = tmp;
    }
    return head;
  }
}

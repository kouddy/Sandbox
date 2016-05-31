package algo.sorting;

import datastructure.Node;

public class BubbleSort {
  public static int[] sortAscending(int[] array) {
    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array.length - 1; j++) {
        if (array[j] > array[j + 1]) {
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
    return array;
  }

  public static Node sortListAscending(Node head) {
    for (Node i = head; i != null; i = i.next) {
      for (Node j = head; j.next != null; j = j.next) {
        if (j.value > j.next.value) {
          int tmp = j.value;
          j.value = j.next.value;
          j.next.value = tmp;
        }
      }
    }
    return head;
  }
}

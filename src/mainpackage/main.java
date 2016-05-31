package mainpackage;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import algo.Searching;
import algo.sorting.QuickSort;
import datastructure.MyHeap;

public class main {
  public static void main(String[] args) {

    int[] arr = { 35, 33, 42, 10, 14, 19, 27, 44, 26, 31 };
    MyHeap h = new MyHeap(arr.length);
    h.heapify(arr);
    int min = h.popMin();

    System.out.println(Arrays
        .stream(QuickSort.quickSort(new int[] { 1, 2, 3, 4, 5, 6 }))
        .mapToObj(v -> String.valueOf(v)).collect(Collectors.joining(",")));

    System.out.println(Searching.binarySearchRecursive(IntStream.range(0, 16)
        .toArray(), 4));
    System.out.println(Searching.binarySearchRecursive(IntStream.range(0, 2)
        .toArray(), 0));
    System.out.println(Searching.binarySearchRecursive(IntStream.range(0, 2)
        .toArray(), 1));
  }
}
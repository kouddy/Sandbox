package mainpackage;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class main {
  public static void main(String[] args) {
    Stream sa = Stream.of(1, 2, 3);
    Stream sb = Stream.concat(sa, Stream.empty());
    sa.count();
    sb.count();

    System.out.println(Arrays.stream(merge(null, new int[] { 1 })).boxed()
        .map(v -> String.valueOf(v)).collect(Collectors.joining(",")));
    System.out.println(Arrays.stream(merge(new int[] { 1 }, null)).boxed()
        .map(v -> String.valueOf(v)).collect(Collectors.joining(",")));
    System.out.println(Arrays
        .stream(merge(new int[] { 1, 2, 5, 6 }, new int[] { 3, 4 })).boxed()
        .map(v -> String.valueOf(v)).collect(Collectors.joining(",")));
    System.out.println(Arrays.stream(merge(new int[] { 1 }, new int[] { 2 }))
        .boxed().map(v -> String.valueOf(v)).collect(Collectors.joining(",")));
    System.out.println(Arrays.stream(merge(new int[] { 2 }, new int[] { 1 }))
        .boxed().map(v -> String.valueOf(v)).collect(Collectors.joining(",")));
    System.out.println(Arrays
        .stream(merge(new int[] { 1 }, new int[] { 2, 3, 4 })).boxed()
        .map(v -> String.valueOf(v)).collect(Collectors.joining(",")));

    System.out.println(Arrays.stream(mergeSort(new int[] {})).boxed()
        .map(v -> String.valueOf(v)).collect(Collectors.joining(",")));

    "a".toString();
  }

  public static int[] mergeSort(int[] arr) {
    if (arr.length <= 1) {
      return arr;
    }
    int mid = arr.length / 2;
    return merge(mergeSort(Arrays.copyOfRange(arr, 0, mid)),
        mergeSort(Arrays.copyOfRange(arr, mid, arr.length)));
  }

  public static int[] merge(int[] a, int[] b) {
    if (a == null)
      return b;
    if (b == null)
      return a;
    int ai = 0;
    int bi = 0;
    int ci = 0;
    int[] c = new int[a.length + b.length];
    while (ai < a.length && bi < b.length) {
      if (a[ai] < b[bi]) {
        c[ci++] = a[ai++];
      } else {
        c[ci++] = b[bi++];
      }
    }

    while (ai < a.length) {
      c[ci++] = a[ai++];
    }
    while (bi < b.length) {
      c[ci++] = b[bi++];
    }
    return c;
  }
}
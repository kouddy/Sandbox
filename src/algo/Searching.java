package algo;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class Searching {
  /*
   * Basic
   */
  public static int binarySearch(int[] arr, int elem) {
    int low = 0;
    int high = arr.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (elem < arr[mid]) {
        high = mid - 1;
      }
      if (elem > arr[mid]) {
        low = mid + 1;
      }
      if (elem == arr[mid]) {
        return mid;
      }
    }
    return -1;
  }

  public static int binarySearchRecursive(int[] array, int elem) {
    return binarySearchRec(array, elem, 0, array.length);
  }

  private static int binarySearchRec(int[] array, int elem, int low, int highExc) {
    if (low < highExc) {
      int mid = low + (highExc - low) / 2;
      if (elem < array[mid]) {
        return binarySearchRec(array, elem, low, mid);
      }
      if (array[mid] < elem) {
        return binarySearchRec(array, elem, mid + 1, highExc);
      }
      if (elem == array[mid]) {
        return mid;
      }
    }
    return -1;
  }

  /*
   * Applications
   */
  public static double numericalBinarySearch(double n,
      Function<Double, Double> p, double low, double high) {
    double mid = low + (high - low) / 2;
    if (Math.abs(p.apply(mid) - n) / n < 0.01) {
      return mid;
    }
    if (p.apply(mid) > n) {
      return numericalBinarySearch(n, p, low, mid);
    } else {
      return numericalBinarySearch(n, p, mid, high);
    }
  }

  public static int binarySearch(int[] arr, BiPredicate<Integer, Integer> p,
      int low, int highExc) {
    if (low < highExc) {
      int mid = low + (highExc - low) / 2;
      if (p.test(mid, arr[mid])) {
        return binarySearch(arr, p, low, mid);
      } else {
        return binarySearch(arr, p, mid + 1, highExc);
      }
    }
    return (low < arr.length && p.test(low, arr[low])) ? low : -1;
  }

  /**
   * Searches rotated array
   */
  public static int rotatedK(int[] arr, int low, int highExc) {
    int mid = low + (highExc - low) / 2;
    if (arr[low] == arr[mid] && arr[mid] == arr[highExc - 1]) {
      return mid;
    }
    if (arr[mid] < arr[highExc - 1]) {
      return rotatedK(arr, low, mid);
    } else {
      return rotatedK(arr, mid, highExc);
    }
  }

  public static int binarySearchRotated(int[] array, int elem) {
    return binarySearchRot(array, elem, 0, array.length);
  }

  private static int binarySearchRot(int[] array, int elem, int low, int high) {
    if (low < high) {
      int mid = (low + high) / 2;
      if (array[mid] == elem) {
        return mid;
      }
      if (array[mid] <= elem && elem <= array[high - 1]) {
        return binarySearchRot(array, elem, mid, high);
      } else {
        return binarySearchRot(array, elem, low, mid);
      }
    }

    return -1;
  }

  /* find number of occurrence of elem in arr. */
  public static int occurence(int[] arr, int elem) {
    int first = binarySearch(arr, (index, v) -> v >= elem, 0, arr.length);
    int last = binarySearch(arr, (index, v) -> v > elem, 0, arr.length) >= 0 ? binarySearch(
        arr, (index, v) -> v > elem, 0, arr.length) : arr.length;
    return last - first;
  }

  /*
   * Given A a sorted array with distinct numbers, find out an i such that A[i]
   * == i.
   */
  public static int test(int[] arr, int low, int highExc) {
    if (low < highExc) {
      int mid = low + (highExc - low) / 2;
      if (arr[mid] == mid) {
        return mid;
      }
      if (arr[mid] > mid) {
        return test(arr, mid + 1, highExc);
      }
      if (arr[mid] < mid) {
        return test(arr, low, mid);
      }
    }
    return -1;
  }

  /*
   * Given an array of distinct numbers A such that A[0] > A[1] and A[n-1] >
   * A[n-2] find out a local minimum (find out an i such that A[i-1] > A[i] <
   * A[i + 1]).
   */
  public static int localMinimum(int[] arr, int low, int high) {
    if (low <= high) {
      int mid = low + (high - low) / 2;
      if (mid == 0 && arr[mid] < arr[mid + 1]) {
        return mid;
      }
      if (mid == arr.length - 1 && arr[mid - 1] > arr[mid]) {
        return mid;
      }
      if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
        return mid;
      }
      if (arr[mid - 1] > arr[mid]) {
        return localMinimum(arr, mid, high);
      }
      if (arr[mid] < arr[mid + 1]) {
        return localMinimum(arr, low, mid);
      }
    }
    return -1;
  }

  /*
   * Given A, an array comprised of an increasing sequence of numbers followed
   * immediately by a decreasing one. Determine if a given number x is in the
   * array
   */
  public static int test2(int[] arr, int elem, int low, int high) {
    if (low < high) {
      int a = low + (high - low) / 4;
      int b = low + (high - low) * 3 / 4;
      if (elem == arr[a]) {
        return a;
      }
      if (elem == arr[b]) {
        return b;
      }
      int retVal = -1;
      if (elem < arr[a]) {
        retVal = binarySearchRec(arr, elem, low, a);
        if (retVal >= 0) {
          return retVal;
        }
      }
      if (arr[b] > elem) {
        retVal = binarySearchRec(arr, elem, b + 1, high);
        if (retVal >= 0) {
          return retVal;
        }
      }
      return test2(arr, elem, a + 1, b);
    }
    return -1;
  }

  public static double sqrt(double n) {
    Function<Double, Double> p = v -> v * v;
    return numericalBinarySearch(n, p, 0.0d, n);
  }

  public static double log2(double n) {
    Function<Double, Double> p = v -> Math.pow(2, v);
    return numericalBinarySearch(n, p, 0.0d, n);
  }
}

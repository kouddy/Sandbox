package algo;

public class Searching {
  /**
   * Fast algorithm to find square root of n given square root of n is a integer
   * 
   * @param n
   * @return
   */
  public static int sqrt(int n) {
    return _sqrt(n, 1, n / 2);
  }

  private static int _sqrt(int n, int low, int high) {
    int mid = (low + high) / 2;
    int tmp = mid * mid;
    if ((low == mid) || (high == mid)) {
      return -1;
    }

    if (tmp == n) {
      return mid;
    } else if (tmp > n) {
      return _sqrt(n, low, mid);
    } else {
      return _sqrt(n, mid, high);
    }
  }

  /**
   * Perform binary search in an array.
   * 
   * @return -1 if element is not found in the array.
   */
  public static int binarySearchRecursive(int[] array, int elem) {
    return binarySearchRec(array, elem, 0, array.length);
  }

  private static int binarySearchRec(int[] array, int elem, int low, int high) {
    int mid = (low + high) / 2;

    if (mid >= array.length || mid < 0) {
      return -1;
    }

    if (array[mid] == elem) {
      return mid;
    } else {
      if ((mid == high) && (mid == low)) {
        return -1;
      } else {
        if (array[mid] < elem) {
          return binarySearchRec(array, elem, mid + 1, high);
        } else {
          return binarySearchRec(array, elem, low, mid - 1);
        }
      }
    }
  }

  /**
   * Searches rotated array
   * 
   * @return -1 if element is not found
   */
  public static int binarySearchRotated(int[] array, int elem) {
    return binarySearchRot(array, elem, 0, array.length);
  }

  private static int binarySearchRot(int[] array, int elem, int low, int high) {
    int mid = (low + high) / 2;

    if (mid >= array.length || mid < 0) {
      return -1;
    }

    if (array[mid] == elem) {
      return mid;
    } else {
      if ((mid == high) && (mid == low)) {
        return -1;
      } else {
        if ((array[mid] < elem) && (array[high] >= elem)) {
          return binarySearchRot(array, elem, mid + 1, high);
        } else {
          return binarySearchRot(array, elem, low, mid - 1);
        }
      }
    }
  }
}

package algo;

public class Query {
  /**
   * Perform Filtering for common elements when arr1.length << arr2.length;
   * 
   * @return Common elements.
   */
  public static int[] getDocumentsBinarySearch(int[] arr1, int[] arr2) {
    int[] ret = new int[arr1.length + arr2.length];
    int reti = 0;
    for (int i = 0; i < arr1.length; i++) {
      int indexInArr2 = Searching.binarySearchRecursive(arr2, arr1[i]);
      if (-1 != indexInArr2) {
        ret[reti++] = arr1[i];
      }
    }
    return ret;
  }

  /**
   * Perform Filtering for common elements when arr1.length == arr2.length;
   * 
   * @return Common elements.
   */
  public static int[] getDocumentsMerge(int[] arr1, int[] arr2) {
    int arr1i = 0;
    int arr2i = 0;
    int[] ret = new int[arr1.length + arr2.length];
    int reti = 0;
    while (arr1i != arr1.length && arr2i != arr2.length) {
      if (arr1[arr1i] < arr2[arr2i]) {
        arr1i++;
      } else if (arr1[arr1i] > arr2[arr2i]) {
        arr2i++;
      } else {
        ret[reti++] = arr1[arr1i++];
        arr2i++;
      }
    }
    return ret;
  }
}

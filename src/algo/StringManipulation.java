package algo;

public class StringManipulation {
  public static boolean isUnique(String str) {
    boolean[] t = new boolean[256];
    for (int i = 0; i < str.length(); i++) {
      if (t[str.charAt(i)] == false) {
        t[str.charAt(i)] = true;
      } else {
        return false;
      }
    }
    return true;
  }

  public static boolean isUniqueNN(String str) {
    for (int i = 0; i < str.length() - 1; i++) {
      char comp = str.charAt(i);
      for (int j = i + 1; j < str.length(); j++) {
        if (comp == str.charAt(j)) {
          return false;
        }
      }
    }
    return true;
  }

  public static char[] reverse1(char[] array) {
    char[] ret = new char[array.length];
    for (int i = 0; i < array.length - 1; i++) {
      ret[i] = array[array.length - i - 2];
    }
    ret[array.length - 1] = array[array.length - 1];
    return ret;
  }

  public static void reverse2(char[] array) {
    int i = 0;
    int j = array.length - 2;
    while (i <= j) {
      swap(array, i++, j--);
    }
  }

  public static boolean isAnagram(char[] a, char[] b) {
    int[] tableA = new int[128];
    int[] tableB = new int[128];

    for (int i = 0; i < a.length; i++) {
      tableA[a[i]] = tableA[a[i]] + 1;
    }

    for (int i = 0; i < b.length; i++) {
      tableB[b[i]] = tableB[b[i]] + 1;
    }

    for (int i = 0; i < tableA.length; i++) {
      if (tableA[i] != tableB[i]) {
        return false;
      }
    }

    return true;
  }

  public static char[] spaceToPer20(char[] array) {
    char[] ret = new char[array.length + countSpaces(array) * 2];

    for (int i = 0, retIndex = 0; i < array.length; i++, retIndex++) {
      if (array[i] == ' ') {
        ret[retIndex] = '%';
        retIndex++;
        ret[retIndex] = '2';
        retIndex++;
        ret[retIndex] = '0';
      } else {
        ret[retIndex] = array[i];
      }
    }
    return ret;
  }

  public static int[][] rotate90(int[][] array, int n) {
    int[][] ret = new int[n][n];

    for (int j = 0; j < n; j++) {
      for (int i = 0; i < n; i++) {
        ret[j][i] = array[i][n - 1 - j];
      }
    }
    return ret;
  }

  public static void rotate90Inplace(int[][] array, int n) {
    for (int i = 0; i < n; i++) {
      int j = 0;
      int k = n - 1;
      // Swap each columns
      while (k > j) {
        swap2Dhorizontally(array, i, j, k);
        j++;
        k--;
      }
    }

    // Swap diagonally
    for (int i = 0; i < n; i++) {
      int j = i;
      int k = 0;
      while (j > k) {
        swap2Ddiagnolally(array, j, k);
        j--;
        k++;
      }
    }

    for (int i = 1; i < n; i++) {
      int j = i;
      int k = n - 1;
      while (k > j) {
        swap2Ddiagnolally(array, j, k);
        j++;
        k--;
      }
    }

  }

  public static void swap2Dhorizontally(int[][] array, int i, int j, int k) {
    int tmp = array[i][k];
    array[i][k] = array[i][j];
    array[i][j] = tmp;
  }

  public static void swap2Ddiagnolally(int[][] array, int i, int j) {
    int tmp = array[j][i];
    array[j][i] = array[i][j];
    array[i][j] = tmp;
  }

  public static int countSpaces(char[] array) {
    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if (array[i] == ' ') {
        count++;
      }
    }
    return count;
  }

  public static void swap(char[] array, int i, int j) {
    char tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}

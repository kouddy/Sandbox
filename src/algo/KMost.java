package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KMost {
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Map<String, Integer> frequency = new HashMap<String, Integer>();
    String word;

    int N = Integer.parseInt(br.readLine());

    // Put words in hashmap.
    for (int i = 0; i < N; i++) {
      word = br.readLine();
      Integer val = frequency.get(word);
      if (null == val) {
        frequency.put(word, Integer.valueOf(1));
      } else {
        frequency.put(word, Integer.valueOf(val.intValue() + 1));
      }
    }

    int k = Integer.parseInt(br.readLine());
    br.close();
    String[] keys = frequency.keySet().toArray(new String[0]);
    Integer[] vals = frequency.values().toArray(new Integer[0]);

    // Sort each word according to their frequency using selection sort.
    sortAccordingToValue(keys, vals);

    int start = 0;
    int end = 0;
    while (end != vals.length) {
      if (vals[start].intValue() == vals[end].intValue()) {
        end++;
      } else {
        sortLexicographicalOrder(keys, start, end - 1);
        start = end;
      }
    }
    sortLexicographicalOrder(keys, start, end - 1);

    // Print k most frequent word.
    for (int i = 0; i < k; i++) {
      System.out.println(keys[i]);
    }

    vals = null;
    keys = null;
    frequency = null;
    br = null;
  }

  /** Sort keys array and vals array at the same time. **/
  public static void sortAccordingToValue(String[] keys, Integer[] vals) {
    for (int i = 0; i < vals.length; i++) {
      int max = i;
      for (int j = i; j < vals.length; j++) {
        if (vals[j].intValue() > vals[max].intValue()) {
          max = j;
        }
      }
      swap(vals, i, max);
      swap(keys, i, max);
    }
  }

  /** Sort keys according to lexicographical order given start and end index. **/
  public static void sortLexicographicalOrder(String[] keys, int start, int end) {
    for (int i = start; i <= end; i++) {
      int min = i;
      for (int j = i; j <= end; j++) {
        if (keys[j].compareTo(keys[min]) < 0) {
          min = j;
        }
      }
      swap(keys, i, min);
    }
  }

  /** Swap two elements in an array given their indexes **/
  public static void swap(Object[] array, int i, int j) {
    Object tmp = array[i];
    array[i] = array[j];
    array[j] = tmp;
  }
}
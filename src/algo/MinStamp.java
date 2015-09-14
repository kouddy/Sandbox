package algo;

import util.Profiling;

public class MinStamp {

  public static void main(String[] args) throws Exception {
    int[] array = { 3, 2, 1 };

    assert minStampBottomUp(array, 1) == 1 : "False";
    assert minStampBottomUp(array, 2) == 1 : "False";
    assert minStampBottomUp(array, 3) == 1 : "False";
    assert minStampBottomUp(array, 4) == 2 : "False";
    assert minStampBottomUp(array, 5) == 2 : "False";
    assert minStampBottomUp(array, 6) == 2 : "False";
    assert minStampBottomUp(array, 7) == 3 : "False";
    assert minStampBottomUp(array, 8) == 3 : "False";
    assert minStampBottomUp(array, 9) == 3 : "False";
    assert minStampBottomUp(array, 10) == 4 : "False";
    assert minStampBottomUp(array, 11) == 4 : "False";
    assert minStampBottomUp(array, 12) == 4 : "False";

    Profiling profiler = new Profiling();

    profiler.start();
    System.out.println(minStampBottomUp(array, 1000000));
    profiler.end();
    profiler.print();

    profiler.start();
    minStampKai(array, 34);
    profiler.end();
    profiler.print();

    profiler.start();
    minStamp(array, 34);
    profiler.end();
    profiler.print();
  }

  public static int minStampBottomUp(int[] a, int value) {
    int[] computed = new int[value + 1];

    for (int i = 1; i <= value; i++) {
      int q = Integer.MAX_VALUE;
      for (int j = 0; j < a.length; j++) {
        if ((i - a[j]) >= 0) {
          q = Math.min(q, 1 + computed[i - a[j]]);
        }
      }
      computed[i] = q;
    }
    return computed[value];
  }

  public static int minStampKai(int[] a, int value) {
    int[] computed = new int[value + 1];
    return minStampAux(a, value, computed);
  }

  private static int minStampAux(int[] a, int value, int[] computed) {
    if (computed[value] > 0) {
      return computed[value];
    }

    if (value == 0) {
      return 0;
    }

    int q = Integer.MAX_VALUE;
    for (int i = 0; i < a.length; i++) {
      if ((value - a[i]) >= 0) {
        q = Math.min(q, 1 + minStampKai(a, value - a[i]));
      }
    }
    computed[value] = q;
    return q;
  }

  public static int minStamp(int[] a, int value) {
    if (value <= 0) {
      return 0;
    }

    int curMin = Integer.MAX_VALUE;
    for (int i = 0; i < a.length; i++) {
      if ((value - a[i]) >= 0) {
        curMin = Math.min(curMin, 1 + minStamp(a, value - a[i]));
      }
    }
    return curMin;
  }
}
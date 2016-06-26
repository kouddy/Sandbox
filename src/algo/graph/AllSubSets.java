package algo.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllSubSets {
  public static void test() {
    int[] a = IntStream.rangeClosed(1, 5).toArray();
    allSubSetsDFS(a);
    System.out.println();
    allSubSetsBFS(a);
    "done".toString();
  }

  public static void allSubSetsDFS(int[] a) {
    allSubSetsDFS(a, 0, new ArrayList<>());
  }

  public static void allSubSetsDFS(int[] a, int i, List<Integer> curSubSet) {
    if (i >= a.length) {
      System.out.print(" " + curSubSet.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(",", "[", "]")));
    } else {
      allSubSetsDFS(a, i + 1, curSubSet);
      curSubSet.add(a[i]);
      allSubSetsDFS(a, i + 1, curSubSet);
      curSubSet.remove(curSubSet.size() - 1);
    }
  }

  public static void allSubSetsBFS(int[] a) {
    List<List<Integer>> s = new ArrayList<>();
    s.add(new ArrayList<>());
    allSubSetsBFS(a, 0, s);
  }

  public static void allSubSetsBFS(int[] a, int i, List<List<Integer>> subSets) {
    if (i >= a.length) {
      System.out.println(subSets.stream().map(l -> l.stream().map(v -> String.valueOf(v)).collect(Collectors.joining(",", "[", "]"))).collect(Collectors.joining(",")));
    } else {
      List<List<Integer>> s = new ArrayList<>();
      for (List<Integer> sub : subSets) {
        List<Integer> copy = sub.stream().collect(Collectors.toList());
        copy.add(a[i]);
        s.add(copy);
      }
      subSets.addAll(s);
      allSubSetsBFS(a, i + 1, subSets);
    }
  }
}

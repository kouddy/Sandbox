package algo.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

public class FloodFill {
  public static void test() {
    int[][] colorBoard = { { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 } };
    fillColorDFS(colorBoard, 2, 2, 0, 3);
    "done".toString();

    int[][] colorBoard2 = { { 1, 1, 0, 1 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 0, 1 }, { 0, 1, 0, 0 } };
    fillColorBFS(colorBoard2, 2, 2, 0, 3);
    "done".toString();
  }

  public static void fillColorDFS(int[][] arr, int x, int y, int source, int target) {
    if (x >= 0 && y >= 0 && x < arr.length && y < arr[x].length && arr[x][y] == source) {
      arr[x][y] = target;
      fillColorDFS(arr, x - 1, y, source, target);
      fillColorDFS(arr, x, y - 1, source, target);
      fillColorDFS(arr, x + 1, y, source, target);
      fillColorDFS(arr, x, y + 1, source, target);
    }
  }

  public static void fillColorBFS(int[][] arr, int x, int y, int source, int target) {
    Queue<Entry<Integer, Integer>> q = new LinkedList<>();
    q.add(new SimpleEntry<>(x, y));
    while (!q.isEmpty()) {
      Entry<Integer, Integer> e = q.remove();
      int i = e.getKey();
      int j = e.getValue();
      if (i >= 0 && j >= 0 && i < arr.length && j < arr[i].length && arr[i][j] == source) {
        arr[i][j] = target;
        q.add(new SimpleEntry<Integer, Integer>(i - 1, j));
        q.add(new SimpleEntry<Integer, Integer>(i, j - 1));
        q.add(new SimpleEntry<Integer, Integer>(i + 1, j));
        q.add(new SimpleEntry<Integer, Integer>(i, j + 1));
      }
    }
  }
}

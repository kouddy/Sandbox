package algo.graph;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;

public class SearchingApplications {
  public void test() {
    boolean[][] arr = { { true, false, true, true, true }, { true, true, true, false, true }, { false, false, false, false, true }, { true, true, true, true, true },
        { true, true, true, true, true } };
    maze(arr, new SimpleEntry<>(0, 0), new SimpleEntry<>(4, 0));
  }

  public static int maze(boolean[][] arr, Entry<Integer, Integer> start, Entry<Integer, Integer> end) {
    int[][] minSteps = new int[arr.length][arr.length];
    boolean[][] visited = new boolean[arr.length][arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        minSteps[i][j] = Integer.MAX_VALUE;
        visited[i][j] = !arr[i][j];
      }
    }

    Queue<Entry<Integer, Integer>> q = new LinkedList<>();
    q.add(start);
    minSteps[start.getKey()][start.getValue()] = 0;
    while (!q.isEmpty()) {
      Entry<Integer, Integer> elem = q.remove();
      int x = elem.getKey();
      int y = elem.getValue();
      visited[x][y] = true;
      if (!(x == end.getKey() && y == end.getValue())) {
        // top
        if (x - 1 >= 0 && !visited[x - 1][y] && minSteps[x][y] + 1 < minSteps[x - 1][y]) {
          minSteps[x - 1][y] = minSteps[x][y] + 1;
        }
        // left
        if (y - 1 >= 0 && !visited[x][y - 1] && minSteps[x][y] + 1 < minSteps[x][y - 1]) {
          minSteps[x][y - 1] = minSteps[x][y] + 1;
        }
        // right
        if (y + 1 < arr[x].length && !visited[x][y + 1] && minSteps[x][y] + 1 < minSteps[x][y + 1]) {
          minSteps[x][y + 1] = minSteps[x][y] + 1;
        }
        // down
        if (x + 1 < arr.length && !visited[x + 1][y] && minSteps[x][y] + 1 < minSteps[x + 1][y]) {
          minSteps[x + 1][y] = minSteps[x][y] + 1;
        }
        int minX = -1;
        int minY = -1;
        int minStep = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
          for (int j = 0; j < arr[i].length; j++) {
            if (!visited[i][j] && minSteps[i][j] < minStep) {
              minX = i;
              minY = j;
              minStep = minSteps[i][j];
            }
          }
        }
        if (minX != -1) {
          q.add(new AbstractMap.SimpleEntry<>(minX, minY));
        }
      }
    }
    return minSteps[end.getKey()][end.getValue()];
  }
}

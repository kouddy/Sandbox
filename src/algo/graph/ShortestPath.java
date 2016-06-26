package algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.stream.IntStream;

public class ShortestPath {
  public static void test() {
    WeightedGraph g = new WeightedGraph(5, true);
    g.addEdge(0, 1, 2);
    g.addEdge(0, 3, 5);
    g.addEdge(1, 2, 2);
    g.addEdge(2, 3, 1);
    g.addEdge(2, 4, 200);

    WeightedGraph g2 = new WeightedGraph(5, true);
    g2.addEdge(0, 1, 5);
    g2.addEdge(0, 2, 10);
    g2.addEdge(1, 2, 4);
    g2.addEdge(1, 3, 1);
    g2.addEdge(1, 4, 9);
    g2.addEdge(2, 3, 2);
    g2.addEdge(3, 4, 1);
    dijkstraShortestPath(g, 0, 4, Optional.ofNullable(null));
    dijkstraShortestPath(g2, 0, 4, Optional.ofNullable(null));
    bellmanFordShortestPath(g, 0, 4);
    bellmanFordShortestPath(g2, 0, 4);
  }

  public static List<Edge> dijkstraShortestPath(WeightedGraph g, int source, int target, Optional<BiPredicate<Integer, Entry<Integer, Integer>>> shouldVisit) {
    boolean[] visited = new boolean[g.numV];
    int[] distance = IntStream.range(0, g.numV).map(i -> Integer.MAX_VALUE).toArray();
    int[] parent = IntStream.range(0, g.numV).map(i -> -1).toArray();
    Queue<Integer> q = new LinkedList<>();
    q.add(source);
    distance[source] = 0;
    BiPredicate<Integer, Entry<Integer, Integer>> shouldVisitFun = shouldVisit.orElse((a, b) -> true);
    while (!q.isEmpty()) {
      int elem = q.remove();
      visited[elem] = true;
      if (elem != target) {
        for (Entry<Integer, Integer> edge : g.adj.get(elem)) {
          if (shouldVisitFun.test(elem, edge) && !visited[edge.getKey()] && distance[elem] + edge.getValue() < distance[edge.getKey()]) {
            distance[edge.getKey()] = distance[elem] + edge.getValue();
            parent[edge.getKey()] = elem;
          }
        }
        int minDistanceIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < g.numV; i++) {
          if (!visited[i] && distance[i] < minDistance) {
            minDistance = distance[i];
            minDistanceIndex = i;
          }
        }
        if (minDistanceIndex != -1) {
          q.add(minDistanceIndex);
        }
      }
    }

    /* When source and target are disconnected, return null. */
    if (parent[target] == -1) {
      return null;
    }
    /* Generate path. */
    List<Edge> edges = new ArrayList<>();
    for (int i = target; i != 0; i = parent[i]) {
      edges.add(new Edge(parent[i], i, distance[i] - distance[parent[i]]));
    }
    Collections.reverse(edges);
    return edges;

    /* We can also generate tree. */
    // WeightedGraph mst = new WeightedGraph(g.numV, true);
    // for (int i = target; i != 0; i = parents[i]) {
    // mst.addEdge(parents[i], i, arr[iterationNum - 1][i] - arr[iterationNum - 1][parents[i]]);
    // }
    // return mst;
  }

  public static WeightedGraph bellmanFordShortestPath(WeightedGraph g, int source, int target) {
    int[][] arr = new int[g.numV - 1][g.numV];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        arr[i][j] = Integer.MAX_VALUE;
      }
    }
    int[] parents = IntStream.range(0, g.numV).map(i -> -1).toArray();
    arr[0][source] = 0;
    boolean hasChange = true;
    int iterationNum = 0;
    for (; iterationNum < arr.length && hasChange; iterationNum++) {
      hasChange = false;
      for (int j = 0; j < g.numV; j++) {
        if (iterationNum > 0) {
          arr[iterationNum][j] = arr[iterationNum - 1][j];
        }
        for (Entry<Integer, Integer> edge : g.adj.get(j)) {
          if (arr[iterationNum][j] + edge.getValue() < arr[iterationNum][edge.getKey()]) {
            arr[iterationNum][edge.getKey()] = arr[iterationNum][j] + edge.getValue();
            parents[edge.getKey()] = j;
            hasChange = true;
          }
        }
      }
    }

    WeightedGraph mst = new WeightedGraph(g.numV, true);
    for (int i = target; parents[i] != -1; i = parents[i]) {
      mst.addEdge(parents[i], i, arr[iterationNum - 1][i] - arr[iterationNum - 1][parents[i]]);
    }
    return mst;
  }
}

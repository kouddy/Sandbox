package algo.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MinimumSpanningTree {
  public static void test() {
    WeightedGraph g = new WeightedGraph(5, false);
    g.addEdge(0, 1, 2);
    g.addEdge(0, 3, 5);
    g.addEdge(1, 2, 2);
    g.addEdge(2, 3, 1);
    g.addEdge(2, 4, 200);

    WeightedGraph g2 = new WeightedGraph(5, false);
    g2.addEdge(0, 1, 5);
    g2.addEdge(0, 2, 10);
    g2.addEdge(1, 2, 4);
    g2.addEdge(1, 3, 1);
    g2.addEdge(1, 4, 9);
    g2.addEdge(2, 3, 2);
    g2.addEdge(3, 4, 1);

    primMST(g, 0);
    primMST_2(g, 0);
    kruskalMST(g);

    primMST(g2, 0);
    primMST_2(g2, 0);
    kruskalMST(g2);
  }

  public static WeightedGraph primMST(WeightedGraph g, int root) {
    Set<Integer> covered = new HashSet<>();
    List<Edge> tree = new ArrayList<>();
    covered.add(root);
    while (covered.size() < g.numV) {
      Edge ed = new Edge(-1, -1, Integer.MAX_VALUE);
      for (int v : covered) {
        for (Entry<Integer, Integer> e : g.adj.get(v)) {
          if (!covered.contains(e.getKey()) && e.getValue() < ed.weight) {
            ed = new Edge(v, e.getKey(), e.getValue());
          }
        }
      }
      covered.add(ed.to);
      tree.add(ed);
    }
    WeightedGraph mst = new WeightedGraph(g.numV, true);
    for (Edge e : tree) {
      mst.addEdge(e.from, e.to, e.weight);
    }
    return mst;
  }

  public static WeightedGraph primMST_2(WeightedGraph g, int root) {
    boolean[] visited = new boolean[g.numV];
    int[] distance = IntStream.range(0, g.numV).map(i -> Integer.MAX_VALUE).toArray();
    int[] parents = IntStream.range(0, g.numV).map(i -> -1).toArray();
    Queue<Integer> q = new LinkedList<>();
    q.add(root);
    distance[root] = 0;
    while (!q.isEmpty()) {
      int elem = q.remove();
      visited[elem] = true;
      /* Updated children. */
      for (Entry<Integer, Integer> edge : g.adj.get(elem)) {
        if (!visited[edge.getKey()] && edge.getValue() < distance[edge.getKey()]) {
          distance[edge.getKey()] = edge.getValue();
          parents[edge.getKey()] = elem;
        }
      }
      /* Find minimum child which this tree can grow. We can use heap to make performance of this faster. */
      int minChild = -1;
      int minWeight = Integer.MAX_VALUE;
      for (int i = 0; i < g.numV; i++) {
        if (!visited[i] && distance[i] < minWeight && visited[parents[i]]) {
          minChild = i;
          minWeight = distance[i];
        }
      }
      if (minChild != -1) {
        q.add(minChild);
      }
    }

    /* Generate tree. */
    WeightedGraph mst = new WeightedGraph(g.numV, true);
    for (int i = 0; i < g.numV; i++) {
      if (parents[i] != -1) {
        mst.addEdge(parents[i], i, distance[i]);
      }
    }
    return mst;
  }

  public static WeightedGraph kruskalMST(WeightedGraph g) {
    List<Edge> edges = IntStream.range(0, g.numV).boxed().flatMap(i -> g.adj.get(i).stream().map(e -> new Edge(i, e.getKey(), e.getValue()))).sorted((a, b) -> a.weight - b.weight)
        .collect(Collectors.toList());
    List<Edge> tree = new ArrayList<>();
    List<Set<Integer>> covered = IntStream.range(0, g.numV).mapToObj(i -> {
      Set<Integer> t = new HashSet<>();
      t.add(i);
      return t;
    }).collect(Collectors.toList());
    int pickedEdgeIndex = 0;

    while (tree.size() < g.numV - 1) {
      Edge e = edges.get(pickedEdgeIndex);
      Set<Integer> fromSet = covered.stream().filter(s -> s.contains(e.from)).findFirst().get();
      Set<Integer> toSet = covered.stream().filter(s -> s.contains(e.to)).findFirst().get();
      if (fromSet != toSet) {
        tree.add(e);
        fromSet.addAll(toSet);
        covered.remove(toSet);
      }
      pickedEdgeIndex++;
    }

    /* Generate tree. */
    WeightedGraph mst = new WeightedGraph(g.numV, true);
    for (Edge e : tree) {
      mst.addEdge(e.from, e.to, e.weight);
    }
    return mst;
  }
}

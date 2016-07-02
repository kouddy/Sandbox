package algo.graph;

import java.util.Optional;
import java.util.stream.IntStream;

public class HasTriangle {
  public static void test() {
    Graph g = new Graph(3, false);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0);
    System.out.println(hasTriangle(g, 0));
  }

  public static boolean hasTriangle(Graph g, int root) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    boolean[] hasTriangle = new boolean[g.numV];
    DFS.traverse(g, 0, state, parents, entry, exit, 0, Optional.ofNullable(null), Optional.ofNullable(null), Optional.of((x, y) -> {
      if (state[y] != TraverseState.UNDISCOVERED && parents[x] >= 0 && parents[parents[x]] >= 0 && y == parents[parents[x]]) {
        hasTriangle[y] = true;
      }
    }));

    for (int i = 0; i < hasTriangle.length; i++) {
      if (hasTriangle[i]) {
        return true;
      }
    }
    return false;
  }
}

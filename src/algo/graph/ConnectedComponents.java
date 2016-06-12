package algo.graph;

import java.util.Optional;
import java.util.stream.IntStream;

public class ConnectedComponents {
  public static void test() {
    Graph g = new Graph(7, false);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(2, 4);
    g.addEdge(5, 6);

    run(g);
  }

  public static void run(Graph g) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    for (int i = 0; i < g.numV; i++) {
      if (state[i] == TraverseState.UNDISCOVERED) {
        int root = i;
        BFS.traverse(g, i, state, parents, Optional.of(node -> {
          System.out.println(node + " is in " + root);
        }), Optional.ofNullable(null), Optional.ofNullable(null));
      }
    }
  }
}

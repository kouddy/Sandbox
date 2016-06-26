package algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TopologicalSort {

  public static void test() {
    Graph g = new Graph(5, true);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(3, 2);
    g.addEdge(4, 3);
    System.out.println(topologicalSort(g).stream().map(i -> String.valueOf(i)).collect(Collectors.joining(",")));
  }

  public static List<Integer> topologicalSort(Graph g) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    List<Integer> processed = new ArrayList<Integer>(g.numV);
    for (int i = 0; i < g.numV; i++) {
      if (state[i] == TraverseState.UNDISCOVERED) {
        DFS.traverse(g, i, state, parents, entry, exit, 0, Optional.ofNullable(null), Optional.of(node -> {
          processed.add(node);
        }), Optional.ofNullable(null));
      }
    }
    Collections.reverse(processed);
    return processed;
  }
}

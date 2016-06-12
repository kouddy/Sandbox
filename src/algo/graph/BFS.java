package algo.graph;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class BFS {
  public static void tester() {
    Graph g = new Graph(5, false);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    BFS.traverse(g, 0, state, parents, Optional.of(i -> {
      System.out.println("Processed Node:" + i);
    }), Optional.ofNullable(null), Optional.of((x, y) -> {
      System.out.println("Processed Edge:" + x + "," + y);
    }));
  }

  public static void traverse(Graph g, int root, TraverseState[] state, int[] parents, Optional<IntConsumer> processEarly, Optional<IntConsumer> processLate,
      Optional<BiConsumer<Integer, Integer>> processEdge) {
    Queue<Integer> q = new LinkedList<>();
    state[root] = TraverseState.DISCOVERED;
    parents[root] = -1;
    q.add(root);
    while (!q.isEmpty()) {
      int elem = q.remove();
      processEarly.orElse(i -> {
      }).accept(elem);
      for (int child : g.adj.get(elem)) {
        /* For directed graph, all edge needs to be processed. */
        if (state[child] != TraverseState.PROCESSED || g.isDirected) {
          processEdge.orElse((a, b) -> {
          }).accept(elem, child);
        }
        if (state[child] == TraverseState.UNDISCOVERED) {
          state[child] = TraverseState.DISCOVERED;
          parents[child] = elem;
          q.add(child);
        }
      }
      state[elem] = TraverseState.PROCESSED;
      processLate.orElse(i -> {
      }).accept(elem);
    }
  }
}

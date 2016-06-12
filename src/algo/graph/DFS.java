package algo.graph;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class DFS {
  public static void test() {
    System.out.println();
    Graph g = new Graph(5, false);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(2, 4);

    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    int curTime = 0;
    traverse(g, 0, state, parents, entry, exit, curTime, Optional.of(i -> {
      System.out.println("Processed Node:" + i);
    }), Optional.ofNullable(null), Optional.of((x, y) -> {
      System.out.println("Processed Edge:" + x + "," + y);
    }));
  }

  public static int traverse(Graph g, int root, TraverseState[] state, int[] parents, int[] entry, int[] exit, int curTime, Optional<IntConsumer> processEarly, Optional<IntConsumer> processLate,
      Optional<BiConsumer<Integer, Integer>> processEdge) {
    state[root] = TraverseState.DISCOVERED;
    curTime++;
    entry[root] = curTime;
    processEarly.orElse(i -> {
    }).accept(root);
    for (int child : g.adj.get(root)) {
      /* child != parents[root] is key. */
      if ((state[child] != TraverseState.PROCESSED && child != parents[root]) || g.isDirected) {
        processEdge.orElse((x, y) -> {
        }).accept(root, child);
      }
      if (state[child] == TraverseState.UNDISCOVERED) {
        state[child] = TraverseState.DISCOVERED;
        parents[child] = root;
        curTime = traverse(g, child, state, parents, entry, exit, curTime, processEarly, processLate, processEdge);
      }
    }
    curTime++;
    exit[root] = curTime;
    state[root] = TraverseState.PROCESSED;
    processLate.orElse(i -> {
    }).accept(root);
    return curTime;
  }
}

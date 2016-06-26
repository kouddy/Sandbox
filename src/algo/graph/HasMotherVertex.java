package algo.graph;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

public class HasMotherVertex {
  public static void test() {
    Graph g = new Graph(5, true);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    g.addEdge(4, 1);
    System.out.println(hasMotherVertex(g)); /* No mother vertex because 4 is another component. */
  }

  public static boolean hasMotherVertex(Graph g) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    Set<Integer> components = new HashSet<Integer>();

    for (int i = 0; i < g.numV; i++) {
      if (state[i] == TraverseState.UNDISCOVERED) {
        components.add(i);
        int root = i;
        DFS.traverse(g, root, state, parents, entry, exit, 0, Optional.ofNullable(null), Optional.ofNullable(null), Optional.of((x, y) -> {
          if (state[y] == TraverseState.PROCESSED && components.contains(y)) {
            components.remove(y);
          }
        }));
      }
    }
    return components.size() == 1;
  }
}

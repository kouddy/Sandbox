package algo.graph;

import java.util.Optional;
import java.util.stream.IntStream;

public class TwoColor {
  public static void test() {
    Graph g = new Graph(5, false);
    g.addEdge(0, 1);
    g.addEdge(0, 3);
    g.addEdge(1, 2);
    g.addEdge(2, 3);
    g.addEdge(2, 4);
    twoColor(g); /* Can be colored */
    Graph g2 = new Graph(3, false);
    g2.addEdge(0, 1);
    g2.addEdge(1, 2);
    g2.addEdge(2, 0);
    twoColor(g2); /* Can't be colored */
  }

  public static void twoColor(Graph g) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parents = new int[g.numV];
    int[] colors = new int[g.numV];
    for (int i = 0; i < g.numV; i++) {
      if (state[i] == TraverseState.UNDISCOVERED) {
        colors[i] = 1;
        BFS.traverse(g, i, state, parents, Optional.ofNullable(null), Optional.ofNullable(null), Optional.of((x, y) -> {
          System.out.println(x + ":" + y + " =>" + colors[x] + "," + colors[y]);
          if (colors[x] == colors[y]) {
            System.out.println("Cannot color by 2 colors");
          } else {
            colors[y] = colors[x] % 2 + 1;
          }
        }));
      }
    }
  }
}

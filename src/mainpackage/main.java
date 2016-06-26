package mainpackage;

import java.util.Stack;
import java.util.stream.IntStream;

import algo.graph.Graph;
import algo.graph.TraverseState;
import algo.graph.WeightedGraph;

public class main {
  public static void main(String[] args) {
    System.out.println();
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
  }

  public static int coinChange(int value, int[] coins) {
    return -1;
  }

  public static void DFSStack(Graph g, int root) {
    TraverseState[] state = IntStream.range(0, g.numV).mapToObj(i -> TraverseState.UNDISCOVERED).toArray(TraverseState[]::new);
    int[] parent = new int[g.numV];
    int[] entry = new int[g.numV];
    int[] exit = new int[g.numV];
    int curTime = 0;

    state[root] = TraverseState.DISCOVERED;
    parent[root] = -1;
    entry[root] = ++curTime;
    Stack<Integer> s = new Stack<>();
    while (!s.isEmpty()) {
      int elem = s.peek();
      // procNodeEarly
      for (Integer edge : g.adj.get(elem)) {
        if ((state[edge] != TraverseState.PROCESSED && edge != parent[elem]) || g.isDirected) {
          // ProcessEdge
        }
        if (state[edge] == TraverseState.UNDISCOVERED) {
          state[edge] = TraverseState.DISCOVERED;
          parent[edge] = elem;
          entry[edge] = ++curTime;
          s.push(edge);
        }
      }

      if (s.peek() == elem) {
        s.pop();
        exit[elem] = ++curTime;
        state[elem] = TraverseState.PROCESSED;
        // process late.
      }
    }
  }

}
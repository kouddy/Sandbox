package algo.graph;

import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

public class MaximumFlow {
  public void test() {
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

    WeightedGraph maxFlowG = new WeightedGraph(4, true);
    maxFlowG.addEdge(0, 1, 5);
    maxFlowG.addEdge(0, 2, 2);
    maxFlowG.addEdge(1, 2, 8);
    maxFlowG.addEdge(1, 3, 3);
    maxFlowG.addEdge(2, 3, 5);
    maximumFlow(maxFlowG, 0, 3);
  }

  public static WeightedGraph maximumFlow(WeightedGraph g, int source, int sink) {
    WeightedGraph redNetwork = g.clone();
    for (int i = 0; i < g.numV; i++) {
      for (Entry<Integer, Integer> edge : g.adj.get(i)) {
        redNetwork.addEdge(edge.getKey(), i, 0);
      }
    }

    for (List<Edge> augPath = ShortestPath.dijkstraShortestPath(redNetwork, source, sink, Optional.of((a, b) -> b.getValue() > 0)); augPath != null; augPath = ShortestPath
        .dijkstraShortestPath(redNetwork, source, sink, Optional.of((a, b) -> b.getValue() > 0))) {
      int minFlow = augPath.stream().min((a, b) -> a.weight - b.weight).get().weight;
      for (Edge e : augPath) {
        Entry<Integer, Integer> edge = redNetwork.adj.get(e.from).stream().filter(i -> i.getKey() == e.to).findFirst().get();
        edge.setValue(edge.getValue() - minFlow);
        Entry<Integer, Integer> reverseEdge = redNetwork.adj.get(e.to).stream().filter(i -> i.getKey() == e.from).findFirst().get();
        reverseEdge.setValue(reverseEdge.getValue() + minFlow);
      }
    }
    /*  */
    WeightedGraph rtnVal = new WeightedGraph(redNetwork.numV, true);
    for (int i = 0; i < redNetwork.numV; i++) {
      for (Entry<Integer, Integer> edge : redNetwork.adj.get(i)) {
        int from = i;
        int to = edge.getKey();
        Entry<Integer, Integer> edgeToSwap = redNetwork.adj.get(from).stream().filter(j -> j.getKey().intValue() == to).findFirst().get();
        if (edgeToSwap.getValue() > 0 && g.adj.get(to).stream().anyMatch(t -> t.getKey() == from)) {
          rtnVal.addEdge(to, from, edgeToSwap.getValue());
        }
      }
    }
    return rtnVal;
  }
}

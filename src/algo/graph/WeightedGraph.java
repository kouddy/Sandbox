package algo.graph;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WeightedGraph {
  public boolean isDirected;
  public int numV;
  public List<List<SimpleEntry<Integer, Integer>>> adj; /* Entry<Vertex, edge weight> */

  public WeightedGraph(int numV, boolean isDirected) {
    this.numV = numV;
    this.isDirected = isDirected;
    adj = new ArrayList<>();
    for (int i = 0; i < numV; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public void addEdge(int from, int to, int weight) {
    SimpleEntry<Integer, Integer> toEdge = new SimpleEntry<>(to, weight);
    adj.get(from).add(toEdge);
    if (!isDirected) {
      SimpleEntry<Integer, Integer> fromEdge = new SimpleEntry<>(from, weight);
      adj.get(to).add(fromEdge);
    }
  }

  public void removeEdge(int from, int to) {
    adj.add(from, adj.get(from).stream().filter(e -> e.getKey().intValue() != to).collect(Collectors.toList()));
    if (!isDirected) {
      adj.add(to, adj.get(to).stream().filter(e -> e.getKey().intValue() != from).collect(Collectors.toList()));
    }
  }

  @Override
  public WeightedGraph clone() {
    WeightedGraph copy = new WeightedGraph(this.numV, this.isDirected);
    copy.adj = adj.stream().map(edges -> edges.stream().map(edge -> new SimpleEntry<>(edge.getKey(), edge.getValue())).collect(Collectors.toList())).collect(Collectors.toList());
    return copy;
  }
}

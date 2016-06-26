package algo.graph;

public class Edge {
  public int from;
  public int to;
  public int weight;

  public Edge(int from, int to, int weight) {
    this.from = from;
    this.to = to;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return String.format("%s -> %s (%s)", from, to, weight);
  }
}

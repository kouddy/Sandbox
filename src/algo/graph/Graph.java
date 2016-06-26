package algo.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
  public boolean isDirected;
  public int numV;
  public List<List<Integer>> adj;

  public Graph(int numV, boolean isDirected) {
    this.isDirected = isDirected;
    this.numV = numV;
    adj = new ArrayList<>(numV);
    for (int i = 0; i < numV; i++) {
      adj.add(new ArrayList<>());
    }
  }

  public Graph(int[][] adjMat, boolean isDirected) {
    this.isDirected = isDirected;
    this.numV = adjMat.length;
    adj = new ArrayList<>();
    for (int i = 0; i < adjMat.length; i++) {
      adj.add(new ArrayList<>());
      for (int j = 0; j < adjMat[i].length; j++) {
        if (adjMat[i][j] == 1) {
          adj.get(i).add(j);
        }
      }
    }
  }

  public void addEdge(int from, int to) {
    adj.get(from).add(to);
    if (!isDirected) {
      adj.get(to).add(from);
    }
  }
}

package datastructure;

public class MyTree {
  Node root;

  public void insert(int val) {
    if (null == root) {
      root = new Node();
      root.setValue(val);
      root.setLeft(null);
      root.setRight(null);
    } else {
      root.insert(val);
    }
  }

  public void traverseBreadth() {
    MyQueue q = new MyQueue();
    q.enqueue(root);
    while (false == q.empty()) {
      Node n = (Node) (q.dequeue());
      if (null != n.getLeft()) {
        q.enqueue(n.getLeft());
      }
      if (null != n.getRight()) {
        q.enqueue(n.getRight());
      }

      System.out.print(n.getValue() + " ");
    }
  }

  public void traverseDepthPre() {
    root.traverseDepthPre();
  }

  public void traverseDepthPost() {
    root.traverseDepthPost();
  }

  private class Node {
    int val;
    Node left;
    Node right;

    private void setValue(int val) {
      this.val = val;
    }

    private int getValue() {
      return val;
    }

    private void setLeft(Node n) {
      this.left = n;
    }

    private Node getLeft() {
      return left;
    }

    private void setRight(Node n) {
      this.right = n;
    }

    private Node getRight() {
      return right;
    }

    private void insert(int v) {
      if (val == v) {
        return;
      } else if (v < val) {
        if (left != null) {
          left.insert(v);
        } else {
          Node n = new Node();
          n.setValue(v);
          n.setLeft(null);
          n.setRight(null);
          left = n;
        }
      } else if (v > val) {
        if (right != null) {
          right.insert(v);
        } else {
          Node n = new Node();
          n.setValue(v);
          n.setLeft(null);
          n.setRight(null);
          right = n;
        }
      }
    }

    private void traverseDepthPre() {
      System.out.print(val + " ");
      if (null != left) {
        left.traverseDepthPre();
      }
      if (null != right) {
        right.traverseDepthPre();
      }
    }

    private void traverseDepthPost() {
      if (null != left) {
        left.traverseDepthPost();
      }
      if (null != right) {
        right.traverseDepthPost();
      }
      System.out.print(val + " ");
    }
  }
}

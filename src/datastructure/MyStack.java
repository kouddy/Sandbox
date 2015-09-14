package datastructure;

public class MyStack {
  Node top = null;
  int length = 0;

  public void push(Object o) {
    Node node = new Node();
    node.setObject(o);
    node.setNext(top);
    top = node;
    length++;
  }

  public Object pop() {
    Object ret = peek();
    if (null != ret) {
      top = top.getNext();
      length--;
    }
    return ret;
  }

  public Object peek() {
    if (top != null) {
      return top.getObject();
    }
    return null;
  }

  private class Node {
    Object o;
    Node next;

    private void setObject(Object o) {
      this.o = o;
    }

    private Object getObject() {
      return o;
    }

    private void setNext(Node n) {
      this.next = n;
    }

    private Node getNext() {
      return next;
    }
  }
}

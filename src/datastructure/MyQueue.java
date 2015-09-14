package datastructure;

public class MyQueue {
  Node head;
  Node tail;
  int length;

  public void enqueue(Object o) {
    Node node = new Node();
    node.setObject(o);
    node.setNext(null);

    if (0 == length) {
      head = node;
      tail = node;
    } else {
      tail.setNext(node);
      tail = node;
    }
    length++;
  }

  public Object dequeue() {
    if (0 == length) {
      return null;
    }

    Object ret = head.getObject();
    head = head.getNext();

    if (1 == length) {
      tail.getNext();
    }

    length--;
    return ret;
  }

  public boolean empty() {
    if (0 == length) {
      return true;
    } else {
      return false;
    }
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

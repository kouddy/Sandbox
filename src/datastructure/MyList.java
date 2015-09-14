package datastructure;

public class MyList {
  Node head;
  Node tail;
  int length;

  public void addFront(Object o) {
    add(o, 0);
  }

  public void addEnd(Object o) {
    add(o, length);
  }

  public void add(Object o, int i) {
    if (i < 0 || i > length) {
      return;
    }

    if (contains(o)) {
      return;
    }

    Node node = new Node();
    node.setObject(o);

    if (0 == length) {
      head = node;
      tail = node;
      node.setNext(null);
    } else if (0 == i) {
      node.setNext(head);
      head = node;
    } else if (length == i) {
      tail.setNext(node);
      tail = node;
      node.setNext(null);
    } else {
      Node tmp = head;
      for (int j = 0; j < i - 1; j++) {
        tmp = tmp.getNext();
      }
      node.setNext(tmp.getNext());
      tmp.setNext(node);
    }
    length++;
  }

  public boolean delete(Object o) {
    if (0 == length) {
      return false;
    }

    if (1 == length) {
      head = null;
      tail = null;
      length--;
      return true;
    } else if (o.equals(head.getObject())) {
      head = head.getNext();
      length--;
      return true;
    } else if (o.equals(tail.getObject())) {
      Node tmp = head;
      for (; tmp.getNext() != tail; tmp = tmp.getNext()) {
      }
      tmp.setNext(null);
      tail = tmp;
      length--;
      return true;
    } else {
      for (Node tmp = head; tmp != tail; tmp = tmp.getNext()) {
        if (tmp.getObject().equals(o)) {
          tmp.setObject(tmp.getNext().getObject());
          tmp.setNext(tmp.getNext().getNext());
          length--;
          return true;
        }
      }
    }
    return false;
  }

  public Object delete(int i) {
    if (0 == length) {
      return null;
    }

    if (1 == length) {
      Object ret = head.getObject();
      head = null;
      tail = null;
      length--;
      return ret;
    } else if (0 == i) {
      Object ret = head.getObject();
      head = head.getNext();
      length--;
      return ret;
    } else if ((length - 1) == i) {
      Node tmp = head;
      for (; tmp.getNext() != tail; tmp = tmp.getNext()) {
      }
      Object ret = tail.getObject();
      tmp.setNext(null);
      tail = tmp;
      length--;
      return ret;
    } else {
      Node tmp = head;
      for (int j = 0; j < i; j++) {
        tmp = tmp.getNext();
      }
      Object ret = tmp.getObject();
      tmp.setObject(tmp.getNext().getObject());
      tmp.setNext(tmp.getNext().getNext());
      length--;
      return ret;
    }
  }

  public boolean contains(Object o) {
    for (Node tmp = head; tmp != null; tmp = tmp.getNext()) {
      if (tmp.getObject().equals(o)) {
        return true;
      }
    }
    return false;
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

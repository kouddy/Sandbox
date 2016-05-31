package algo;

import datastructure.Node;

public class MergeList {
  public static void mergeTester() {
    Node A = new Node(1, new Node(2, new Node(5, null)));
    Node B = new Node(3, new Node(4, new Node(7, null)));

    System.out.println(mergeIter(A, B));

    A = new Node(1, new Node(2, new Node(5, null)));
    B = new Node(3, new Node(4, new Node(7, null)));
    System.out.println(mergeRec(A, B));
  }

  public static Node mergeRec(Node A, Node B) {
    if (null == A)
      return B;
    if (null == B)
      return A;

    Node smaller = A.value > B.value ? B : A;
    Node bigger = A.value > B.value ? A : B;
    Node head = smaller;
    head.next = mergeRec(smaller.next, bigger);
    return head;
  }

  public static Node mergeIter(Node A, Node B) {
    if (null == A)
      return B;
    if (null == B)
      return A;

    Node smaller = A.value > B.value ? B : A;
    Node bigger = A.value > B.value ? A : B;
    Node prev = smaller;
    Node head = prev;
    smaller = smaller.next;
    while (smaller != null) {
      if (smaller.value <= bigger.value) {
        smaller = smaller.next;
        prev = prev.next;
      } else {
        prev.next = bigger;
        bigger = smaller;
        smaller = prev.next;
      }
    }
    prev.next = bigger;
    return head;
  }
}

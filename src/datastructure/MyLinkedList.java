package datastructure;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

public class MyLinkedList {
  public static void removeDuplicate(List<String> l) {
    Hashtable<String, String> table = new Hashtable<String, String>();
    Iterator<String> it = l.iterator();
    String o;
    while (it.hasNext()) {
      o = it.next();
      if (table.contains(o)) {
        l.remove(o);
      } else {
        table.put(o, o);
      }
    }
  }
}

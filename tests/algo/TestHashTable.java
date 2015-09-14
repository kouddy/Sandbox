package algo;

import org.junit.Assert;
import org.junit.Test;

import datastructure.HashTable;

public class TestHashTable {

  @Test
  public void testPutTwice() {
    HashTable h = new HashTable(11);
    String s = "key";
    String val1 = "val1";
    String val2 = "val2";
    h.put(s, val1);
    Assert.assertEquals(val1, h.get(s));
    h.put(s, val2);
    Assert.assertNotEquals(val2, h.get(s));
  }
}
